package ui.subframe;

import core.KeyStoreCreator;
import core.message.ExceptionHandler;
import core.message.Notifier;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 */
public class KeyStoreCreatorFrame extends JFrame {

    private JButton createKeyStoreButton;
    private JPasswordField keyStorePasswordField;
    private JFileChooser keyStoreFileChooser;

    //The Frame for KeyStore creating and saving
    public KeyStoreCreatorFrame() {
        super("KeyStore Creator");
        initFrame();
    }

    private void initFrame() {
        this.setLocationByPlatform(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(410, 220);
        initFrameComponents();
        initBehavior();
        this.setVisible(true);
    }

    private void initFrameComponents() {
        JLabel keyStorePasswordLabel = new JLabel("KeyStore password:");
        this.add(keyStorePasswordLabel);
        keyStorePasswordLabel.setLocation(40, 40);
        keyStorePasswordLabel.setSize(120, 25);

        keyStorePasswordField = new JPasswordField();
        this.add(keyStorePasswordField);
        keyStorePasswordField.setLocation(170, 40);
        keyStorePasswordField.setSize(200, 25);

        createKeyStoreButton = new JButton("Create & Save KeyStore");
        this.add(createKeyStoreButton);
        createKeyStoreButton.setLocation(80, 110);
        createKeyStoreButton.setSize(250, 50);
    }

    private void initBehavior() {
        createKeyStoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyStoreFileChooser = new JFileChooser();
                keyStoreFileChooser.setSelectedFile(new File(".keystore"));
                if (keyStoreFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        KeyStoreCreator.CreateKeyStore(keyStorePasswordField.getPassword(), keyStoreFileChooser.getSelectedFile().getAbsolutePath());
                        Notifier.showMessage("Success", "KeyStore created successfully");
                    } catch (Exception exc) {
                        ExceptionHandler.printException(exc);
                    }
                }
            }
        });
    }

}
