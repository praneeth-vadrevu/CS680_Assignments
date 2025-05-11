package umbcs680.state;

public class CodeGenerator {
    private String ast;

    public CodeGenerator(String ast) {
        this.ast = ast;
    }

    public String generateCode() {
        if (ast.contains("CODEGEN_ERROR")) {
            return "ERROR";
        }
        return "COMPILED: " + ast.substring(5);
    }
}

