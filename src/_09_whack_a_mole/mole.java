package _09_whack_a_mole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class mole implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private int molePosition;
    private int successfulWhacks = 0;
    private int misses = 0;
    private final int TOTAL_MOLES = 24;
    private final Random random = new Random();

    public static void main(String[] args) {
        new mole().startGame();
    }

    public void startGame() {
        frame = new JFrame("Whack-A-Mole");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        molePosition = random.nextInt(TOTAL_MOLES);
        drawButtons(molePosition);

        frame.setVisible(true);
    }

    private void drawButtons(int molePosition) {
        frame.getContentPane().removeAll();

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 6));

        for (int i = 0; i < TOTAL_MOLES; i++) {
            JButton button = new JButton();
            if (i == molePosition) {
                button.setText("Mole!");
            }
            button.addActionListener(this);
            panel.add(button);
        }

        frame.add(panel);
        frame.setSize(600, 400);
        frame.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        if ("Mole!".equals(clickedButton.getText())) {
            successfulWhacks++;
//            playSound("whack.wav"); // Replace with actual sound file if available
        } else {
            misses++;
            speak("You missed!");
        }

        if (successfulWhacks == 10) {
            endGame("You won! Great job!");
        } else if (misses == 5) {
            endGame("You lost! Better luck next time.");
        } else {
            molePosition = random.nextInt(TOTAL_MOLES);
            drawButtons(molePosition);
        }
    }

    private void playSound(String soundFile) {
        // Placeholder for playing a sound
        System.out.println("Play sound: " + soundFile);
    }

    private void speak(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    private void endGame(String message) {
        JOptionPane.showMessageDialog(frame, message + "\nYour whack rate: " + (successfulWhacks / (double) (successfulWhacks + misses) * 100) + "%");
        frame.dispose();
    }
}
