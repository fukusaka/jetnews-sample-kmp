_DATA_DIR = ".buck2_data"
_INCREMENTAL_STATE_FILE = _DATA_DIR + "/incremental_state.txt"

def _ktlint_format_impl(ctx: AnalysisContext) -> list[Provider]:
    # 前回成功時のチェックサム
    previous_run_checksum = ctx.actions.declare_output("previous_run.checksum")

    # 現在のチェックサム
    current_checksum = ctx.actions.declare_output("current.checksum")

    # 整形対象となりうる全ファイルリスト
    all_files = ctx.actions.write("all_files.txt", ctx.attrs.srcs)

    # 前回成功時から修正があり整形対象とすべきリスト
    target_files = ctx.actions.declare_output("target_files.txt")

    #
    # ktlint対象のファイルのチェックサムを計算してファイルに出力する

    prepare_checksum_file = ctx.actions.write(
        "prepare_checksum_file.sh",
        cmd_args([
            "#!/bin/sh",
            "set -e",
            "",
            cmd_args(["cat", "/dev/null", ctx.attrs.incremental_state, ">", previous_run_checksum.as_output()], delimiter=" "),
            cmd_args(["cat", all_files, "|", "xargs", "cksum", ">", current_checksum.as_output()], delimiter=" "),
        ]),
        is_executable = True,
    )

    ctx.actions.run(
        cmd_args(["sh", prepare_checksum_file]).hidden([
            previous_run_checksum.as_output(),
            current_checksum.as_output(),
            ctx.attrs.srcs,
            ctx.attrs.incremental_state,
        ]),
        category="ktlint",
        identifier="prepare_ktlint",
    )

    #
    # チェックサムを比較してktlint対象にすべきファイルリスト（target_files）を生成する

    def parse_checksum(content_string):
        table = [ line.strip().split() for line in content_string.splitlines() ]
        return { columns[2]: "{}:{}".format(columns[0], columns[1]) for columns in table }

    def f(ctx, artifacts, outputs):
        previous_run = parse_checksum(artifacts[previous_run_checksum].read_string())
        current = parse_checksum(artifacts[current_checksum].read_string())
        skip_files = { src.short_path: True for src in ctx.attrs.srcs if src.short_path in previous_run and previous_run[src.short_path] == current.get(src.short_path) }
        ctx.actions.write(
            outputs[target_files],
            [ src for src in ctx.attrs.srcs if src.short_path not in skip_files ],
        )

    ctx.actions.dynamic_output(
        dynamic = [current_checksum, previous_run_checksum],
        inputs = [] + ctx.attrs.srcs,
        outputs = [target_files],
        f = f,
    )

    #
    # buck2 run で実行されるスクリプト
    # buck2 build でソースファイルを修正するのは御法度なので、 buck2 run で書き換える。
    #
    # JVMの警告「WARNING: An illegal reflective access operation has occurred」がノイズなので
    # "2>/dev/null" で隠している。
    #
    # ktlintFormatに成功したときは、チェックサムを _INCREMENTAL_STATE_FILE に書き込む。
    # 次回の実行でチェックサムが同じ場合はフォーマットをスキップする。

    run_sh = ctx.actions.write(
        "run_ktlint_format.sh",
        cmd_args([
            "#!/bin/sh",
            "set -e",
            "",
            cmd_args(["cat", target_files, "|", "xargs", ctx.attrs.ktlint_bin[RunInfo], "--format", ctx.attrs.args, '"$@"', "2>/dev/null"], delimiter=" "),
            "",
            cmd_args(["mkdir", "-p", _DATA_DIR], delimiter=" "),
            cmd_args(["cat", all_files, "|", "xargs", "cksum", ">", _INCREMENTAL_STATE_FILE], delimiter=" "),
        ]),
        is_executable = True,
    )

    return [
        DefaultInfo(default_output = target_files, other_outputs = [all_files, previous_run_checksum, current_checksum]),
        RunInfo(args = cmd_args([run_sh]).hidden([
            run_sh,
            target_files,
            all_files,
            previous_run_checksum,
            current_checksum,
            ctx.attrs.ktlint_bin[RunInfo],
            ctx.attrs.args,
        ])),
    ]

_ktlint_format = rule(
    impl = _ktlint_format_impl,
    attrs = {
        "ktlint_bin": attrs.default_only(attrs.exec_dep(providers = [RunInfo], default = "@toolchains//:ktlint_bin")),
        "srcs": attrs.list(attrs.source(), default = []),
        "args": attrs.list(attrs.string(), default = []),
        "incremental_state": attrs.list(attrs.source()),
    },
)

def ktlint_format(**kwargs):
    _ktlint_format(
        incremental_state = glob([_INCREMENTAL_STATE_FILE]),
        **kwargs,
    )
