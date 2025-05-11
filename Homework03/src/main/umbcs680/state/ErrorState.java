package umbcs680.state;

public class ErrorState implements CompilerState {
    @Override
    public void execute(Compiler compiler) {
        System.err.println("Compilation Failed: " + compiler.getErrorMsg());
    }
}
