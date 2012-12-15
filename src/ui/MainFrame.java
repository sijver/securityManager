package ui;

import core.AliasesViewer;
import core.message.ExceptionHandler;
import ui.subframe.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 */
public class MainFrame extends JFrame {

    private JButton createKeyStoreButton;
    private JButton addAliasToKeyStoreButton;
    private JButton addCertificateButton;
    private JButton signFileButton;
    private JButton checkSignatureAndCertificateButton;
    private JButton viewKeyStoreAliasesButton;
    private JButton infoButton;

    private String programInfo = "KeyStore is a storage facility for cryptographic keys and certificates.\n" +
            "KeyStore manages different types of entries that are identified by an \"alias\" string.\n" +
            "One entry in KeyStore can contain public certificate belonging to another party or own private key with corresponding certificate.\n" +
            "\n" +
            "\"Create KeyStore\" - create KeyStore and specify its password.\n" +
            "\n" +
            "\"Put own data to KeyStore\" - put own private key with corresponding certificate to KeyStore and specify its alias.\n" +
            "\n" +
            "\"Put someone's certificate to KeyStore\" - put public certificate belonging to another party to KeyStore and specify its alias.\n" +
            "\n" +
            "\"Create signature\" - create a signature of file using own private key from appropriate alias.\n" +
            "\n" +
            "\"Check certificate and signature\" - verify signature of file received from another party.\n" +
            "\n" +
            "\"View KeyStore aliases\" - you can view all the aliases of appropriate KeyStore.";

    public MainFrame() {
        super("Security Manager");
        initFrame();
    }

    private void initFrame() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(330, 600);
        this.setLocationRelativeTo(null);
        initFrameComponents();
        initBehavior();
        this.setVisible(true);
    }

    private void initFrameComponents() {
        createKeyStoreButton = new JButton("Create KeyStore");
        this.add(createKeyStoreButton);
        createKeyStoreButton.setLocation(40, 40);
        createKeyStoreButton.setSize(250, 50);

        addAliasToKeyStoreButton = new JButton("Put own data to KeyStore");
        this.add(addAliasToKeyStoreButton);
        addAliasToKeyStoreButton.setLocation(40, 130);
        addAliasToKeyStoreButton.setSize(250, 50);

        addCertificateButton = new JButton("Put someone's certificate to KeyStore");
        this.add(addCertificateButton);
        addCertificateButton.setLocation(40, 220);
        addCertificateButton.setSize(250, 50);

        signFileButton = new JButton("Create signature");
        this.add(signFileButton);
        signFileButton.setLocation(40, 310);
        signFileButton.setSize(250, 50);

        checkSignatureAndCertificateButton = new JButton("Check certificate and signature");
        this.add(checkSignatureAndCertificateButton);
        checkSignatureAndCertificateButton.setLocation(40, 400);
        checkSignatureAndCertificateButton.setSize(250, 50);

        viewKeyStoreAliasesButton = new JButton("View KeyStore aliases");
        this.add(viewKeyStoreAliasesButton);
        viewKeyStoreAliasesButton.setLocation(40, 490);
        viewKeyStoreAliasesButton.setSize(165, 50);

        infoButton = new JButton("Info");
        this.add(infoButton);
        infoButton.setLocation(230, 490);
        infoButton.setSize(60, 50);
    }

    private void initBehavior() {
        createKeyStoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new KeyStoreCreatorFrame();
            }
        });

        addAliasToKeyStoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserPrivateManagerFrame();
            }
        });

        addCertificateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CertificateManagerFrame();
            }
        });

        signFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignatureGeneratorFrame();
            }
        });

        checkSignatureAndCertificateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignatureVerificatorFrame();
            }
        });

        viewKeyStoreAliasesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AliasesViewer.viewKeyStoreAliases();
                } catch (Exception exc) {
                    ExceptionHandler.printException(exc);
                }
            }
        });

        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InfoFrame("Info", programInfo);
            }
        });
    }

}
