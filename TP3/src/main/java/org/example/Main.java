package org.example;


import org.example.Model.Libro;
import org.example.Util.UniqueISBNGenerator;
import org.example.Enum.Estado;

public class Main {
    public static void main(String[] args) {
        UniqueISBNGenerator isbnGenerator = new UniqueISBNGenerator();
        Libro l1 = new Libro(isbnGenerator.generateUniqueISBN13(), "Harry potter", "J.K Rowling");
        System.out.println(l1.toString());
    }
}