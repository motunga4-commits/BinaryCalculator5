import javax.swing.*;
import java.awt.*;

/**
 * DisplayPanel handles showing the main calculation and its conversions.
 */
public class DisplayPanel extends JPanel {
    private JLabel mainDisplay;
    private JLabel hexLabel;
    private JLabel decLabel;
    private JLabel octLabel;
    private JLabel binLabel;

    public DisplayPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));
        setPreferredSize(new Dimension(350, 140));

        // Main number at the top right
        JPanel mainDisplayPanel = new JPanel(new BorderLayout());
        mainDisplayPanel.setBackground(Color.WHITE);
        mainDisplayPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        mainDisplay = new JLabel("0", SwingConstants.RIGHT);
        mainDisplay.setFont(new Font("Segoe UI", Font.BOLD, 48));
        mainDisplay.setForeground(Color.BLACK);
        mainDisplayPanel.add(mainDisplay, BorderLayout.EAST);

        add(mainDisplayPanel);
        add(Box.createVerticalStrut(10));

        // Panel for HEX, DEC, OCT, BIN
        JPanel numberSystemsPanel = new JPanel(new GridLayout(4, 2, 10, 3));
        numberSystemsPanel.setBackground(Color.WHITE);
        numberSystemsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 11);
        Color labelColor = new Color(100, 100, 100);

        // HEX
        JLabel hexTitleLabel = new JLabel("HEX");
        hexTitleLabel.setFont(labelFont);
        hexTitleLabel.setForeground(labelColor);
        hexLabel = new JLabel("0", SwingConstants.RIGHT);
        hexLabel.setFont(labelFont);
        hexLabel.setForeground(Color.BLACK);
        numberSystemsPanel.add(hexTitleLabel);
        numberSystemsPanel.add(hexLabel);

        // DEC
        JLabel decTitleLabel = new JLabel("DEC");
        decTitleLabel.setFont(labelFont);
        decTitleLabel.setForeground(labelColor);
        decLabel = new JLabel("0", SwingConstants.RIGHT);
        decLabel.setFont(labelFont);
        decLabel.setForeground(Color.BLACK);
        numberSystemsPanel.add(decTitleLabel);
        numberSystemsPanel.add(decLabel);

        // OCT
        JLabel octTitleLabel = new JLabel("OCT");
        octTitleLabel.setFont(labelFont);
        octTitleLabel.setForeground(labelColor);
        octLabel = new JLabel("0", SwingConstants.RIGHT);
        octLabel.setFont(labelFont);
        octLabel.setForeground(Color.BLACK);
        numberSystemsPanel.add(octTitleLabel);
        numberSystemsPanel.add(octLabel);

        // BIN
        JLabel binTitleLabel = new JLabel("BIN");
        binTitleLabel.setFont(labelFont);
        binTitleLabel.setForeground(labelColor);
        binLabel = new JLabel("0", SwingConstants.RIGHT);
        binLabel.setFont(labelFont);
        binLabel.setForeground(Color.BLACK);
        numberSystemsPanel.add(binTitleLabel);
        numberSystemsPanel.add(binLabel);

        add(numberSystemsPanel);
    }

    public void updateDisplay(String value) {
        mainDisplay.setText(value);
    }

    public void updateNumberSystems(long value) {
        hexLabel.setText(Long.toHexString(value).toUpperCase());
        decLabel.setText(String.valueOf(value));
        octLabel.setText(Long.toOctalString(value));
        binLabel.setText(Long.toBinaryString(value));
    }
}