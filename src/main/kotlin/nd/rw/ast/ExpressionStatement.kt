package nd.rw.ast

import nd.rw.token.Token

class ExpressionStatement(private val expression: Expression,
                          private val token: Token) : Statement {

    override fun tokenLiteral(): String {
        return token.literal

    }

    override fun statementNode() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toString(): String {
        return "$expression;"
    }


}