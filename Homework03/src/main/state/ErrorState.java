package state;
import main.Compiler;
import java.lang.System;

public class ErrorState implements CompilerState {

    @Override
    public void execute(Compiler compiler) {
        System.err.println("Compilation Failed: " + compiler.getErrorMsg()); //use simplified name
    }
}