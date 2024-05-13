# A list of available rules and their signatures can be found here: https://buck2.build/docs/api/rules/

#genrule(
#    name = "hello_world",
#    out = "out.txt",
#    cmd = "echo BUILT BY BUCK2> $OUT",
#)

load("@toolchains//:ktlint.bzl", "ktlint")

ktlint(
    name = "ktlintCheck",
    args = [
        "--relative",
        "--color",
    ],
    srcs = glob([
        "**/*.kt",
        "**/*.kts",
    ]),
)

ktlint(
    name = "ktlintCheckCI",
    args = [
        "--relative",
        "--reporter=plain",
        "--reporter=sarif,output=build/reports/ktlint-results.sarif",
    ],
    srcs = glob([
        "**/*.kt",
        "**/*.kts",
    ]),
)

ktlint(
    name = "ktlintFormatFull",
    args = [
        "--format",
        "--relative",
        "--color",
    ],
    srcs = glob([
        "**/*.kt",
        "**/*.kts",
    ]),
)

load("//tools/buck2:ktlint_format.bzl", "ktlint_format")
ktlint_format(
    name = "ktlintFormat",
    args = [
        "--relative",
        "--color",
    ],
    srcs = glob([
        "**/*.kt",
        "**/*.kts",
    ]),
)
