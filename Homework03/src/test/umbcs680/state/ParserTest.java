package umbcs680.state;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testParseSuccess() {
        Parser parser = new Parser("TOKENS:VALID CODE");
        String ast = parser.parse();
        assertEquals("AST: VALID CODE", ast);
    }

    @Test
    public void testParseFailure() {
        Parser parser = new Parser("ERROR");
        String ast = parser.parse();
        assertEquals("ERROR", ast);
    }
}

