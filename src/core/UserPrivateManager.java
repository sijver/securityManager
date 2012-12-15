package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.CertificateFactory;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * Created with IntelliJ IDEA.
 */
public class UserPrivateManager {

    public static void ImportDataToKeyStore(String keyStoreFileName, String certificateFileName, String privateKeyFileName, String aliasName, char[] keyStorePassword, char[] privateKeyPassword) throws Exception {
        //Load the KeyStore
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream keyStoreInputStream = new FileInputStream(keyStoreFileName);
        keyStore.load(keyStoreInputStream, keyStorePassword);
        keyStoreInputStream.close();

        //Load the certificate chain (in X.509 DER encoding).
        FileInputStream certificateStream = new FileInputStream(certificateFileName);
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        java.security.cert.Certificate[] chain = {};
        chain = certificateFactory.generateCertificates(certificateStream).toArray(chain);
        certificateStream.close();

        //Load the Private Key (in PKCS#8 DER encoding).
        File keyFile = new File(privateKeyFileName);
        byte[] encodedKey = new byte[(int) keyFile.length()];
        FileInputStream keyInputStream = new FileInputStream(keyFile);
        keyInputStream.read(encodedKey);
        keyInputStream.close();
        KeyFactory rSAKeyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = rSAKeyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));

        //Add the new entry to KeyStore with own Private Key and Certificate
        keyStore.setEntry(aliasName,
                new KeyStore.PrivateKeyEntry(privateKey, chain),
                new KeyStore.PasswordProtection(privateKeyPassword)
        );

        // Write out the KeyStore
        FileOutputStream keyStoreOutputStream = new FileOutputStream(keyStoreFileName);
        keyStore.store(keyStoreOutputStream, keyStorePassword);
        keyStoreOutputStream.close();
    }

}
