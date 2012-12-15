package ui.subframe;

import core.SignatureVerificator;
import core.message.ExceptionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 */
public class SignatureVerificatorFrame extends JFrame {

    private JButton openKeyStoreButton;
    private JFileChooser keyStoreFileChooser;
    private JButton openFileToCheckSignButton;
    private JFileChooser fileToCheckSignChooser;
    private JButton openSignatureButton;
    private JFileChooser signatureFileChooser;
    private JTextField aliasNameCATextField;
    private JTextField aliasNameTextField;
    private JPasswordField keyStorePasswordField;
    private JButton finishButton;

    private String keyStoreFileName;
    private String fileToCheckSignName;
    private String signatureFileName;

    //The Frame for verifying of signature of file using someone's public certificate from KeyStore
    public SignatureVerificatorFrame() {
        super("Signature Verificator");
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
        openKeyStoreButton = new JButton("Open KeyStore to get Public Key");
        this.add(openKeyStoreButton);
        openKeyStoreButton.setLocation(80, 40);
        openKeyStoreButton.setSize(250, 50);

        openFileToCheckSignButton = new JButton("Open file to check its signature");
        this.add(openFileToCheckSignButton);
        openFileToCheckSignButton.setLocation(80, 130);
        openFileToCheckSignButton.setSize(250, 50);

        openSignatureButton = new JButton("Specify signature file");
        this.add(openSignatureButton);
        openSignatureButton.setLocation(80, 220);
        openSignatureButton.setSize(250, 50);

        JLabel aliasNameLabel = new JLabel("Alias name:");
        this.add(aliasNameLabel);
        aliasNameLabel.setLocation(40, 300);
        aliasNameLabel.setSize(130, 25);

        aliasNameTextField = new JTextField();
        this.add(aliasNameTextField);
        aliasNameTextField.setLocation(180, 300);
        aliasNameTextField.setSize(190, 25);

        JLabel aliasNameCALabel = new JLabel("CA Alias name:");
        this.add(aliasNameCALabel);
        aliasNameCALabel.setLocation(40, 350);
        aliasNameCALabel.setSize(130, 25);

        aliasNameCATextField = new JTextField();
        this.add(aliasNameCATextField);
        aliasNameCATextField.setLocation(180, 350);
        aliasNameCATextField.setSize(190, 25);

        JLabel keyStorePasswordLabel = new JLabel("KeyStore password:");
        this.add(keyStorePasswordLabel);
        keyStorePasswordLabel.setLocation(40, 400);
        keyStorePasswordLabel.setSize(130, 25);

        keyStorePasswordField = new JPasswordField();
        this.add(keyStorePasswordField);
        keyStorePasswordField.setLocation(180, 400);
        keyStorePasswordField.setSize(190, 25);

        finishButton = new JButton("Check Signature");
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

        openFileToCheckSignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileToCheckSignChooser = new JFileChooser();
                if (fileToCheckSignChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    fileToCheckSignName = fileToCheckSignChooser.getSelectedFile().getAbsolutePath();
                }
            }
        });

        openSignatureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signatureFileChooser = new JFileChooser();
                if (signatureFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    signatureFileName = signatureFileChooser.getSelectedFile().getAbsolutePath();
                }
            }
        });

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SignatureVerificator.verifySignature(keyStoreFileName, fileToCheckSignName, signatureFileName, aliasNameTextField.getText(), aliasNameCATextField.getText(), keyStorePasswordField.getPassword());
                } catch (Exception exc) {
                    ExceptionHandler.printException(exc);
                }
            }
        });


    }

}
