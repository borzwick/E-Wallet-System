import javax.swing.SwingUtilities;

// The main method to start the application
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SplashScreen::new); // Create and show the splash screen on the Event Dispatch Thread
    }
}
