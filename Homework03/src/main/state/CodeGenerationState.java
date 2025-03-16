package state;

import main.Compiler;

public class CodeGenerationState implements CompilerState {
    @Override
    public void execute(Compiler compiler) {
        System.out.println("Code Generation...");
        String ast = compiler.getInterCode();  // Simplified name

        if (ast == null || ast.isEmpty() || ast.contains("ERROR")) {
            reportError(compiler, "No AST for code generation!", compiler);
            return;
        }

        // **Simulated Code Generation (Enhanced OOP)**
        CodeGenerator codeGenerator = new CodeGenerator(ast);
        String compiled = codeGenerator.generateCode();  // Simplified name

        if(compiled == null || compiled.isEmpty() || compiled.contains("ERROR")) {
            reportError(compiler, "Code Generation Failed", compiler);
            return;
        }

        System.out.println("Code Generation complete. Compiled output: " + compiled);
        compiler.setCompOutput(compiled);  // Simplified name
        compiler.setState(new CompleteState());
    }

    private void reportError(Compiler compiler, String message, Compiler compiler1) {
        System.err.println("Code Generation Error: " + message);
        compiler.setErrorMsg(message);  // Simplified name
        compiler.setState(new ErrorState());
    }
}

// **Simplified CodeGenerator class for example purpose (SRP)**
class CodeGenerator {
    private String ast;

    public CodeGenerator(String ast) {
        this.ast = ast;
    }

    public String generateCode() {
        // **VERY Simplified Code Generation**
        if (ast.contains("CODEGEN_ERROR")) {
            return "ERROR";
        }
        return "COMPILED: " + ast.substring(5);
    }
}