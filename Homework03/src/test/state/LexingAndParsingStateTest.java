import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.Compiler;
import state.*;

public class LexingAndParsingStateTest {

    @Test
    public void testLexingAndParsingSuccessful() {
        String sourceCode = "int x = 10;";
        Compiler compiler = new Compiler(sourceCode);
        LexingAndParsingState lexingAndParsingState = new LexingAndParsingState();
        lexingAndParsingState.execute(compiler);

        // Verify that the state transitioned to CodeGenerationState
        assertTrue(compiler.getState() instanceof CodeGenerationState);

        // Verify that the intermediate code (AST) is not null
        assertNotNull(compiler.getInterCode());
    }

    @Test
    public void testLexingAndParsingEmptySourceCode() {
        String sourceCode = "";
        Compiler compiler = new Compiler(sourceCode);
        LexingAndParsingState lexingAndParsingState = new LexingAndParsingState();
        lexingAndParsingState.execute(compiler);

        // Verify that the state transitioned to ErrorState
        assertTrue(compiler.getState() instanceof ErrorState);
        assertNotNull(compiler.getErrorMsg());
    }

    @Test
    public void testLexingAndParsingError() {
        String sourceCode = "LEXING_ERROR";
        Compiler compiler = new Compiler(sourceCode);
        LexingAndParsingState lexingAndParsingState = new LexingAndParsingState();
        lexingAndParsingState.execute(compiler);

        // Verify that the state transitioned to ErrorState
        assertTrue(compiler.getState() instanceof ErrorState);
        assertNotNull(compiler.getErrorMsg());
    }
}