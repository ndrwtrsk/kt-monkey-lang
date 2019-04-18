package nd.rw.token

import nd.rw.token.TokenType.*

data class Token(val type: TokenType, val literal: String) {
    constructor(type: TokenType, char: Char) : this(type, char.toString())

    companion object {
        private val keywords = mapOf(
                "fn" to FUNCTION,
                "let" to LET,
                "true" to TRUE,
                "false" to FALSE,
                "if" to IF,
                "else" to ELSE,
                "return" to RETURN)

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
    EQ("=="),
    NOT_EQ("!="),

    // Delimiters
    COMMA(","),
    SEMICOLON(";"),

    LPAREN("("),
    RPAREN(")"),
    LBRACE("{"),
    RBRACE("}"),

    // Keywords
    FUNCTION("FUNCTION"),
    LET("LET"),
    TRUE("TRUE"),
    FALSE("FALSE"),
    IF("IF"),
    ELSE("ELSE"),
    RETURN("RETURN")

}