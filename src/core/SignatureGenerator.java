package core;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;

/**
 * Created with IntelliJ IDEA.
 */
public class SignatureGenerator {

    public static void generateSignature(String keyStoreFileName, String fileToSignName, String signatureFileName, String aliasName, char[] keyStorePassword, char[] privateKeyPassword) throws Exception {
        //Load KeyStore and own Private Key from alias
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream keyStoreInputStream = new FileInputStream(keyStoreFileName);
        BufferedInputStream keyStoreBufferedInputStream = new BufferedInputStream(keyStoreInputStream);
        keyStore.load(keyStoreBufferedInputStream, keyStorePassword);
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(aliasName, privateKeyPassword);

        //Create a Signature object and initialize it with the private key
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(privateKey);

        //Load the file and sign its the data
        FileInputStream fileToSignInputStream = new FileInputStream(fileToSignName);
        BufferedInputStream fileToSignBufferedInputStream = new BufferedInputStream(fileToSignInputStream);
        byte[] buffer = new byte[1024];
        int len;
        while (fileToSignBufferedInputStream.available() != 0) {
            len = fileToSignBufferedInputStream.read(buffer);
            signature.update(buffer, 0, len);
        }
        fileToSignBufferedInputStream.close();

        //Now that all the data to be signed has been read in, generate a signature for it
        byte[] realSig = signature.sign();

        //Save the signature in a file
        FileOutputStream signatureFileOutputStream = new FileOutputStream(signatureFileName);
        signatureFileOutputStream.write(realSig);
        signatureFileOutputStream.close();
    }

}
