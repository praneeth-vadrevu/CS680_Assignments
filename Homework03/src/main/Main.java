package main;

import main.Compiler;

public class Main {
    public static void main(String[] args) {
        String sourceCode = "int x = 10; print(x + 5);";
        Compiler compiler = new Compiler(sourceCode);
        compiler.execute();

        if (compiler.getErrorMsg() != null) {
            System.out.println("Compilation failed: " + compiler.getErrorMsg());
        } else {
            System.out.println("Compilation successful. Output: " + compiler.getCompOutput());
        }
    }
}
