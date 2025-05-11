package umbcs680.state;

public class CodeGenerationState implements CompilerState {
    @Override
    public void execute(Compiler compiler) {
        System.out.println("Code Generation...");
        String ast = compiler.getInterCode();

        if (ast == null || ast.isEmpty() || ast.contains("ERROR")) {
            reportError(compiler, "No AST for code generation!");
            return;
        }

        CodeGenerator codeGenerator = new CodeGenerator(ast);
        String compiled = codeGenerator.generateCode();

        if (compiled == null || compiled.isEmpty() || compiled.contains("ERROR")) {
            reportError(compiler, "Code Generation Failed");
            return;
        }

        System.out.println("Code Generation complete. Compiled output: " + compiled);
        compiler.setCompOutput(compiled);
        compiler.setState(new CompleteState());
    }

    private void reportError(Compiler compiler, String message) {
        System.err.println("Code Generation Error: " + message);
        compiler.setErrorMsg(message);
        compiler.setState(new ErrorState());
    }
}

