import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.Compiler;
import state.*;

public class CodeGenerationStateTest {

    @Test
    public void testCodeGenerationSuccessful() {
        Compiler compiler = new Compiler("valid source");
        compiler.setInterCode("AST: valid ast");
        CodeGenerationState codeGenerationState = new CodeGenerationState();
        codeGenerationState.execute(compiler);

        assertTrue(compiler.getState() instanceof CompleteState);


        assertNotNull(compiler.getCompOutput());
    }

    @Test
    public void testCodeGenerationNoAST() {
        Compiler compiler = new Compiler("valid source");
        compiler.setInterCode("");
        CodeGenerationState codeGenerationState = new CodeGenerationState();
        codeGenerationState.execute(compiler);

        // Verify that the state transitioned to ErrorState
        assertTrue(compiler.getState() instanceof ErrorState);
        assertNotNull(compiler.getErrorMsg());
    }

    @Test
    public void testCodeGenerationError() {
        Compiler compiler = new Compiler("valid source");
        compiler.setInterCode("AST: CODEGEN_ERROR");
        CodeGenerationState codeGenerationState = new CodeGenerationState();
        codeGenerationState.execute(compiler);

        // Verify that the state transitioned to ErrorState
        assertTrue(compiler.getState() instanceof ErrorState);
        assertNotNull(compiler.getErrorMsg());
    }
}