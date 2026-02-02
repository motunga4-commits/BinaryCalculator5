import javax.swing.*;
import java.awt.*;

public class CalculatorFrame extends JFrame {
    private DisplayPanel displayPanel;
    private ButtonPanel buttonPanel;
    private CalculatorEngine engine;

    public CalculatorFrame() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        engine = new CalculatorEngine();

        setupFrame();

        setSize(350, 650);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(243, 243, 243));
    }

    private void setupFrame() {
        setLayout(new BorderLayout());

        MenuPanel menuPanel = new MenuPanel();
        add(menuPanel, BorderLayout.NORTH);

        displayPanel = new DisplayPanel();
        add(displayPanel, BorderLayout.CENTER);

        buttonPanel = new ButtonPanel(this);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void handleButtonClick(String buttonText) {
        String result = engine.processInput(buttonText);
        displayPanel.updateDisplay(result);
        displayPanel.updateNumberSystems(engine.getCurrentValue());
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            CalculatorFrame calculator = new CalculatorFrame();
            calculator.setVisible(true);
        });
    }
}