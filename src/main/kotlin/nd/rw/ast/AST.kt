package nd.rw.ast

interface Node {
    fun tokenLiteral(): String

}

interface Statement : Node {
    fun statementNode()
}

interface Expression : Node {
    fun expressionNode()
}

