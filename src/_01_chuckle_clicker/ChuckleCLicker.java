package _01_chuckle_clicker;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ChuckleCLicker implements ActionListener{
	JButton trickButton = new JButton("Trick");
    JButton treatButton = new JButton("Treat");
    
    // Method to show picture from the internet
    private void showPictureFromTheInternet(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            Icon icon = new ImageIcon(url);
            JLabel imageLabel = new JLabel(icon);
            JFrame frame = new JFrame();
            frame.add(imageLabel);
            frame.setVisible(true);
            frame.pack();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    // Method to create the UI
    private void createUI() {
        JFrame frame = new JFrame("Trick or Treat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setLayout(new FlowLayout());

        

        // Adding ActionListeners to the buttons
        trickButton.addActionListener(this);

        treatButton.addActionListener(this);

        frame.add(trickButton);
        frame.add(treatButton);

        frame.setVisible(true);
    }

    // Main method to run the application
    public static void main(String[] args) {
 
            
            
           new ChuckleCLicker().createUI();
            
        
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton pressed = (JButton) e.getSource();
		if (pressed == trickButton) {
			showPictureFromTheInternet("https://media.istockphoto.com/id/1971124912/photo/golden.jpg?s=2048x2048&w=is&k=20&c=eiEOcEnNrBdH_6K2jf6VmLVuT5cOOxWyL13t2fovKT0=");

		}
		if (pressed == treatButton) {
			showPictureFromTheInternet("https://i.guim.co.uk/img/media/f1cf534836a8499ba2c0deff509241428abea9ea/28_0_803_482/master/803.jpg?width=465&dpr=1&s=none");
		}
		
	}
}