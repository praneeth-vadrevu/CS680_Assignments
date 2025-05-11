
package umbcs680.state;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompleteStateTest {

    @Test
    public void testStateRemainsCompleteAfterExecution() {
        Compiler compiler = new Compiler("print('Hello')");
        compiler.setState(new CompleteState());

        compiler.execute();

        // Ensure that the state remains CompleteState after execution
        assertTrue(compiler.getState() instanceof CompleteState);
    }
}

