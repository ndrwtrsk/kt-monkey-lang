package nd.rw.token

data class Token(val type: TokenType, val literal: String) {
    constructor(type: TokenType, char: Char) : this(type, char.toString())

    companion object {

        private val keywords = mapOf("fn" to TokenType.FUNCTION, "let" to TokenType.LET)

        fun lookUpIdent(ident: String): TokenType? {
            return keywords[ident]
        }
    }

}


enum class TokenType(val value: String) {
    ILLEGAL("ILLEGAL"),
    EOF("EOF"),

    // Identifiers + literals
    IDENT("IDENT"), // add, foobar, x, y, ...
    INT("INT"),   // 1343456

    // Operators
    ASSIGN("="),
    PLUS("+"),
    MINUS("-"),
    BANG("!"),
    ASTERISK("*"),
    SLASH("/"),

    LT("<"),
    GT(">"),

    // Delimiters
    COMMA(","),
    SEMICOLON(";"),

    LPAREN("("),
    RPAREN(")"),
    LBRACE("{"),
    RBRACE("}"),

    // Keywords
    FUNCTION("FUNCTION"),
    LET("LET")

}