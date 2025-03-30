package _11_lights_out;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class LightsOut implements MouseListener {

    JPanel gamePanel = new JPanel();
    JFrame frame = new JFrame("Lights Out");

    public LightsOut() {
        gamePanel.setLayout(new GridLayout(5, 5));

        for (int i = 0; i < 25; i++) {
            JLabel light = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            light.setOpaque(true);
            light.setBackground(Color.LIGHT_GRAY);
            light.setPreferredSize(new Dimension(60, 60));
            light.addMouseListener(this);
            gamePanel.add(light);
        }

        frame.add(gamePanel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        randomizeBoard();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel clickedLight = (JLabel) e.getSource();
        int pos = Integer.parseInt(clickedLight.getText());
        makeMove(pos);
        checkWin();
    }

    void makeMove(int pos) {
        toggle(getLightAtPosition(pos));
        if (pos >= 5) toggle(getLightAtPosition(pos - 5));
        if ((pos + 1) % 5 != 0) toggle(getLightAtPosition(pos + 1));
        if (pos % 5 != 0) toggle(getLightAtPosition(pos - 1));
        if (pos + 5 <= 24) toggle(getLightAtPosition(pos + 5));
    }

    JLabel getLightAtPosition(int lightPosition) {
        return (JLabel) gamePanel.getComponent(lightPosition);
    }

    void toggle(JLabel label) {
        label.setBackground(label.getBackground() == Color.WHITE ? Color.LIGHT_GRAY : Color.WHITE);
    }

    void checkWin() {
        for (Component c : gamePanel.getComponents()) {
            if (c.getBackground() == Color.LIGHT_GRAY) return;
        }
        JOptionPane.showMessageDialog(frame, "You won!");
    }

    void randomizeBoard() {
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            makeMove(rand.nextInt(25));
        }
    }

    public static void main(String[] args) {
        new LightsOut();
    }

    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
}
