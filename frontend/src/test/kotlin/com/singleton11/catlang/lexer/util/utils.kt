package com.singleton11.catlang.lexer.util

fun <C1 : Iterable<A>, C2 : Iterable<B>, A, B> Pair<C1, C2>.prettyPrint() = buildString {
    appendLine("(")
    appendLine("  first: [")
    for (element in this@prettyPrint.first) {
        appendLine("    $element")
    }
    appendLine("  ]")
    appendLine("  second: [")
    for (element in this@prettyPrint.second) {
        appendLine("    $element")
    }
    appendLine("  ]")
    appendLine(")")
}