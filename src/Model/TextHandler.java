package Model;

public class TextHandler {
    public int getNextWordIndex(String[] array, int startIndex) {
        for (int i = startIndex; i < array.length; i++) {
            if (!array[i].equals(" ")) {
                return i;
            }
        }
        return -1;
    }
}
