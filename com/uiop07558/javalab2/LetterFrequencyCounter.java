package com.uiop07558.javalab2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LetterFrequencyCounter {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the name of the input text file: ");
    String inputFileName = scanner.nextLine();
    System.out.print("Enter the name of the output text file: ");
    String outputFileName = scanner.nextLine();

    File inputFile = new File(inputFileName);
    File outputFile = new File(outputFileName);

    if (!inputFile.exists()) {
      System.out.println("Input file does not exist.");
      scanner.close();
      return;
    }

    Map<Character, Integer> letterFrequency = new HashMap<>();

    for (char c = 'a'; c <= 'z'; c++) {
      letterFrequency.put(c, 0);
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
      int ch;
      while ((ch = reader.read()) != -1) {
        char character = Character.toLowerCase((char) ch);
        if (character >= 'a' && character <= 'z') {
          letterFrequency.put(character, letterFrequency.get(character) + 1);
        }
      }
    } 
    catch (IOException e) {
      System.out.println("Error reading the input file.");
      scanner.close();
      return;
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
      for (char c = 'a'; c <= 'z'; c++) {
        if (letterFrequency.get(c) > 0) {
          writer.write(c + ": " + letterFrequency.get(c));
          writer.newLine();
        }
      }
      System.out.println("Letter frequencies have been written to " + outputFileName);
    } catch (IOException e) {
      System.out.println("Error writing to the output file.");
    }

    scanner.close();
  }
}