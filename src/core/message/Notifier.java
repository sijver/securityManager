package core.message;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 */
public class Notifier {

    //Shows MessageDialog with needed information and prints it to command line
    public static void showMessage(String messageHeader, String messageText) {
        JOptionPane.showMessageDialog(null, messageText, messageHeader, JOptionPane.INFORMATION_MESSAGE);
        showCommandLineMessageOnly(messageHeader, messageText);
    }

    //Shows needed information only in command line
    public static void showCommandLineMessageOnly(String messageHeader, String messageText) {
        System.out.println(messageHeader + ": " + messageText);
    }

}
