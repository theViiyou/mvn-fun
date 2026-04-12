package de.factorizer;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello Factorizer!");
        Factorizer factorizer = Factorizer.getInstance();
        factorizer.run(args);
    }
}
