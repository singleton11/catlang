package com.singleton11.catlang.lexer

import org.apache.commons.text.StringEscapeUtils

data class Token(val type: TokenType, val value: String, val line: Int, val col: Int) {
    enum class TokenType {
        Identifier, LeftParen, RightParen, LeftBrace, RightBrace, Whitespace, StringLiteral
    }

    override fun toString(): String {
        return "Token(type=$type, value='${StringEscapeUtils.escapeJava(value)}', line=$line, col=$col)"
    }
}