package _03_typing_tutor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Date;

public class Typing_Tutor implements KeyListener {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel label = new JLabel();
    char currentLetter;
    int correctLetters = 0;
    Date timeAtStart = new Date();

    void setup() {
        // Initialize frame
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize current letter
        currentLetter = generateRandomLetter();
        
        // Setup label to show current letter
        label.setText(String.valueOf(currentLetter));
        label.setFont(label.getFont().deriveFont(28.0f));
        label.setHorizontalAlignment(JLabel.CENTER);

        // Add label to the panel and panel to the frame
        panel.add(label);
        frame.add(panel);

        // Add key listener to frame
        frame.addKeyListener(this);

        // Pack the frame to adjust its size based on its content
        frame.pack();
    }

    // Method to generate a random letter
    char generateRandomLetter() {
        Random r = new Random();
        return (char) (r.nextInt(26) + 'a');
    }

    // Key listener events
    @Override
    public void keyPressed(KeyEvent e) {
        char typedChar = e.getKeyChar();
        System.out.println("Typed: " + typedChar);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char typedChar = e.getKeyChar();
        if (typedChar == currentLetter) {
            System.out.println("Correct!");
            panel.setBackground(Color.GREEN);
            correctLetters++;
        } else {
            panel.setBackground(Color.RED);
        }

        // Generate a new letter and update label
        currentLetter = generateRandomLetter();
        label.setText(String.valueOf(currentLetter));

        // Optional: Show typing speed after a certain number of correct characters
        if (correctLetters == 10) {
            showTypingSpeed(correctLetters);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    // Method to calculate and show typing speed
    private void showTypingSpeed(int numberOfCorrectCharactersTyped) {
        Date timeAtEnd = new Date();
        long gameDuration = timeAtEnd.getTime() - timeAtStart.getTime();
        long gameInSeconds = (gameDuration / 1000) % 60;
        double charactersPerSecond = ((double) numberOfCorrectCharactersTyped / (double) gameInSeconds);
        int charactersPerMinute = (int) (charactersPerSecond * 60);
        JOptionPane.showMessageDialog(null, "Your typing speed is " + charactersPerMinute + " characters per minute.");
    }


}