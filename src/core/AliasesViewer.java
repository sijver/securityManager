package core;

import core.message.Notifier;
import ui.subframe.InfoFrame;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.Enumeration;

/**
 * Created with IntelliJ IDEA.
 */
public class AliasesViewer {

    //Shows list of aliases of specified KeyStore
    public static void viewKeyStoreAliases() throws Exception {
        JFileChooser keyStoreFileChooser = new JFileChooser();
        if (keyStoreFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String keyStoreFileName = keyStoreFileChooser.getSelectedFile().getAbsolutePath();
            JPasswordField keyStorePasswordField = new JPasswordField();

            //Get KeyStore password
            JOptionPane.showConfirmDialog(
                    null,
                    keyStorePasswordField,
                    "KeyStore Password:",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            //Load KeyStore
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            FileInputStream keyStoreInputStream = new FileInputStream(keyStoreFileName);
            BufferedInputStream keyStoreBufferedInputStream = new BufferedInputStream(keyStoreInputStream);
            keyStore.load(keyStoreBufferedInputStream, keyStorePasswordField.getPassword());

            //Get aliases and show them
            String aliases = "";
            Enumeration<String> aliasesEnum = keyStore.aliases();
            while (aliasesEnum.hasMoreElements()) {
                aliases = aliases + aliasesEnum.nextElement() + "\n";
            }
            new InfoFrame("Aliases list", aliases);
            Notifier.showCommandLineMessageOnly("Aliases list", aliases);
        }
    }

}
