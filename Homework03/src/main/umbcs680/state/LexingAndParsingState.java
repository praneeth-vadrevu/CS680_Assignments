package umbcs680.state;

public class LexingAndParsingState implements CompilerState {
    @Override
    public void execute(Compiler compiler) {
        System.out.println("Lexing and Parsing...");
        String source = compiler.getSourceCode();

        if (source == null || source.isEmpty()) {
            reportError(compiler, "Source code is empty!");
            return;
        }

        Lexer lexer = new Lexer(source);
        String tokens = lexer.lex();

        if (tokens == null || tokens.isEmpty() || tokens.contains("ERROR")) {
            reportError(compiler, "Lexing error occurred");
            return;
        }

        Parser parser = new Parser(tokens);
        String ast = parser.parse();

        if (ast == null || ast.isEmpty() || ast.contains("ERROR")) {
            reportError(compiler, "Parsing error occurred");
            return;
        }

        System.out.println("Lexing and Parsing complete. AST: " + ast);
        compiler.setInterCode(ast);
        compiler.setState(new CodeGenerationState());
    }

    private void reportError(Compiler compiler, String message) {
        System.err.println("Lexing and Parsing Error: " + message);
        compiler.setErrorMsg(message);
        compiler.setState(new ErrorState());
    }
}

