package Model;

import java.util.ArrayList;
import java.util.List;

public class Document {
    List<Paragraph> paragraphList = new ArrayList<>();

    private final int columnsCount = 60;

    public Document() {
        this.createNewParagraph();
    }

    public void createNewParagraph() {
        this.paragraphList.add(new Paragraph());
    }

    public void deleteParagraph(int paragraphIndex) {
        if(this.isContainsParagraphByIndex(paragraphIndex)) {
            this.paragraphList.remove(paragraphIndex);
        }
    }

    public boolean isContainsParagraphByIndex(int index) {
        return  0 <= index && index < paragraphList.size();
    }

    public List<Paragraph> getParagraphList() {
        return paragraphList;
    }

    public int getColumnsCount() {
        return columnsCount;
    }
}
