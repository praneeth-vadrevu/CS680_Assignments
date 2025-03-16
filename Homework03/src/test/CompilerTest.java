import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.Compiler;
import state.*;

public class CompilerTest {

    @Test
    public void testSuccessfulCompilation() {
        String sourceCode = "int x = 10; print(x + 5);";
        Compiler compiler = new Compiler(sourceCode);
        compiler.execute();

        // Verify that the compilation reached the CompleteState
        assertTrue(compiler.getState() instanceof CompleteState);
        assertNull(compiler.getErrorMsg()); //use simplified name
    }

    @Test
    public void testLexingParsingError() {
        String sourceCode = ""; // Empty source code to trigger LexingError
        Compiler compiler = new Compiler(sourceCode);
        compiler.execute();

        // Verify that the compilation reached the ErrorState
        assertTrue(compiler.getState() instanceof ErrorState);
        assertNotNull(compiler.getErrorMsg()); //use simplified name
    }

    @Test
    public void testCodeGenError() {
        String sourceCode = "CODEGEN_ERROR";
        Compiler compiler = new Compiler(sourceCode);
        compiler.execute();

        assertTrue(compiler.getState() instanceof ErrorState);
        assertNotNull(compiler.getErrorMsg()); //use simplified name
    }
}