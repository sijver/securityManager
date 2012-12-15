package ui.subframe;

import core.CertificateManager;
import core.message.ExceptionHandler;
import core.message.Notifier;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 */
public class CertificateManagerFrame extends JFrame {

    private JButton openKeyStoreButton;
    private JFileChooser keyStoreFileChooser;
    private JButton openCertificateButton;
    private JFileChooser certificateFileChooser;
    private JTextField aliasNameTextField;
    private JPasswordField keyStorePasswordField;
    private JButton finishButton;

    private String keyStoreFileName;
    private String certificateFileName;

    //Frame for importing of someone's public certificate to KeyStore
    public CertificateManagerFrame() {
        super("Public Certificates Manager");
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
        openKeyStoreButton = new JButton("Open KeyStore to add Certificate");
        this.add(openKeyStoreButton);
        openKeyStoreButton.setLocation(80, 40);
        openKeyStoreButton.setSize(250, 50);

        openCertificateButton = new JButton("Open certificate to import");
        this.add(openCertificateButton);
        openCertificateButton.setLocation(80, 130);
        openCertificateButton.setSize(250, 50);

        JLabel aliasNameLabel = new JLabel("Alias name:");
        this.add(aliasNameLabel);
        aliasNameLabel.setLocation(40, 210);
        aliasNameLabel.setSize(130, 25);

        aliasNameTextField = new JTextField();
        this.add(aliasNameTextField);
        aliasNameTextField.setLocation(180, 210);
        aliasNameTextField.setSize(190, 25);

        JLabel keyStorePasswordLabel = new JLabel("KeyStore password:");
        this.add(keyStorePasswordLabel);
        keyStorePasswordLabel.setLocation(40, 260);
        keyStorePasswordLabel.setSize(130, 25);

        keyStorePasswordField = new JPasswordField();
        this.add(keyStorePasswordField);
        keyStorePasswordField.setLocation(180, 260);
        keyStorePasswordField.setSize(190, 25);

        finishButton = new JButton("Import certificate alias to KeyStore");
        this.add(finishButton);
        finishButton.setLocation(80, 320);
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

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CertificateManager.importCertificate(keyStoreFileName, certificateFileName, aliasNameTextField.getText(), keyStorePasswordField.getPassword());
                    Notifier.showMessage("Success", "Public certificate imported to KeyStore successfully");
                } catch (Exception exc) {
                    ExceptionHandler.printException(exc);
                }
            }
        });


    }

}
