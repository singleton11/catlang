package com.singleton11.catlang.lexer

val whitespaces = setOf(' ', '\t', '\n')

class Lexer(private val source: String) : AutoCloseable {
    private var pos = 0
    private var line = 0
    private var col = 0
    private var current: Char = 0.toChar()

    private val errors = mutableListOf<LexingError>()
    private val tokens = mutableListOf<Token>()

    fun scan(): Pair<Collection<Token>, Collection<LexingError>> {

        while (!isEnd()) {
            current = advance()
            when (current) {
                '(' -> tokens.add(Token(Token.TokenType.LeftParen, "(", line, col))
                ')' -> tokens.add(Token(Token.TokenType.RightParen, ")", line, col))
                '{' -> tokens.add(Token(Token.TokenType.LeftBrace, "{", line, col))
                '}' -> tokens.add(Token(Token.TokenType.RightBrace, "}", line, col))
                '"' -> {
                    next()
                    current = advance()
                    tokens.add(Token(Token.TokenType.StringLiteral, peek({ current != '"' }), line, col))
                    next()
                }
                in whitespaces -> tokens.add(Token(Token.TokenType.Whitespace, peek({ current in whitespaces }) {
                    if (current == '\n') {
                        line++
                        col = 0
                    }
                }, line, col))
                else -> {
                    if (current.isLetter()) {
                        tokens.add(Token(Token.TokenType.Identifier, peek({ current.isLetter() }), line, col))
                    } else {
                        errors.add(LexingError("Unexpected $current on line $line", line, col))
                    }
                }
            }
            next()
        }

        return tokens to errors
    }

    override fun close() {
        pos = 0
        line = 0
        col = 0

        tokens.clear()
        errors.clear()
    }

    private fun peek(until: Lexer.() -> Boolean, additionally: Lexer.() -> Unit = {}) = buildString {
        while (until()) {
            additionally()
            append(current)
            next()
            if (isEnd()) {
                errors.add(LexingError("\" expected on line $line", line, col))
                return@buildString
            }
            current = advance()
        }
        prev()
    }

    private fun prev() {
        pos--
        col--
    }

    private fun next() {
        pos++
        col++
    }

    private fun isEnd() = pos >= source.length

    private fun advance() = source[pos]

    data class LexingError(val message: String, val line: Int, val col: Int) {
        override fun toString() = "LexingError(message='${message.escape()}', line=$line, col=$col)"
    }
}