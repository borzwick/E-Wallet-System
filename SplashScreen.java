////// SPLASH SCREEN CLASS----------------------------------------------------------------------------------------------------------------------------------

import javax.swing.*;
import java.awt.*;

// This class represents a splash screen that appears when the application starts
public class SplashScreen extends JFrame {

    // Constructor for the SplashScreen class
    public SplashScreen() {
        // Set up the splash screen window
        setTitle("Splash Screen"); // Set the title of the window
        setSize(400, 300); // Set the size of the window
        setLocationRelativeTo(null); // Center the window on the screen
        setUndecorated(true); // Remove window borders and title bar
        getContentPane().setBackground(Color.WHITE); // Set the background color of the window to white

        // Load the logo image
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\ACER\\Desktop\\CMPSC 113\\e-Wallet\\coarta1.png"); // insert directory of logo here
        Image logoImage = logoIcon.getImage(); // // Get the image from the ImageIcon
        Image scaledLogoImage = logoImage.getScaledInstance(350, 350, Image.SCALE_SMOOTH); // Scale the image to fit the window
        ImageIcon scaledLogoIcon = new ImageIcon(scaledLogoImage); // Create a new ImageIcon from the scaled image
        JLabel logoLabel = new JLabel(scaledLogoIcon); // Create a JLabel to display the logo
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the logo in the JLabel
        add(logoLabel, BorderLayout.CENTER); // Add the logo to the center of the window

        // Make the splash screen visible
        setVisible(true);

        // Create a timer to close the splash screen after a delay and show the login window
        Timer timer = new Timer(2000, e -> { // Set a 2-second delay
            dispose(); // Close the splash screen
            new LoginWindow(); // Open the login window
        });
        timer.setRepeats(false); // Only execute once
        timer.start(); // Start the timer
    }
}