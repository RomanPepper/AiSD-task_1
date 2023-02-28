package Model;

public class Paragraph {
    private String paragraphText;
    private TextStyleSettings textStyleSettings;

    //Конструктор стандартного пустого параграфа
    public Paragraph() {
        this.paragraphText = "";
        this.textStyleSettings = new TextStyleSettings();
    }

    //Конструктор параграфа с пользовательскими настройками
    public Paragraph(String paragraphText, TextStyleSettings textStyleSettings) {
        this.paragraphText = paragraphText;
        this.textStyleSettings = textStyleSettings;
    }

    public void changeTextStyleSettings(int leftIndent, int rightIndent, int topIndent, int bottomIndent,
                                        int breakLineIndent, ListType listType) {
        textStyleSettings.setLeftIndent(leftIndent);
        textStyleSettings.setRightIndent(rightIndent);
        textStyleSettings.setTopIndent(topIndent);
        textStyleSettings.setBottomIndent(bottomIndent);
        textStyleSettings.setBreakLineIndent(breakLineIndent);
        textStyleSettings.setListType(listType);
    }

    public void formatParagraph(int columnsCount) {
        String[] piecesOfText = paragraphText.split("\\s*\\n\\s*");
        //Тут очень тупо получается, т.к. по ТЗ необходимо редактировать данные внутри модели (расставлять отступы)
        //на основании данных, получаемых из отображения, а потому с инкапсуляцией проблемы...

        //Обработка отступов и красных строк
        for (int i = 0; i < piecesOfText.length; i++) {
            //Отступы красной строки
            piecesOfText[i] = " ".repeat(textStyleSettings.getBreakLineIndent()) + piecesOfText[i];

            //Отступ справа
            piecesOfText[i] = piecesOfText[i] + " ".repeat(textStyleSettings.getRightIndent());

            //Отступ слева
            piecesOfText[i] = " ".repeat(textStyleSettings.getLeftIndent()) + piecesOfText[i];

            //Отступы сверху
            piecesOfText[i] = "\n".repeat(textStyleSettings.getTopIndent()) + piecesOfText[i];

            //Отступы снизу
            piecesOfText[i] = piecesOfText[i] + "\n".repeat(textStyleSettings.getBottomIndent());

        }

        //Обработка заголовков
        for (int i = 0; i < piecesOfText.length; i++) {
            //Если вся строка состоит из больших букв, то это заголовок
            if (piecesOfText[i].equals(piecesOfText[i].toUpperCase())) {
                piecesOfText[i] = " ".repeat((80 - piecesOfText.length) / 2) + piecesOfText[i]
                        + " ".repeat((80 - piecesOfText.length) / 2);
            }
        }

        //Обработка списков
        if(textStyleSettings.getListType() == ListType.MARKED) {
            for (String s : piecesOfText) {
                s.replaceAll("\\*", "•");
            }
        } else {
            for(int i = 0; i < piecesOfText.length; i++) {
                int count = 1;
                String[] symbolsArray = piecesOfText[i].split("");

                for(int j = 0; j < symbolsArray.length; j++) {
                    if (symbolsArray[j].equals("*")) {
                        symbolsArray[j] = String.valueOf(count) + ". ";
                    }
                }

                piecesOfText[i] = String.join("", symbolsArray);
            }
        }
    }
}
