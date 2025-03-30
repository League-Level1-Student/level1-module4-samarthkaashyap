package _06_calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcUi extends JFrame {
    private Calculator calculator = new Calculator();

    public CalcUi() {
        // Create UI components
        JTextField num1Field = new JTextField(10);
        JTextField num2Field = new JTextField(10);
        JLabel resultLabel = new JLabel("Result: ");

        JButton addButton = new JButton("Add");
        JButton subtractButton = new JButton("Subtract");
        JButton multiplyButton = new JButton("Multiply");
        JButton divideButton = new JButton("Divide");

        // Create a panel for inputs and buttons
        JPanel panel = new JPanel();
        panel.add(new JLabel("Number 1:"));
        panel.add(num1Field);
        panel.add(new JLabel("Number 2:"));
        panel.add(num2Field);

        // Add buttons to panel
        panel.add(addButton);
        panel.add(subtractButton);
        panel.add(multiplyButton);
        panel.add(divideButton);
        panel.add(resultLabel);

        // Action listeners for each button
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double num1 = Double.parseDouble(num1Field.getText());
                double num2 = Double.parseDouble(num2Field.getText());
                double result = calculator.add(num1, num2);
                resultLabel.setText("Result: " + result);
            }
        });

        subtractButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double num1 = Double.parseDouble(num1Field.getText());
                double num2 = Double.parseDouble(num2Field.getText());
                double result = calculator.subtract(num1, num2);
                resultLabel.setText("Result: " + result);
            }
        });

        multiplyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double num1 = Double.parseDouble(num1Field.getText());
                double num2 = Double.parseDouble(num2Field.getText());
                double result = calculator.multiply(num1, num2);
                resultLabel.setText("Result: " + result);
            }
        });

        divideButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double num1 = Double.parseDouble(num1Field.getText());
                double num2 = Double.parseDouble(num2Field.getText());
                try {
                    double result = calculator.divide(num1, num2);
                    resultLabel.setText("Result: " + result);
                } catch (ArithmeticException ex) {
                    resultLabel.setText("Error: " + ex.getMessage());
                }
            }
        });

        // Setup JFrame
        this.add(panel);
        this.setTitle("Calculator");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new CalcUi();
    }
}