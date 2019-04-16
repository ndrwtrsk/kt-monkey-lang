package nd.rw.lexer

import spock.lang.Specification
import nd.rw.token.Token
import spock.lang.Unroll

import static nd.rw.token.TokenType.*

class LexerSpec extends Specification {

    @Unroll
    def "should return correctly next tokens"() {
        given:

            def lexer = new Lexer(input)

        when:
            def tokens = []
            expectedTokens.each {
                tokens = tokens + lexer.nextToken()
            }
        then:
            tokens == expectedTokens

        where:
            input << [
                    "=+(){},;",
                    """
                    let five = 5;
                    let ten = 10;

                    let add = fn(x, y) {
                        x + y;
                    };

                    let result = add(five, ten);
                    !-/*5;
                    5 < 10 > 5;
                    """,


            ]
            expectedTokens << [
                    [
                            new Token(ASSIGN, "="),
                            new Token(PLUS, "+"),
                            new Token(LPAREN, "("),
                            new Token(RPAREN, ")"),
                            new Token(LBRACE, "{"),
                            new Token(RBRACE, "}"),
                            new Token(COMMA, ","),
                            new Token(SEMICOLON, ";"),
                            new Token(EOF, ""),
                    ],
                    [
                            new Token(LET, "let"),
                            new Token(IDENT, "five"),
                            new Token(ASSIGN, "="),
                            new Token(INT, "5"),
                            new Token(SEMICOLON, ";"),
                            new Token(LET, "let"),
                            new Token(IDENT, "ten"),
                            new Token(ASSIGN, "="),
                            new Token(INT, "10"),
                            new Token(SEMICOLON, ";"),
                            new Token(LET, "let"),
                            new Token(IDENT, "add"),
                            new Token(ASSIGN, "="),
                            new Token(FUNCTION, "fn"),
                            new Token(LPAREN, "("),
                            new Token(IDENT, "x"),
                            new Token(COMMA, ","),
                            new Token(IDENT, "y"),
                            new Token(RPAREN, ")"),
                            new Token(LBRACE, "{"),
                            new Token(IDENT, "x"),
                            new Token(PLUS, "+"),
                            new Token(IDENT, "y"),
                            new Token(SEMICOLON, ";"),
                            new Token(RBRACE, "}"),
                            new Token(SEMICOLON, ";"),
                            new Token(LET, "let"),
                            new Token(IDENT, "result"),
                            new Token(ASSIGN, "="),
                            new Token(IDENT, "add"),
                            new Token(LPAREN, "("),
                            new Token(IDENT, "five"),
                            new Token(COMMA, ","),
                            new Token(IDENT, "ten"),
                            new Token(RPAREN, ")"),
                            new Token(SEMICOLON, ";"),
                            new Token(BANG, "!"),
                            new Token(MINUS, "-"),
                            new Token(SLASH, "/"),
                            new Token(ASTERISK, "*"),
                            new Token(INT, "5"),
                            new Token(SEMICOLON, ";"),
                            new Token(INT, "5"),
                            new Token(LT, "<"),
                            new Token(INT, "10"),
                            new Token(GT, ">"),
                            new Token(INT, "5"),
                            new Token(SEMICOLON, ";"),
                            new Token(EOF, ""),
                    ]

            ]
    }

}
