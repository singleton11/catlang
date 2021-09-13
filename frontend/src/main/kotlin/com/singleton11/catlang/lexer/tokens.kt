package com.singleton11.catlang.lexer

data class Token(val type: TokenType, val value: String, val line: Int, val col: Int) {
    enum class TokenType {
        Identifier, LeftParen, RightParen, LeftBrace, RightBrace, Whitespace, StringLiteral
    }
}