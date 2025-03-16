package state;
import main.Compiler;

public class LexingAndParsingState implements CompilerState {
    @Override
    public void execute(Compiler compiler) {
        System.out.println("Lexing and Parsing...");
        String source = compiler.getSourceCode();   // Simplified name

        if (source == null || source.isEmpty()) {
            reportError(compiler, "Source code is empty!", compiler);
            return;
        }

        // **Simulated Lexing and Parsing (Enhanced OOP)**
        Lexer lexer = new Lexer(source);
        String tokens = lexer.lex();  // Lexer returns Tokens

        if (tokens == null || tokens.isEmpty() || tokens.contains("ERROR")){ //Check for lexing errors.
            reportError(compiler, "Lexing error occurred",compiler);
            return;
        }

        Parser parser = new Parser(tokens);
        String ast = parser.parse();                // Parser returns an AST

        if (ast == null || ast.isEmpty() || ast.contains("ERROR")) { //Check for parsing errors.
            reportError(compiler, "Parsing error occurred", compiler);
            return;
        }

        System.out.println("Lexing and Parsing complete. AST: " + ast);
        compiler.setInterCode(ast); // Pass AST to next state
        compiler.setState(new CodeGenerationState());
    }

    private void reportError(Compiler compiler, String message, Compiler compiler1) {
        System.err.println("Lexing and Parsing Error: " + message);
        compiler.setErrorMsg(message);  // Simplified name
        compiler.setState(new ErrorState());
    }
}

// **Simplified Lexer and Parser classes for example purpose (SRP)**
class Lexer {
    private String sourceCode;

    public Lexer(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String lex() {
        // **VERY Simplified Tokenization**
        if(sourceCode.contains("LEXING_ERROR")) {
            return "ERROR";
        }
        return "TOKENS: " + sourceCode.toUpperCase();
    }
}

class Parser {
    private String tokens;

    public Parser(String tokens) {
        this.tokens = tokens;
    }

    public String parse() {
        // **VERY Simplified Parsing**

        if(tokens.contains("ERROR")){//Simulate a parsing error.
            return "ERROR"; //error
        }

        return "AST: " + tokens.substring(7);
    }
}