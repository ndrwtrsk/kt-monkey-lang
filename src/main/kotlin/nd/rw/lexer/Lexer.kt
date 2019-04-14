package nd.rw.lexer

import token.Token
import token.TokenType.*

class Lexer(private val input: String) {

    companion object {
        const val NULL: Char = 0.toChar()
    }

    init {
        readChar()
    }

    private var position = 0
    private var readPosition = 0
    private var currentChar = NULL


    fun nextToken(): Token {
        val token = when(currentChar) {
            '=' -> Token(ASSIGN, currentChar)
            ';' -> Token(SEMICOLON, currentChar)
            '(' -> Token(LPAREN, currentChar)
            ')' -> Token(RPAREN, currentChar)
            '{' -> Token(LBRACE, currentChar)
            '}' -> Token(RBRACE, currentChar)
            ',' -> Token(COMMA, currentChar)
            '+' -> Token(PLUS, currentChar)
            else -> Token(EOF, "")
        }
        readChar()
        return token
    }


    // TODO support for UNICODE?
    private fun readChar() {
        currentChar = if (readPosition >= input.length) {
            NULL
        } else {
            input[readPosition]
        }
        position = readPosition
        readPosition++
    }

}