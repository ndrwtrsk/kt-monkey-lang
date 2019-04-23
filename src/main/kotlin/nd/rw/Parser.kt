package nd.rw

import nd.rw.ast.*
import nd.rw.lexer.Lexer
import nd.rw.token.Token
import nd.rw.token.TokenType
import nd.rw.token.TokenType.*

class Parser(private val lexer: Lexer) {

    private var curToken: Token = Token(EOF, 0.toChar())
    private var peekToken: Token = Token(EOF, 0.toChar())
    private val errors = mutableListOf<String>()
        get

    init {
        nextToken()
        nextToken()
    }

    fun parse(): Program? {
        val program = Program()
        while (curToken.type != EOF) {
            parseStatement()?.let {
                program.statements.add(it)
            }

            nextToken()

        }
        return program
    }

    private fun parseStatement(): Statement? {
        return when (curToken.type) {
            LET -> parseLetStatement()
            RETURN -> parseReturnStatement()
            else -> null
        }
    }

    private fun parseLetStatement(): Statement? {
        if (!expectTokenAtPeek(IDENT)) {
            return null
        }

        val name = Identifier(curToken, curToken.literal)

        if (!expectTokenAtPeek(ASSIGN)) {
            return null
        }

        // TODO: We're skipping the expressions until we
        // encounter a semicolon
        while (!currentTokenIs(SEMICOLON)) {
            nextToken()
        }

        return LetStatement(name = name)
    }

    private fun parseReturnStatement(): Statement? {
        nextToken()

        // TODO: We're skipping the expressions until we
        // encounter a semicolon
        while (!currentTokenIs(SEMICOLON)) {
            nextToken()
        }

        return ReturnStatement(expression = null)
    }

    private fun nextToken() {
        curToken = peekToken
        peekToken = lexer.nextToken()
    }

    private fun expectTokenAtPeek(expected: TokenType): Boolean {
        return if (peekTokenIs(expected)) {
            nextToken()
            true
        } else {
            peekError(expected)
            false
        }
    }

    private fun peekTokenIs(expected: TokenType): Boolean {
        return peekToken.type == expected
    }

    private fun currentTokenIs(expected: TokenType): Boolean {
        return curToken.type == expected
    }

    private fun peekError(expected: TokenType) {
        val error = "expected next token to be $expected but got ${peekToken.type} instead"
        errors.add(error)
    }
}