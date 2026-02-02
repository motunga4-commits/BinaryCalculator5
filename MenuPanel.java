import javax.swing.*;
import java.awt.*;

/**
 * Menu Panel - Shows a hamburger menu icon and a "Programmer" title.
 */
public class MenuPanel extends JPanel {

    public MenuPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        setPreferredSize(new Dimension(350, 50));

        // Add the hamburger menu icon on the left
        JLabel menuIcon = new JLabel("☰");
        menuIcon.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        menuIcon.setForeground(new Color(100, 100, 100));
        add(menuIcon, BorderLayout.WEST);

        // Toss the title in the center
        JLabel title = new JLabel("Programmer");
        title.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        add(title, BorderLayout.CENTER);
    }
}