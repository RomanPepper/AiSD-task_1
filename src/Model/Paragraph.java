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

    public void formatParagraph(int columnsCount) {
        //Тут очень тупо получается, т.к. по ТЗ необходимо редактировать данные внутри модели (расставлять отступы)
        //на основании данных, получаемых из отображения, а потому с инкапсуляцией проблемы...
        String[] piecesOfText = paragraphText.split("\\s*\\n\\s*");

        for (int i = 0; i < piecesOfText.length; i++) {
            //Заголовки и обычный текст будем обрабатывать отдельно, т.к. стандартные отступы неприменимы к заголовкам
            if (piecesOfText[i].toUpperCase().equals(piecesOfText[i]) && !piecesOfText[i].equals("")) {
                //Сначала приведём текст к нормальному виду:
                piecesOfText[i] = piecesOfText[i].toLowerCase();
                String firstSymbol = String.valueOf(piecesOfText[i].charAt(0));
                piecesOfText[i] = piecesOfText[i].replaceFirst(firstSymbol, firstSymbol.toUpperCase());

                //Центрирование заголовка
                piecesOfText[i] = "  ".repeat((columnsCount - piecesOfText.length) / 2) + piecesOfText[i] +
                        "  ".repeat((columnsCount - piecesOfText.length) / 2);

            } else {
                //Теперь обработка обычного текста
                //Отступы сверху
                piecesOfText[i] = "\n".repeat(textStyleSettings.getTopIndent()) + piecesOfText[i];


                //Отступы слева
                boolean breakLineFlag = false;
                String[] currWordsArray = piecesOfText[i].split("");
                for(int j = 0; j < piecesOfText[i].length(); j++) {
                    //Особое условие для обработки первой строки, чтобы избежать проблем с красной строкой
                    if(j < columnsCount && !breakLineFlag) {
                        //Добавим отступ красной строки:
                        currWordsArray[j] = "  ".repeat(textStyleSettings.getBreakLineIndent()) + currWordsArray[j];
                        breakLineFlag = true;
                    }
                }



                //Отступы справа



                //Отступы cнизу
                piecesOfText[i] = piecesOfText[i] + "\n".repeat(textStyleSettings.getBottomIndent());

            }
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
