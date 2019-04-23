package nd.rw

import nd.rw.ast.LetStatement
import nd.rw.ast.ReturnStatement
import nd.rw.lexer.Lexer
import nd.rw.token.Token
import nd.rw.token.TokenType
import spock.lang.Specification

class ParserSpec extends Specification {

    def "should parse simple let statement"() {
        given:
            def input = 'let five = 5;'
            def lexer = new Lexer(input)
            def parser = new Parser(lexer)

        when:
            def program = parser.parse()

        then:
            program
            program.statements.size() == 1
            program.statements[0] instanceof LetStatement
            with((LetStatement) program.statements[0]) {
                token == new Token(TokenType.LET, 'let')
                name.value == 'five'
                !value // todo wait for expression parsing
            }
    }

    def "should parse simple return statement"() {
        given:
            def input = "return 999111;"
            def lexer = new Lexer(input)
            def parser = new Parser(lexer)

        when:
            def program = parser.parse()

        then:
            program
            program.statements.size() == 1
            program.statements[0] instanceof ReturnStatement
            with((ReturnStatement) program.statements[0]) {
                token == new Token(TokenType.RETURN, 'return')
                !expression // todo wait for expression parsing
            }
    }

}
