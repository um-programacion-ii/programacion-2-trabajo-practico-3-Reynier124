package org.example.Util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class UniqueISBNGenerator {
    private Set<String> generatedISBNs;
    private Random random;

    public UniqueISBNGenerator() {
        generatedISBNs = new HashSet<>();
        random = new Random();
    }


    public String generateUniqueISBN13() {
        String isbn;
        do {
            isbn = generateISBN13();
        } while (generatedISBNs.contains(isbn));

        generatedISBNs.add(isbn);
        return isbn;
    }

    private String generateISBN13() {
        int[] isbn = new int[13];

        for (int i = 0; i < 12; i++) {
            isbn[i] = random.nextInt(10);
        }

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += (i % 2 == 0) ? isbn[i] : isbn[i] * 3;
        }

        int checksum = 10 - (sum % 10);
        if (checksum == 10) {
            checksum = 0;
        }

        isbn[12] = checksum;

        StringBuilder isbnString = new StringBuilder();
        for (int digit : isbn) {
            isbnString.append(digit);
        }

        return isbnString.toString();
    }
}
