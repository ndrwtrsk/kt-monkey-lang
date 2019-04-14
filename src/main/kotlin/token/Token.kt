package token

data class Token(val type: TokenType, val literal: String) {
    constructor(type: TokenType, char: Char) : this(type, char.toString())
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