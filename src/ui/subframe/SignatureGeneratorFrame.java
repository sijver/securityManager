package ui.subframe;

import core.SignatureGenerator;
import core.message.ExceptionHandler;
import core.message.Notifier;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 */
public class SignatureGeneratorFrame extends JFrame {

    private JButton openKeyStoreButton;
    private JFileChooser keyStoreFileChooser;
    private JButton openFileToSignButton;
    private JFileChooser fileToSignChooser;
    private JButton saveSignatureButton;
    private JFileChooser signatureFileChooser;
    private JTextField aliasNameTextField;
    private JPasswordField keyStorePasswordField;
    private JPasswordField privateKeyPasswordField;
    private JButton finishButton;

    private String keyStoreFileName;
    private String fileToSignName;
    private String signatureFileName;

    //The frame for creating signature of file using own private key from loaded KeyStore
    public SignatureGeneratorFrame() {
        super("Signature Generator");
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
        openKeyStoreButton = new JButton("Open KeyStore to get Private Key");
        this.add(openKeyStoreButton);
        openKeyStoreButton.setLocation(80, 40);
        openKeyStoreButton.setSize(250, 50);

        openFileToSignButton = new JButton("Open file to sign");
        this.add(openFileToSignButton);
        openFileToSignButton.setLocation(80, 130);
        openFileToSignButton.setSize(250, 50);

        saveSignatureButton = new JButton("Specify signature file");
        this.add(saveSignatureButton);
        saveSignatureButton.setLocation(80, 220);
        saveSignatureButton.setSize(250, 50);

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

        finishButton = new JButton("Generate and save Signature");
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

        openFileToSignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileToSignChooser = new JFileChooser();
                if (fileToSignChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    fileToSignName = fileToSignChooser.getSelectedFile().getAbsolutePath();
                }
            }
        });

        saveSignatureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signatureFileChooser = new JFileChooser();
                if (signatureFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    signatureFileName = signatureFileChooser.getSelectedFile().getAbsolutePath();
                }
            }
        });

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SignatureGenerator.generateSignature(keyStoreFileName, fileToSignName, signatureFileName, aliasNameTextField.getText(), keyStorePasswordField.getPassword(), privateKeyPasswordField.getPassword());
                    Notifier.showMessage("Success", "Signature generated successfully");
                } catch (Exception exc) {
                    ExceptionHandler.printException(exc);
                }
            }
        });


    }

}
