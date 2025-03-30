package _05_fortune_teller;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Random;
import java.util.Scanner;

import game_tools.Sound;

public class FortuneTeller extends JPanel implements Runnable, MouseListener {

    JFrame frame = new JFrame();
    int frameWidth = 500;
    int frameHeight = 500;
    BufferedImage fortuneTellerImage;

    // Constructor
    FortuneTeller() throws Exception {
        // 1. Choose an image for your fortune teller and put it in your default package
        fortuneTellerImage = ImageIO.read(getClass().getResource("fortune teller.png"));
        
        // 2. Adjust the frameWidth and frameHeight variables to fit your image nicely (doesn’t need a new line of code)
        // (Adjust frameWidth and frameHeight above if necessary to fit your image)

        // 3. Complete the begin() method in the FortuneTellerRunner class
        // This instruction refers to a separate class, so we can assume begin() method in that class starts this FortuneTeller thread.

        // 4. Add a mouse listener to the frame
        frame.addMouseListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        
        // 5. Print the mouseX variable
        // 6. Add the mouseY variable to the previous line so that it prints out too (no new line)
        System.out.println("Mouse coordinates: (" + mouseX + ", " + mouseY + ")");

        // 7. Adjust your secret location coordinates here
        int secretLocationX = 250; // Set these coordinates to wherever the "secret location" should be
        int secretLocationY = 250;

        // If the mouse coordinates and secret location are close, allow the user to ask a question.
        if (areClose(mouseX, secretLocationX) && areClose(mouseY, secretLocationY)) {
            // 8. Find a spooky sound and put it in your _05_fortune_teller package (freesound.org)
            // For example, "creepy-noise.wav"

            // 9. Play the sound
            play("creepy-noise.wav");

            // 10. Insert your completed Magic 8 ball code here
            magic8Ball();
        }
    }

    private void magic8Ball() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(4);

        // Get user question
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ask a question for the Magic 8 Ball: ");
        scanner.nextLine(); // Read and ignore the question since we're just simulating the response.

        // Respond based on the random number
        if (randomNumber == 0) {
            System.out.println("Yes");
        } else if (randomNumber == 1) {
            System.out.println("No");
        } else if (randomNumber == 2) {
            System.out.println("Maybe you should ask Google?");
        } else if (randomNumber == 3) {
            System.out.println("I’m not sure, try again later.");
        }
    }

    private boolean areClose(int mouseX, int secretLocationX) {
        return mouseX < secretLocationX + 15 && mouseX > secretLocationX - 15;
    }

    private void pause(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**************** don't worry about the stuff under here *******************/

    @Override
    public void run() {
        frame.add(this);
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static synchronized void play(final String fileName) {
        Sound sound = new Sound("_05_fortune_teller/" + fileName);
        sound.play();
    }

    private void showAnotherImage(String imageName) {
        try {
            fortuneTellerImage = ImageIO.read(getClass().getResource(imageName));
        } catch (Exception e) {
            System.err.println("Couldn't find this image: " + imageName);
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(fortuneTellerImage, 0, 0, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}