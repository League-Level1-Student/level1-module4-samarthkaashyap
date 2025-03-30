package _10_slot_machine;

import javax.swing.*;

import _10_slot_machine.SlotMachine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;

public class SlotMachine extends JFrame {
    private JLabel reel1, reel2, reel3;
    private JLabel winCounterLabel;
    private int winCounter = 0;
    private String[] images = {"optical1.png", "Orange-512.webp", "Gambling_lemon-512.webp"}; // Replace with your image file names
    private Random random = new Random();
    public SlotMachine() {
        setTitle("Slot Machine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(400, 300);

        // Create the reels
        JPanel reelsPanel = new JPanel();
        reelsPanel.setLayout(new GridLayout(1, 3));
        reel1 = createLabelImage(images[0]);
        reel2 = createLabelImage(images[1]);
        reel3 = createLabelImage(images[2]);
        reelsPanel.add(reel1);
        reelsPanel.add(reel2);
        reelsPanel.add(reel3);
        add(reelsPanel, BorderLayout.CENTER);

        // Create the spin button
        JButton spinButton = new JButton("SPIN");
        spinButton.addActionListener(new SpinButtonListener());
        add(spinButton, BorderLayout.SOUTH);

        // Create the win counter
        winCounterLabel = new JLabel("Wins: 0");
        winCounterLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(winCounterLabel, BorderLayout.NORTH);

        setVisible(true);
    }

    private JLabel createLabelImage(String fileName) {
        URL imageURL = getClass().getResource(fileName);
        if (imageURL == null) {
            System.err.println("Could not find image " + fileName);
            return new JLabel();
        }
        Icon icon = new ImageIcon(imageURL);
        return new JLabel(icon);
    }

    private class SpinButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Randomize the reels
            String img1 = images[random.nextInt(images.length)];
            String img2 = images[random.nextInt(images.length)];
            String img3 = images[random.nextInt(images.length)];
            reel1.setIcon(new ImageIcon(getClass().getResource(img1)));
            reel2.setIcon(new ImageIcon(getClass().getResource(img2)));
            reel3.setIcon(new ImageIcon(getClass().getResource(img3)));

            // Check if all three images match
            if (img1.equals(img2) && img2.equals(img3)) {
                winCounter++;
                JOptionPane.showMessageDialog(SlotMachine.this, "YOU WIN!");
                winCounterLabel.setText("Wins: " + winCounter);
            }
        }
    }

    public static void main(String[] args) {
    	SwingUtilities.invokeLater(SlotMachine::new);
    }
}
