package nd.rw.lexer

import nd.rw.extensions.isLetterOrUnderScore
import nd.rw.token.Token
import nd.rw.token.TokenType.*

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
        eatWhitespace()
        val token = when (currentChar) {
            '=' -> extractAssignOrEq()
            ';' -> Token(SEMICOLON, currentChar)
            '(' -> Token(LPAREN, currentChar)
            ')' -> Token(RPAREN, currentChar)
            '{' -> Token(LBRACE, currentChar)
            '}' -> Token(RBRACE, currentChar)
            ',' -> Token(COMMA, currentChar)
            '+' -> Token(PLUS, currentChar)
            '-' -> Token(MINUS, currentChar)
            '*' -> Token(ASTERISK, currentChar)
            '/' -> Token(SLASH, currentChar)
            '<' -> Token(LT, currentChar)
            '>' -> Token(GT, currentChar)
            '!' -> extractBangOrNotEq()
            NULL -> Token(EOF, "")
            else -> return extractMultiCharToken() // TODO magic GOTO jump... skips readChar() and return token and immediately quits

        }
        readChar()
        return token
    }

    private fun extractAssignOrEq(): Token {
        return if (peekChar() == '=') {
            val ch = currentChar
            readChar()
            Token(EQ, "$ch$currentChar")
        } else {
            Token(ASSIGN, currentChar)
        }
    }

    private fun extractBangOrNotEq(): Token {
        return if (peekChar() == '=') {
            val ch = currentChar
            readChar()
            Token(NOT_EQ, "$ch$currentChar")
        } else {
            Token(BANG, currentChar)
        }
    }

    private fun extractMultiCharToken(): Token {
        return when {
            currentChar.isLetterOrUnderScore() -> {
                val literal: String = readIdentifier()
                val type = Token.lookUpIdent(literal) ?: IDENT
                Token(type, literal)
            }
            currentChar.isDigit() -> {
                Token(INT, readNumber())
            }
            else -> Token(ILLEGAL, "")
        }
    }

    private fun readNumber(): String {
        val startPosition = this.position
        while(currentChar.isDigit()){
            readChar()
        }
        return input.substring(startPosition, this.position)
    }

    private fun readIdentifier(): String {
        val startingPosition = this.position
        while (currentChar.isLetterOrUnderScore()) {
            readChar()
        }
        return input.substring(startingPosition, this.position)
    }

    private fun eatWhitespace() {
        while (currentChar.isWhitespace()) {
            readChar()
        }
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

    private fun peekChar(): Char {
        return if (readPosition >= input.length) {
            NULL
        } else {
            input[readPosition]
        }
    }

}