package _06_calculator;

public class CalcRunner {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        // Testing addition
        System.out.println("Addition: " + calculator.add(10, 5));

        // Testing subtraction
        System.out.println("Subtraction: " + calculator.subtract(10, 5));

        // Testing multiplication
        System.out.println("Multiplication: " + calculator.multiply(10, 5));

        // Testing division
        try {
            System.out.println("Division: " + calculator.divide(10, 5));
            System.out.println("Division by zero: " + calculator.divide(10, 0));
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}