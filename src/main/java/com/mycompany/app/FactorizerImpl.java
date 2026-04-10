package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class FactorizerImpl implements Factorizer {

    // 1 Singleton Instanz bilden
    private static final FactorizerImpl instance = new FactorizerImpl();

    // 2 Privaten Konstruktor
    private FactorizerImpl() {
    }

    @Override
    public void run(String[] args) {
        Stream.of(args)
                .forEach(arg -> {
                    try {
                        Integer n = Integer.parseInt(arg);
                        List<Integer> factors = factorize(n);

                        String primeSuffix = factors.size() == 1 ? " (prime number)" : "";
                        System.out.println(
                                String.format(" -n=%d -> %s%s", n, factors, primeSuffix));

                    } catch (NumberFormatException e) {
                        System.err.println("Invalid number: " + args);
                    }
                });
    }

    // 3 getInstance Methode gibt die Singleton-Instanz aus
    static Factorizer getInstance() {
        return instance;
    }

    @Override
    public List<Integer> factorize(Integer n) {
        List<Integer> factors = new ArrayList<>();
        int temp = n;
        for (int i = 2; i <= temp; i++) {
            while (temp % i == 0) {
                factors.add(i);
                temp /= i;
            }
        }
        return factors;
    }
}
