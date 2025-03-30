package _07_tv_show_episode_info;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TVShowEpisodeInfoDisplayer {

    public TVShowEpisodeInfoDisplayer() {
        // Create a JFrame
        JFrame frame = new JFrame("TV Show Info");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);

        // Create a JPanel to hold the components
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        // Set frame visibility
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Label for input
        JLabel userLabel = new JLabel("Enter TV Show:");
        userLabel.setBounds(10, 20, 100, 25);
        panel.add(userLabel);

        // Text field for input
        JTextField showField = new JTextField(20);
        showField.setBounds(120, 20, 200, 25);
        panel.add(showField);

        // Button to submit
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, 60, 100, 25);
        panel.add(submitButton);

        // Action listener for the button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String showName = showField.getText();
                if (!showName.isEmpty()) {
                    String showData = getShowEpisodeData(showName);
                    if (showData.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Show not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, showData, "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a TV show name.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

	
	
	

/////////////////////////DO NOT MODIFY ANY CODE BELOW THIS LINE//////////////////////////////////////////
	
	/**
	 * Searches the tvmaze.com api for season and episode information about
	 * a chosen show and returns the information in a String
	 * 
	 * @param showTitle	the name of the show to get information about
	 * @return 			a String containing the show information or a blank
	 * 					String if the show is not found
	 */
	public String getShowEpisodeData(String showTitle) {
		int id = WebUtilities.getShowId(showTitle);
		if(id < 0) {
			return "";
		}
		
		int totalSeasons = 0;
		int totalEpisodes = 0;
		int[] episodes = null;
		
		try {
			URL url = new URL("https://api.tvmaze.com/shows/"+id+"/seasons");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			InputStream responseStream = connection.getInputStream();
			String in = WebUtilities.getStringFromInputStream(responseStream);
			String[] info = WebUtilities.parseJSONArray(in);
			totalSeasons = info.length;
			episodes = new int[totalSeasons];
			boolean success = true;
			for(int i = 0; i < totalSeasons; i++) {
				try {
					episodes[i] = WebUtilities.getIntFromJSONObject(info[i], "episodeOrder");
				}catch(Exception e) {
					episodes[i] = -1;
					success = false;
				}
				totalEpisodes += episodes[i];
			}
			if(!success) {
				JOptionPane.showMessageDialog(null, "Could not retrieve some or all of the episode data.", "Data Not Available", 0);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String res = showTitle + "\nTotal Seasons: " + totalSeasons + "\nTotal Episodes: " + totalEpisodes + '\n';
		for(int i = 0; i < totalSeasons; i++) {
			res += "Season " + (i + 1) +": " + (episodes[i] > -1 ? episodes[i] : "?") + " episodes\n";
		}
		
		return res;
	}
}
