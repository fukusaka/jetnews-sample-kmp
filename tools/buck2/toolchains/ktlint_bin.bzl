def _ktlint_bin_impl(ctx: AnalysisContext) -> list[Provider]:
    output = ctx.actions.declare_output("ktlint")

    ctx.actions.download_file(
        output,
        "https://github.com/pinterest/ktlint/releases/download/{}/ktlint".format(ctx.attrs.version),
        sha256 = ctx.attrs.sha256,
        is_executable = True,
        is_deferrable = True,
    )

    return [
        DefaultInfo(default_output = output),
        RunInfo(args = [output]),
    ]


ktlint_bin = rule(
    impl = _ktlint_bin_impl,
    attrs = {
        "version": attrs.string(),
        "sha256": attrs.string(),
    },
)
