package nd.rw.ast

class Program : Node {

    val statements = mutableListOf<Node>()

    override fun tokenLiteral(): String {
        return statements.firstOrNull()?.tokenLiteral() ?: ""
    }

}