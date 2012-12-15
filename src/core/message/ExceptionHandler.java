package core.message;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 */
public class ExceptionHandler {

    //Shows exception in MessageDialog and in command line
    public static void printException(Exception e) {
        String eString = e.toString();
        JOptionPane.showMessageDialog(null, eString, "Exception", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        //System.out.println(eString);
    }

}
