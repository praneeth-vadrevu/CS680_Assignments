package umbcs680.state;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ErrorStateTest {

    @Test
    public void testErrorStatePreservesItself() {
        Compiler compiler = new Compiler("Some invalid code");
        compiler.setErrorMsg("Syntax error detected");
        compiler.setState(new ErrorState());

        CompilerState initialState = compiler.getState();  // Capture state before execution
        compiler.execute();
        CompilerState finalState = compiler.getState();    // Capture state after execution

        // Validate that the state did not change after execution
        assertSame(initialState, finalState, "State should remain ErrorState after execution");
    }
}
