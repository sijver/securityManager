package ui.subframe;

import core.UserPrivateManager;
import core.message.ExceptionHandler;
import core.message.Notifier;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 */
public class UserPrivateManagerFrame extends JFrame {

    private JButton openKeyStoreButton;
    private JFileChooser keyStoreFileChooser;
    private JButton openCertificateButton;
    private JFileChooser certificateFileChooser;
    private JButton openPrivateKeyButton;
    private JFileChooser privateKeyFileChooser;
    private JTextField aliasNameTextField;
    private JPasswordField keyStorePasswordField;
    private JPasswordField privateKeyPasswordField;
    private JButton finishButton;

    private String keyStoreFileName;
    private String certificateFileName;
    private String privateKeyFileName;

    //Frame for importing of own private key and corresponding public certificate to KeyStore
    public UserPrivateManagerFrame() {
        super("Private Key and Certificate Manager");
        initFrame();
    }

    private void initFrame() {
        this.setLocationByPlatform(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(410, 580);
        initFrameComponents();
        initBehavior();
        this.setVisible(true);
    }

    private void initFrameComponents() {
        openKeyStoreButton = new JButton("Open KeyStore to add data");
        this.add(openKeyStoreButton);
        openKeyStoreButton.setLocation(80, 40);
        openKeyStoreButton.setSize(250, 50);

        openCertificateButton = new JButton("Open your public Certificate");
        this.add(openCertificateButton);
        openCertificateButton.setLocation(80, 130);
        openCertificateButton.setSize(250, 50);

        openPrivateKeyButton = new JButton("Open Private Key");
        this.add(openPrivateKeyButton);
        openPrivateKeyButton.setLocation(80, 220);
        openPrivateKeyButton.setSize(250, 50);

        JLabel aliasNameLabel = new JLabel("Alias name:");
        this.add(aliasNameLabel);
        aliasNameLabel.setLocation(40, 300);
        aliasNameLabel.setSize(130, 25);

        aliasNameTextField = new JTextField();
        this.add(aliasNameTextField);
        aliasNameTextField.setLocation(180, 300);
        aliasNameTextField.setSize(190, 25);

        JLabel keyStorePasswordLabel = new JLabel("KeyStore password:");
        this.add(keyStorePasswordLabel);
        keyStorePasswordLabel.setLocation(40, 350);
        keyStorePasswordLabel.setSize(130, 25);

        keyStorePasswordField = new JPasswordField();
        this.add(keyStorePasswordField);
        keyStorePasswordField.setLocation(180, 350);
        keyStorePasswordField.setSize(190, 25);

        JLabel privateKeyPasswordLabel = new JLabel("Private Key password:");
        this.add(privateKeyPasswordLabel);
        privateKeyPasswordLabel.setLocation(40, 400);
        privateKeyPasswordLabel.setSize(130, 25);

        privateKeyPasswordField = new JPasswordField();
        this.add(privateKeyPasswordField);
        privateKeyPasswordField.setLocation(180, 400);
        privateKeyPasswordField.setSize(190, 25);

        finishButton = new JButton("Import private alias to KeyStore");
        this.add(finishButton);
        finishButton.setLocation(80, 460);
        finishButton.setSize(250, 50);
    }

    private void initBehavior() {
        openKeyStoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyStoreFileChooser = new JFileChooser();
                if (keyStoreFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    keyStoreFileName = keyStoreFileChooser.getSelectedFile().getAbsolutePath();
                }
            }
        });

        openCertificateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                certificateFileChooser = new JFileChooser();
                if (certificateFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    certificateFileName = certificateFileChooser.getSelectedFile().getAbsolutePath();
                }
            }
        });

        openPrivateKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                privateKeyFileChooser = new JFileChooser();
                if (privateKeyFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    privateKeyFileName = privateKeyFileChooser.getSelectedFile().getAbsolutePath();
                }
            }
        });

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UserPrivateManager.ImportDataToKeyStore(keyStoreFileName, certificateFileName, privateKeyFileName, aliasNameTextField.getText(), keyStorePasswordField.getPassword(), privateKeyPasswordField.getPassword());
                    Notifier.showMessage("Success", "Private Key and Certificate imported to KeyStore successfully");
                } catch (Exception exc) {
                    ExceptionHandler.printException(exc);
                }
            }
        });


    }

}
