package _04_book_of_illusions;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BookOfIllusions extends MouseAdapter {

	// 1. Make a JFrame variable and initialize it
	JFrame frame = new JFrame();
	JLabel imageLabel;
	String image1 = "optical1.png"; // Make sure these images are in the _04_book_of_illusions folder
	String image2 = "optical1.png"; // Change these file names to match your image names
	boolean showingFirstImage = true;

	public void run() {
		// 2. Make the frame visible
		frame.setVisible(true);
		
		// 3. Set the size of the frame
		frame.setSize(800, 600); // Adjust this size as needed
		
		// 4. Find 2 images and save them to your project’s _04_book_of_illusions folder
		// (Assume images are already added in the correct location)

		// 5. Make a variable to hold the location of your image
		// This was done above with `image1` and `image2`

		// 6. Create a variable of type "JLabel" but don’t initialize it yet
		// `imageLabel` is created above but not initialized yet

		// 7. Use the "loadImageFromComputer" method to initialize your JLabel
		imageLabel = loadImageFromComputer(image1);

		// 8. Add your JLabel to the frame
		frame.add(imageLabel);

		// 9. Call the pack() method on the frame
		frame.pack();

		// 10. Add a mouse listener to your frame (hint: use *this*)
		frame.addMouseListener(this);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// 11. Print "clicked!" to the console when the mouse is pressed
		System.out.println("clicked!");

		// 12. Remove everything from the frame that was added earlier
		frame.getContentPane().removeAll();

		// 13. Load a new image like before (toggle between the two images)
		if (showingFirstImage) {
			imageLabel = loadImageFromComputer(image2);
		} else {
			imageLabel = loadImageFromComputer(image1);
		}
		showingFirstImage = !showingFirstImage; // Toggle the image

		// 8. Add your JLabel to the frame again
		frame.add(imageLabel);

		// 14. Pack the frame to resize it to fit the new image
		frame.pack();
	}

	// Method to load an image from the computer
	public JLabel loadImageFromComputer(String fileName) {
		URL imageURL = getClass().getResource(fileName);
		Icon icon = new ImageIcon(imageURL);
		return new JLabel(icon);
	}
}