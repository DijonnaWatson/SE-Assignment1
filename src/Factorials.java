package com.example.factorial;

import java.util.Scanner;

public class Factorials {

  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);

        // Get user input
    System.out.println("Compute factorial of a number between 1 and 20: ");
    int number = keyboard.nextInt();
        
        // Check for valid input
    while (number < 1 || number > 20) {
      System.out.println("Your number is out of range. Please enter a number between 1 and 20: ");
      number = keyboard.nextInt();
    }
        
        // Initialize computation component
    ComputeFactorial calculator = new CalculatorFac();
        
        // Compute and display result
    long result = calculator.calculateFactorial(number);
    System.out.println("The factorial of " + number + " is: " + result);
        
    keyboard.close();
  }
}
