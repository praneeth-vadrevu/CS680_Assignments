package umbcs680.state;

public class Parser {
    private String tokens;

    public Parser(String tokens) {
        this.tokens = tokens;
    }

    public String parse() {
        if (tokens.contains("ERROR")) {
            return "ERROR";
        }
        return "AST: " + tokens.substring(7);
    }
}
