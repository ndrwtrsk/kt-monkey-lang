package nd.rw.ast

import nd.rw.token.Token
import nd.rw.token.TokenType

class ReturnStatement(private val expression: Expression?,
                      private val token: Token = Token(TokenType.RETURN, "return")) : Statement {


    override fun tokenLiteral(): String = token.literal

    override fun statementNode() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}