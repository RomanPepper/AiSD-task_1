import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.*;

public class WindowApplication extends JFrame{

    private JPanel mainPanel;
    private JTextArea textArea1;
    private JButton prevButton;
    private JButton nextButton;
    private JButton applyButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    public WindowApplication() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.setName("Bebra-IT-Developer: task 1");
        this.setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(720, 960));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        this.pack();
        this.setVisible(true);
    }
}
