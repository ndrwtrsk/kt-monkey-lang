package nd.rw.ast

import nd.rw.token.Token

class Identifier(private val token: Token, private val value: String) : Expression {

    override fun tokenLiteral(): String {
        return token.literal
    }

    override fun expressionNode() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}