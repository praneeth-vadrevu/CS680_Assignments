package umbcs680.state;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CodeGenerationStateTest {

    @Test
    public void testSuccessfulCodeGeneration() {
        Compiler compiler = new Compiler("print('Hello')");
        compiler.setInterCode("AST: print('Hello')"); // Valid AST
        CodeGenerationState codeGenState = new CodeGenerationState();

        codeGenState.execute(compiler);

        assertNotNull(compiler.getCompOutput());
        assertTrue(compiler.getCompOutput().startsWith("COMPILED:"));
        assertTrue(compiler.getState() instanceof CompleteState);
    }

    @Test
    public void testCodeGenerationFailsWithEmptyAst() {
        Compiler compiler = new Compiler("print('Hello')");
        compiler.setInterCode(""); // Invalid AST
        CodeGenerationState codeGenState = new CodeGenerationState();

        codeGenState.execute(compiler);

        assertTrue(compiler.getState() instanceof ErrorState);
        assertEquals("No AST for code generation!", compiler.getErrorMsg());
    }

    @Test
    public void testCodeGenerationFailsWithErrorAst() {
        Compiler compiler = new Compiler("print('Hello')");
        compiler.setInterCode("ERROR"); // Invalid AST with error keyword
        CodeGenerationState codeGenState = new CodeGenerationState();

        codeGenState.execute(compiler);

        assertTrue(compiler.getState() instanceof ErrorState);
        assertEquals("No AST for code generation!", compiler.getErrorMsg());
    }
}
