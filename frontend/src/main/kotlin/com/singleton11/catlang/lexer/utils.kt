package com.singleton11.catlang.lexer

val translationLookup = mapOf(
    '\b' to "\\b",
    '\n' to "\\n",
    '\t' to "\\t",
    '\u000c' to "\\f",
    '\r' to "\\r"
)

fun String.escape() = buildString {
    for (c in this@escape) translationLookup[c]?.let { append(it) } ?: append(c)
}