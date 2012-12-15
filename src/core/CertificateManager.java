package core;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;

/**
 * Created with IntelliJ IDEA.
 */
public class CertificateManager {

    public static void importCertificate(String keyStoreFileName, String certificateFileName, String aliasName, char[] keyStorePassword) throws Exception {
        //Load KeyStore
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream keyStoreInputStream = new FileInputStream(keyStoreFileName);
        BufferedInputStream keyStoreBufferedInputStream = new BufferedInputStream(keyStoreInputStream);
        keyStore.load(keyStoreBufferedInputStream, keyStorePassword);

        //Load the certificate (in X.509 DER encoding).
        FileInputStream certificateStream = new FileInputStream(certificateFileName);
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        java.security.cert.Certificate certificate = certificateFactory.generateCertificate(certificateStream);
        certificateStream.close();

        //Add the new entry with someone's certificate
        keyStore.setCertificateEntry(aliasName, certificate);

        //Write out the KeyStore
        FileOutputStream keyStoreOutputStream = new FileOutputStream(keyStoreFileName);
        keyStore.store(keyStoreOutputStream, keyStorePassword);
        keyStoreOutputStream.close();
    }

}
