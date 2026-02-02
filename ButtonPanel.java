import javax.swing.*;
import java.awt.*;

/**
 * Button Panel - Handles all the calculator buttons, including the binary ones.
 * This matches the calculator’s full button layout, just like in the reference image.
 */
public class ButtonPanel extends JPanel {
    private CalculatorFrame parent;

    public ButtonPanel(CalculatorFrame parent) {
        this.parent = parent;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(243, 243, 243));
        setBorder(BorderFactory.createEmptyBorder(5, 15, 15, 15));

        // Top row: QWORD, MS, and friends.
        add(createTopButtonRow());
        add(Box.createVerticalStrut(5));

        // Row for dropdowns: bitwise stuff and bit shift.
        add(createDropdownRow());
        add(Box.createVerticalStrut(5));

        // Main grid: 6 rows of calculator buttons.
        add(createButtonGrid());
    }

    private JPanel createTopButtonRow() {
        JPanel panel = new JPanel(new GridLayout(1, 5, 5, 5));
        panel.setBackground(new Color(243, 243, 243));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));

        String[] buttons = {"⊞", "⊟", "QWORD", "MS", "M▼"};

        for (String text : buttons) {
            JButton btn = createStyledButton(text, new Color(250, 250, 250), 11);
            panel.add(btn);
        }

        return panel;
    }

    private JPanel createDropdownRow() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
        panel.setBackground(new Color(243, 243, 243));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));

        // Fake dropdowns: just labels styled to look like dropdowns.
        JPanel bitwisePanel = new JPanel(new BorderLayout());
        bitwisePanel.setBackground(Color.WHITE);
        bitwisePanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        JLabel bitwiseLabel = new JLabel("  ⚡ Bitwise");
        bitwiseLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        JLabel bitwiseArrow = new JLabel("▼  ");
        bitwiseArrow.setFont(new Font("Segoe UI", Font.PLAIN, 8));
        bitwisePanel.add(bitwiseLabel, BorderLayout.WEST);
        bitwisePanel.add(bitwiseArrow, BorderLayout.EAST);

        JPanel bitshiftPanel = new JPanel(new BorderLayout());
        bitshiftPanel.setBackground(Color.WHITE);
        bitshiftPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        JLabel bitshiftLabel = new JLabel("  ⚡ Bit shift");
        bitshiftLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        JLabel bitshiftArrow = new JLabel("▼  ");
        bitshiftArrow.setFont(new Font("Segoe UI", Font.PLAIN, 8));
        bitshiftPanel.add(bitshiftLabel, BorderLayout.WEST);
        bitshiftPanel.add(bitshiftArrow, BorderLayout.EAST);

        panel.add(bitwisePanel);
        panel.add(bitshiftPanel);

        return panel;
    }

    private JPanel createButtonGrid() {
        JPanel panel = new JPanel(new GridLayout(6, 5, 5, 5));
        panel.setBackground(new Color(243, 243, 243));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));

        // All buttons, row by row (6 rows, 5 columns).
        String[][] buttonLayout = {
                {"A", "<<", ">>", "C", "⌫"},
                {"B", "(", ")", "%", "÷"},
                {"C", "7", "8", "9", "×"},
                {"D", "4", "5", "6", "-"},
                {"E", "1", "2", "3", "+"},
                {"F", "±", "0", ".", "="}
        };

        for (String[] row : buttonLayout) {
            for (String text : row) {
                JButton btn = createStyledButton(text, getButtonColor(text), 14);
                btn.addActionListener(e -> parent.handleButtonClick(text));
                panel.add(btn);
            }
        }

        return panel;
    }

    private JButton createStyledButton(String text, Color bgColor, int fontSize) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, fontSize));
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Make the equals button stand out.
        if (text.equals("=")) {
            button.setBackground(new Color(0, 120, 212));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Segoe UI", Font.BOLD, fontSize));
        }

        // Button hover effect.
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            Color originalColor = button.getBackground();
            Color originalForeground = button.getForeground();

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (text.equals("=")) {
                    button.setBackground(new Color(0, 100, 180));
                } else {
                    button.setBackground(new Color(
                            Math.max(0, originalColor.getRed() - 20),
                            Math.max(0, originalColor.getGreen() - 20),
                            Math.max(0, originalColor.getBlue() - 20)
                    ));
                }
            }



            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(originalColor);
                button.setForeground(originalForeground);
            }
        });
         return button;
        }

        private Color getButtonColor(String text) {
            // The equals button pops in blue.
            if (text.equals("=")) {
                return new Color(0, 120, 212);
            }

            // Buttons for A-F look lighter, kind of like they're off.
            if (text.matches("[A-F]")) {
                return new Color(240, 240, 240);
            }

            // Number keys are plain white.
            if (text.matches("[0-9]")) {
                return Color.WHITE;
            }

            // Everything else—operators, special keys—gets a soft gray.
            return new Color(250, 250, 250);
        }
    }