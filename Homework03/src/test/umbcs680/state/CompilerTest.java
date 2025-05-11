package umbcs680.state;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompilerTest {

    @Test
    public void testInitialStateIsLexingAndParsing() {
        Compiler compiler = new Compiler("print('Hello')");
        assertTrue(compiler.getState() instanceof LexingAndParsingState, "Initial state should be LexingAndParsingState");
    }

    @Test
    public void testStateTransition() {
        Compiler compiler = new Compiler("print('Hello')");
        CompilerState newState = new CompleteState();
        compiler.setState(newState);
        assertSame(newState, compiler.getState(), "State should be updated to CompleteState");
    }

    @Test
    public void testGettersAndSetters() {
        Compiler compiler = new Compiler("source code");

        compiler.setSourceCode("updated source");
        assertEquals("updated source", compiler.getSourceCode());

        compiler.setInterCode("intermediate code");
        assertEquals("intermediate code", compiler.getInterCode());

        compiler.setCompOutput("compiled output");
        assertEquals("compiled output", compiler.getCompOutput());

        compiler.setErrorMsg("error message");
        assertEquals("error message", compiler.getErrorMsg());
    }

    @Test
    public void testExecutionDelegation() {
        Compiler compiler = new Compiler("print('Hello')");
        CompleteState completeState = new CompleteState();
        compiler.setState(completeState);

        compiler.execute();
        assertSame(completeState, compiler.getState(), "State should remain CompleteState after execution");
    }
}
