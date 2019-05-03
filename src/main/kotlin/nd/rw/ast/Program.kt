package nd.rw.ast

class Program : Node {

    val statements = mutableListOf<Node>()

    override fun tokenLiteral(): String {
        return statements.firstOrNull()?.tokenLiteral() ?: ""
    }

    override fun toString(): String {
        val builder = StringBuilder()
        statements.forEach { node -> builder.append("$node\n") }
        return "Program(statements=$builder)"
    }

}