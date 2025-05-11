package umbcs680.state;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CodeGeneratorTest {

    @Test
    public void testGenerateCodeSuccess() {
        CodeGenerator generator = new CodeGenerator("AST: valid code");
        String result = generator.generateCode();
        assertEquals("COMPILED: valid code", result);
    }

    @Test
    public void testGenerateCodeFailure() {
        CodeGenerator generator = new CodeGenerator("CODEGEN_ERROR");
        String result = generator.generateCode();
        assertEquals("ERROR", result);
    }
}
