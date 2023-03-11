package Model;


public class Paragraph {
    private String paragraphText;
    private TextStyleSettings textStyleSettings;

    //Конструктор стандартного пустого параграфа
    public Paragraph() {
        this.paragraphText = "";
        this.textStyleSettings = new TextStyleSettings();
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

    public void formatParagraph() {
        String[] piecesOfText = paragraphText.split("\\s*\\n\\s*"); //Массив абзацев текста

        //Обработка текста
        for (int i = 0; i < piecesOfText.length; i++) {
            //1. Отступы слева и справа
            //Проверка на однострочность
            if (piecesOfText[i].length() + textStyleSettings.getLeftIndent()
                    + textStyleSettings.getRightIndent() <= textStyleSettings.getRowSize()) {
                piecesOfText[i] = " ".repeat(textStyleSettings.getLeftIndent())
                        + piecesOfText[i] + " ".repeat(textStyleSettings.getRightIndent());
            } else { //Если весь абзац с учётом обработки не помещается в одну строку, то...
                int columnsCount = textStyleSettings.getRowSize();
                int leftIndent = textStyleSettings.getLeftIndent();
                int rightIndent = textStyleSettings.getRightIndent();

                String[] wordsArray = piecesOfText[i].split("\s+");
                int lineSymbolsCount = 0;

                for(int j = 0; j < wordsArray.length - 1; j++) {
                    if(j == 0) {
                        lineSymbolsCount += leftIndent;
                        wordsArray[j] = " ".repeat(leftIndent) + wordsArray[j];
                    }

                    lineSymbolsCount += wordsArray[j].length() + 1; // +1 для учёта пробелов, которыми слова потом слепятся
                    if(lineSymbolsCount + wordsArray[j + 1].length() + rightIndent > columnsCount) {
                        wordsArray[j] = wordsArray[j] + "\n";
                        //Количество пробелов будем уменьшать на 1, чтобы скомпенсировать лишний пробел от склейки строки в конце
                        wordsArray[j + 1] = " ".repeat(leftIndent - 1) + wordsArray[j + 1];
                        lineSymbolsCount = leftIndent;
                    }
                }

                piecesOfText[i] = String.join(" ", wordsArray);
            }


            //3. Отступы красной строки
            piecesOfText[i] = " ".repeat(textStyleSettings.getBreakLineIndent()) + piecesOfText[i];


            //4. Отступы сверху
            piecesOfText[i] = "\n".repeat(textStyleSettings.getTopIndent()) + piecesOfText[i];

            //5. Отступы cнизу
            piecesOfText[i] = piecesOfText[i] + "\n".repeat(textStyleSettings.getBottomIndent());

        }

        //Обработка списков
        switch (textStyleSettings.getListType()) {
            case MARKED -> {
                for (int i = 0; i < piecesOfText.length; i++) {
                    piecesOfText[i] = piecesOfText[i].replaceAll("\\*", "•");
                }
            }
            case NUMBERED -> {
                int listElementNumber = 1;
                for (int i = 0; i < piecesOfText.length; i++) {
                    if (piecesOfText[i].contains("*")) {
                        String[] symbolsArray = piecesOfText[i].split("");
                        if (symbolsArray[0].equals("*")) {
                            piecesOfText[i] = piecesOfText[i].replaceFirst("\\*", listElementNumber + ". ");
                            listElementNumber++;
                        }
                    }
                }
            }
        }


        paragraphText = String.join("\n", piecesOfText);
    }

    public TextStyleSettings getTextStyleSettings() {
        return textStyleSettings;
    }

    public String getParagraphText() {
        return paragraphText;
    }

    public void setParagraphText(String paragraphText) {
        this.paragraphText = paragraphText;
    }

}
