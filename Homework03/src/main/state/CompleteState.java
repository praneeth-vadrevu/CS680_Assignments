package state;
import main.Compiler;


public class CompleteState implements CompilerState {
    @Override
    public void execute(Compiler compiler) {
        System.out.println("Compilation Complete!");
    }
}