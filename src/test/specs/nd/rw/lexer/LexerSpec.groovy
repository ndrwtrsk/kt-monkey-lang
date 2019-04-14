package nd.rw.lexer

import spock.lang.Specification
import token.Token

import static token.TokenType.*

class LexerSpec extends Specification {

    def "should return correctly next tokens"() {
        given:
            def input = "=+(){},;"
            def lexer = new Lexer(input)

        when:
            def tokens = []
            (0..(input.size())).each {
                tokens = tokens + lexer.nextToken()
            }
        then:
            tokens == [
                    new Token(ASSIGN, "="),
                    new Token(PLUS, "+"),
                    new Token(LPAREN, "("),
                    new Token(RPAREN, ")"),
                    new Token(LBRACE, "{"),
                    new Token(RBRACE, "}"),
                    new Token(COMMA, ","),
                    new Token(SEMICOLON, ";"),
                    new Token(EOF, ""),
            ]

    }

}
