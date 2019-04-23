package nd.rw

import nd.rw.ast.LetStatement
import nd.rw.lexer.Lexer
import nd.rw.token.Token
import nd.rw.token.TokenType
import spock.lang.Specification

class ParserSpec extends Specification {

    def "should return simple let statement program"() {
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

}
