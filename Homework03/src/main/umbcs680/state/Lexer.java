package umbcs680.state;

public class Lexer {

        private String sourceCode;

        public Lexer(String sourceCode) {
            this.sourceCode = sourceCode;
        }

        public String lex() {
            if (sourceCode.contains("LEXING_ERROR")) {
                return "ERROR";
            }
            return "TOKENS: " + sourceCode.toUpperCase();
        }


}
