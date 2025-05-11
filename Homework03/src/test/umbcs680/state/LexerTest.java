package umbcs680.state;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LexerTest {

    @Test
    public void testLexSuccess() {
        Lexer lexer = new Lexer("valid source");
        String tokens = lexer.lex();
        assertEquals("TOKENS: VALID SOURCE", tokens);
    }

    @Test
    public void testLexFailure() {
        Lexer lexer = new Lexer("LEXING_ERROR in source");
        String tokens = lexer.lex();
        assertEquals("ERROR", tokens);
    }
}
