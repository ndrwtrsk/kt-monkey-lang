package nd.rw.ast

import nd.rw.token.Token
import nd.rw.token.TokenType

class LetStatement(val name: Identifier,
                   val value: Expression? = null,
                   val token: Token = Token(TokenType.LET, "let")) : Statement {

    override fun statementNode() {


    }

    override fun tokenLiteral(): String {
        return token.literal
    }

}