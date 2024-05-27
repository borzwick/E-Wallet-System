import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JFrame {
    public SplashScreen() {
        // Set up the splash screen window
        setTitle("Splash Screen");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setUndecorated(true); // Remove window borders

        // Load the logo image
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\Chooey\\Downloads\\E-Wallet-System-main\\ewallet\\ewalletlogo.png"); // file name of the logo
        Image logoImage = logoIcon.getImage();
        Image scaledLogoImage = logoImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(scaledLogoImage);
        JLabel logoLabel = new JLabel(scaledLogoIcon);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(logoLabel, BorderLayout.CENTER);

        // Make the splash screen visible
        setVisible(true);

        // Create a timer to close the splash screen after a delay and show the login window
        Timer timer = new Timer(3000, e -> {
            dispose(); // Close the splash screen
            new LoginWindow(); // Open the login window
        });
        timer.setRepeats(false); // Only execute once
        timer.start(); // Start the timer
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SplashScreen::new);
    }
}
