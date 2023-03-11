package View;

import Model.Document;
import Model.ListType;
import Model.Paragraph;
import Model.TextStyleSettings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
    private JTextField rowSizeTextField;
    private Document document;

    public WindowApplication(Document document) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.setName("Bebra-IT-Developer: task 1");
        this.setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(645, 780));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.document = document;

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        mainTextArea.setColumns(document.getParagraphList().get(0).getTextStyleSettings().getRowSize());

        //RadioButton
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(markedListTypeRadioButton);
        buttonGroup.add(numberedListTypeRadioButton);

        //Изначально выводим первый параграф, созданный при запуске программы
        this.switchParagraph( 0);

        //Написать логику кнопок "prev", "next"
        nextParagraphButton.addActionListener(e -> {
            int currParagraphNum = Integer.parseInt(paragraphNumTextField.getText());
            this.switchParagraph(currParagraphNum + 1);
        });

        prevParagraphButton.addActionListener(e -> {
            int currParagraphNum = Integer.parseInt(paragraphNumTextField.getText());
            this.switchParagraph(currParagraphNum - 1);
        });

        startFormatButton.addActionListener(e -> {
            int currParagraphNum = Integer.parseInt(paragraphNumTextField.getText());

            int leftIndent = Integer.parseInt(leftIndentTextField.getText());
            int rightIndent = Integer.parseInt(rightIndentTextField.getText());
            int topIndent = Integer.parseInt(topIndentTextField.getText());
            int bottomIndent = Integer.parseInt(bottomIndentTextField.getText());
            int breakLineIndent = Integer.parseInt(breakLineIndentTextField.getText());
            int rowSize = Integer.parseInt(rowSizeTextField.getText());
            String mainText = mainTextArea.getText();
            ListType listType = markedListTypeRadioButton.isSelected() ? ListType.MARKED : ListType.NUMBERED;

            //Устанавливаем выбранному параграфу новые настройки
            document.getParagraphList().get(currParagraphNum).changeTextStyleSettings(leftIndent, rightIndent, topIndent,
                    bottomIndent, breakLineIndent, listType);

            //Обновляем текст в параграфе
            document.getParagraphList().get(currParagraphNum).setParagraphText(mainText);

            //Форматируем текст выбранного параграфа
            document.getParagraphList().get(currParagraphNum).formatParagraph();

            //Обновляем данные
            switchParagraph(currParagraphNum);
        });

        newParagraphButton.addActionListener(e -> {
            document.createNewParagraph();
            switchParagraph(document.getParagraphList().size() - 1);
        });

        deleteParagraphButton.addActionListener(e -> {
            int paragraphNumber = Integer.parseInt(paragraphNumTextField.getText());
            document.deleteParagraph(paragraphNumber);
            switchParagraph(document.getParagraphList().size() - 1);
        });

        this.pack();
        this.setVisible(true);
    }

    private void switchParagraph(int paragraphIndex) {
        //Написать эту логику
        //Примечание: должен быть чек на наличие необходимого параграфа
        if(document.isContainsParagraphByIndex(paragraphIndex)) {
            //Запрос на обновление данных
            Paragraph newParagraph = document.getParagraphList().get(paragraphIndex);
            TextStyleSettings textStyleSettings = newParagraph.getTextStyleSettings();

            paragraphNumTextField.setText(String.valueOf(paragraphIndex));
            leftIndentTextField.setText(String.valueOf(textStyleSettings.getLeftIndent()));
            rightIndentTextField.setText(String.valueOf(textStyleSettings.getRightIndent()));
            topIndentTextField.setText(String.valueOf(textStyleSettings.getTopIndent()));
            bottomIndentTextField.setText(String.valueOf(textStyleSettings.getBottomIndent()));
            breakLineIndentTextField.setText(String.valueOf(textStyleSettings.getBreakLineIndent()));
            rowSizeTextField.setText(String.valueOf(textStyleSettings.getRowSize()));

            if(textStyleSettings.getListType() == ListType.NUMBERED) {
                numberedListTypeRadioButton.setSelected(true);
                markedListTypeRadioButton.setSelected(false);
            } else {
                numberedListTypeRadioButton.setSelected(false);
                markedListTypeRadioButton.setSelected(true);
            }

//            System.out.println("amogus".repeat(0));
            mainTextArea.setText(newParagraph.getParagraphText());
        }
    }
}
