package de.factorizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class FactorizerImpl implements Factorizer {

    // 1 lazy Singleton not initialized
    private static FactorizerImpl instance;

    // Mapping für Zahlwörter
    private static final Map<String, Long> NUMBER_WORDS = new HashMap<>();
    static {
        NUMBER_WORDS.put("null", 0L);
        NUMBER_WORDS.put("eins", 1L);
        NUMBER_WORDS.put("zwei", 2L);
        NUMBER_WORDS.put("drei", 3L);
        NUMBER_WORDS.put("sieben", 7L);
        NUMBER_WORDS.put("neun", 9L);
        NUMBER_WORDS.put("zehn", 10L);
    }

    // 2 Privaten Konstruktor
    private FactorizerImpl() {
    }

    // 3 getInstance Methode erzeugt und gibt die lazy Singleton-Instanz aus
    static synchronized Factorizer getInstance() {
        if (instance == null) {
            instance = new FactorizerImpl();
        }
        return instance;
    }

    @Override
    public void run(String[] args) {
        Stream.of(args)
                .forEach(arg -> {
                    try {
                        Long n = parseArg(arg);
                        List<Long> factors = factorize(n);

                        String primeSuffix = factors.size() == 1 ? " (prime number)" : "";
                        System.out.println(
                                String.format(" -n=%d -> %s%s", n, factors, primeSuffix));

                    } catch (NumberFormatException e) {
                        System.err.println("Invalid number: " + args);
                    }
                });
    }

    // Hilfsmethode zum Parsen von Zahl oder Wort
    private Long parseArg(String arg) {
        String lowerArg = arg.toLowerCase();
        if (NUMBER_WORDS.containsKey(lowerArg)) {
            return NUMBER_WORDS.get(lowerArg);
        }
        return Long.parseLong(arg);
    }

    @Override
    public List<Long> factorize(Long n) {
        List<Long> factors = new ArrayList<>();
        long temp = n;
        if (n < 0) {
            throw new IllegalArgumentException("illegal negative parameter: n");
        }
        // 0 und 1 Ausschluss da keine primes
        if (n < 2) {
            return factors;
        }
        for (long i = 2; i * i <= temp; i++) {
            while (temp % i == 0) {
                factors.add(i);
                temp /= i;
            }
        }
        if (temp > 1) {
            factors.add(temp);
        }
        return factors;
    }
}
