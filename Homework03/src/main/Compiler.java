package main;
import state.*;

public class Compiler {
    private CompilerState currentState;  // Simplified name
    private String sourceCode;
    private String interCode;           // Simplified name
    private String compOutput;          // Simplified name
    private String errorMsg;            // Simplified name

    public Compiler(String sourceCode) {
        this.sourceCode = sourceCode;
        this.currentState = new LexingAndParsingState(); // Start state
    }

    public void setState(CompilerState state) {
        System.out.println("Transitioning to state: " + state.getClass().getSimpleName());
        this.currentState = state;
    }

    public void execute() {
        currentState.execute(this); // Delegate to state
    }

    // Getters and Setters - Encapsulation
    public String getSourceCode() { return sourceCode; }
    public void setSourceCode(String sourceCode) {this.sourceCode = sourceCode;}
    public String getInterCode() { return interCode; }
    public void setInterCode(String interCode) { this.interCode = interCode; }
    public String getCompOutput() { return compOutput; }
    public void setCompOutput(String compOutput) {this.compOutput = compOutput; }
    public String getErrorMsg() { return errorMsg; }
    public void setErrorMsg(String errorMsg) { this.errorMsg = errorMsg; }

    public CompilerState getState() { return currentState; } //for test usage

}