package org.example;

import org.example.movie.MovieOperator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        runMovieOperator(scanner);
    }

    public static void runMovieOperator(Scanner scanner){
        new MovieOperator(scanner);
    }
}