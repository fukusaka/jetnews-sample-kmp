
def _ktlint_impl(ctx: AnalysisContext) -> list[Provider]:
    file_list = ctx.actions.write(
        "file_list.txt",
        ctx.attrs.srcs,
    )

    sh = ctx.actions.write(
        ctx.label.name + ".sh",
        cmd_args([
            "#!/bin/sh",
            "set -e",
            "",
            cmd_args(["cat", file_list, "|", "xargs", ctx.attrs.ktlint_bin[RunInfo], ctx.attrs.args, '"$@"'], delimiter=" "),
        ]),
        is_executable = True,
    )

    return [
        DefaultInfo(default_output = sh, other_outputs = [file_list]),
        RunInfo(args = cmd_args(["sh", sh]).hidden(file_list, ctx.attrs.ktlint_bin[RunInfo])),
    ]

ktlint = rule(
    impl = _ktlint_impl,
    attrs = {
        "ktlint_bin": attrs.default_only(attrs.exec_dep(providers = [RunInfo], default = "@toolchains//:ktlint_bin")),
        "srcs": attrs.list(attrs.source(), default = []),
        "args": attrs.list(attrs.string(), default = []),
    },
)
