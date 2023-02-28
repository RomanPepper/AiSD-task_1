import Model.Document;
import View.WindowApplication;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Document currDocument = new Document();

        WindowApplication windowApplication = new WindowApplication(currDocument);
    }
}