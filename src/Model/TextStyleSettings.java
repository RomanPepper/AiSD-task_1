package Model;

public class TextStyleSettings {
    private int leftIndent;
    private int rightIndent;
    private int topIndent;
    private int bottomIndent;
    private int breakLineIndent;
    private int rowSize;

    private ListType listType;

    //Конструктор по умолчанию
    public TextStyleSettings() {
        this.leftIndent = 4;
        this.rightIndent = 4;
        this.topIndent = 1;
        this.bottomIndent = 0;
        this.breakLineIndent = 4;
        this.rowSize = 60;
        this.listType = ListType.MARKED;
    }

    //Конструктор с пользовательскими настройками
    public TextStyleSettings(int leftIndent, int rightIndent, int topIndent, int bottomIndent,
                             int breakLineIndent, int rowSize, ListType listType) {
        this.leftIndent = leftIndent;
        this.rightIndent = rightIndent;
        this.topIndent = topIndent;
        this.bottomIndent = bottomIndent;
        this.breakLineIndent = breakLineIndent;
        this.rowSize = rowSize;
        this.listType = listType;
    }

    public int getLeftIndent() {
        return leftIndent;
    }

    public void setLeftIndent(int leftIndent) {
        this.leftIndent = leftIndent;
    }

    public int getRightIndent() {
        return rightIndent;
    }

    public void setRightIndent(int rightIndent) {
        this.rightIndent = rightIndent;
    }

    public int getTopIndent() {
        return topIndent;
    }

    public void setTopIndent(int topIndent) {
        this.topIndent = topIndent;
    }

    public int getBottomIndent() {
        return bottomIndent;
    }

    public void setBottomIndent(int bottomIndent) {
        this.bottomIndent = bottomIndent;
    }

    public int getBreakLineIndent() {
        return breakLineIndent;
    }

    public void setBreakLineIndent(int breakLineIndent) {
        this.breakLineIndent = breakLineIndent;
    }

    public ListType getListType() {
        return listType;
    }

    public void setListType(ListType listType) {
        this.listType = listType;
    }

    public int getRowSize() {
        return rowSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }
}
