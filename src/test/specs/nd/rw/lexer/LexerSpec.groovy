package nd.rw.lexer

import nd.rw.Lexer
import spock.lang.Specification

class LexerSpec extends Specification {

    def "object is created"() {
        expect:
            new Lexer()
    }

}
