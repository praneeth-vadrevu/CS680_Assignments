package umbcs680.state;

public class Compiler {
    private CompilerState currentState;
    private String sourceCode;
    private String interCode;
    private String compOutput;
    private String errorMsg;

    public Compiler(String sourceCode) {
        this.sourceCode = sourceCode;
        this.currentState = new LexingAndParsingState();
    }

    public void setState(CompilerState state) {
        System.out.println("Transitioning to state: " + state.getClass().getSimpleName());
        this.currentState = state;
    }

    public void execute() {
        currentState.execute(this);
    }

    // Getters and Setters
    public String getSourceCode() { return sourceCode; }
    public void setSourceCode(String sourceCode) { this.sourceCode = sourceCode; }
    public String getInterCode() { return interCode; }
    public void setInterCode(String interCode) { this.interCode = interCode; }
    public String getCompOutput() { return compOutput; }
    public void setCompOutput(String compOutput) { this.compOutput = compOutput; }
    public String getErrorMsg() { return errorMsg; }
    public void setErrorMsg(String errorMsg) { this.errorMsg = errorMsg; }
    public CompilerState getState() { return currentState; }
}
