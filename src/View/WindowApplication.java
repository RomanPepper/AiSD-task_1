package View;

import Model.Document;
import Model.ListType;

import javax.swing.*;
import java.awt.*;

public class WindowApplication extends JFrame{

    private JPanel mainPanel;
    private JTextArea mainTextArea;
    private JButton prevParagraphButton;
    private JButton nextParagraphButton;
    private JButton startFormatButton;
    private JTextField leftIndentTextField;
    private JTextField rightIndentTextField;
    private JTextField paragraphNumTextField;
    private JTextField topIndentTextField;
    private JTextField bottomIndentTextField;
    private JTextField breakLineIndentTextField;
    private JRadioButton numberedListTypeRadioButton;
    private JRadioButton markedListTypeRadioButton;
    private JButton newParagraphButton;
    private JButton deleteParagraphButton;

    public WindowApplication(Document document) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.setName("Bebra-IT-Developer: task 1");
        this.setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(640, 740));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        mainTextArea.setColumns(document.getColumnsCount());

        //RadioButton
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(markedListTypeRadioButton);
        buttonGroup.add(numberedListTypeRadioButton);

        //Изначально выводим первый параграф, созданный при запуске программы
        this.switchParagraph(0);

        //Написать логику кнопок "prev", "next"
        nextParagraphButton.addActionListener(e -> {
            int currParagraphNum = Integer.parseInt(paragraphNumTextField.getText());
            this.switchParagraph(currParagraphNum - 1);
        });

        prevParagraphButton.addActionListener(e -> {
            int currParagraphNum = Integer.parseInt(paragraphNumTextField.getText());
            this.switchParagraph((currParagraphNum + 1));
        });

        startFormatButton.addActionListener(e -> {
            int currParagraphNum = Integer.parseInt(paragraphNumTextField.getText());
            int leftIndent = Integer.parseInt(leftIndentTextField.getText());
            int rightIndent = Integer.parseInt(rightIndentTextField.getText());
            int topIndent = Integer.parseInt(topIndentTextField.getText());
            int bottomIndent = Integer.parseInt(bottomIndentTextField.getText());
            int breakLineIndent = Integer.parseInt(breakLineIndentTextField.getText());

            ListType listType = markedListTypeRadioButton.isSelected() ? ListType.MARKED : ListType.NUMBERED;

            document.getParagraphList().get(currParagraphNum - 1).changeTextStyleSettings(leftIndent, rightIndent, topIndent,
                    bottomIndent, breakLineIndent, listType);

            document.getParagraphList().get(currParagraphNum).formatParagraph(document.getColumnsCount());
        });

        newParagraphButton.addActionListener(e -> {
            document.createNewParagraph();
        });

        deleteParagraphButton.addActionListener(e -> {
            int paragraphNumber = Integer.parseInt(paragraphNumTextField.getText());
            document.deleteParagraph(paragraphNumber - 1);
        });

        this.pack();
        this.setVisible(true);
    }

    private void switchParagraph(int paragraphIndex) {
        //Написать эту логику
        //Примечание: должен быть чек на наличие необходимого параграфа

    }
}
