package core;

import core.message.Notifier;
import ui.subframe.InfoFrame;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.Signature;

/**
 * Created with IntelliJ IDEA.
 */
public class SignatureVerificator {

    public static void verifySignature(String keyStoreFileName, String fileToCheckSignName, String signatureFileName, String aliasName, char[] keyStorePassword) throws Exception {
        //Import Certificate and its Public Key from KeyStore
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream keyStoreInputStream = new FileInputStream(keyStoreFileName);
        BufferedInputStream keyStoreBufferedInputStream = new BufferedInputStream(keyStoreInputStream);
        keyStore.load(keyStoreBufferedInputStream, keyStorePassword);
        java.security.cert.Certificate certificate = keyStore.getCertificate(aliasName);
        System.out.println(certificate);
        PublicKey publicKey = certificate.getPublicKey();

        //Open the Signature File
        FileInputStream signatureInputStream = new FileInputStream(signatureFileName);
        byte[] signatureToVerify = new byte[signatureInputStream.available()];
        signatureInputStream.read(signatureToVerify);
        signatureInputStream.close();

        //Create a Signature object and initialize it with the public certificate
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initVerify(publicKey);

        //Load the file to check the Signature
        FileInputStream fileInputStream = new FileInputStream(fileToCheckSignName);
        BufferedInputStream fileBufferedInputStream = new BufferedInputStream(fileInputStream);

        //Verify the signature. This is done by creating of new signature for needed file using
        //public certificate of sender and by comparing of the received signature and created
        byte[] buffer = new byte[1024];
        int len;
        while (fileBufferedInputStream.available() != 0) {
            len = fileBufferedInputStream.read(buffer);
            signature.update(buffer, 0, len);
        }
        fileBufferedInputStream.close();
        boolean verified = signature.verify(signatureToVerify);

        //Show message is signature verified
        if (verified) {
            Notifier.showMessage("Success", "Signature verified successfully");
        } else {
            Notifier.showMessage("Fail", "Signature not verified. Signature, file or certificate is wrong");
        }

        //Get certificate owner information and show it
        String certificateText = certificate.toString();
        int certificateBegin = certificateText.indexOf("Version");
        int certificateEnd = certificateText.indexOf("Certificate Extension");
        if (certificateBegin != -1 && certificateEnd != -1) {
            certificateText = certificateText.substring(certificateBegin, certificateEnd);
        }
        new InfoFrame("Certificate info", certificateText);
    }

}
