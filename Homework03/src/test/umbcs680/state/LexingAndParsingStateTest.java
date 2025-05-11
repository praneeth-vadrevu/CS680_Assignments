package umbcs680.state;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LexingAndParsingStateTest {

    @Test
    public void testSuccessfulLexingAndParsing() {
        Compiler compiler = new Compiler("valid code");
        LexingAndParsingState state = new LexingAndParsingState();

        state.execute(compiler);

        assertNotNull(compiler.getInterCode(), "Intermediate code should not be null after successful parsing");
        assertTrue(compiler.getInterCode().startsWith("AST:"), "Intermediate code should start with AST:");
        assertTrue(compiler.getState() instanceof CodeGenerationState, "Compiler should transition to CodeGenerationState");
    }

    @Test
    public void testEmptySourceCodeTriggersError() {
        Compiler compiler = new Compiler("");
        LexingAndParsingState state = new LexingAndParsingState();

        state.execute(compiler);

        assertTrue(compiler.getState() instanceof ErrorState, "Compiler should transition to ErrorState");
        assertEquals("Source code is empty!", compiler.getErrorMsg(), "Error message should indicate empty source");
    }

    @Test
    public void testLexingErrorTriggersErrorState() {
        Compiler compiler = new Compiler("LEXING_ERROR");
        LexingAndParsingState state = new LexingAndParsingState();

        state.execute(compiler);

        assertTrue(compiler.getState() instanceof ErrorState, "Compiler should transition to ErrorState");
        assertEquals("Lexing error occurred", compiler.getErrorMsg(), "Error message should indicate lexing error");
    }

    @Test
    public void testParsingErrorTriggersErrorState() {
        Compiler compiler = new Compiler("valid code with ERROR");
        LexingAndParsingState state = new LexingAndParsingState();

        state.execute(compiler);

        assertTrue(compiler.getState() instanceof ErrorState, "Compiler should transition to ErrorState");
        assertEquals("Lexing error occurred", compiler.getErrorMsg(), "Error message should indicate lexing error");

    }
}
