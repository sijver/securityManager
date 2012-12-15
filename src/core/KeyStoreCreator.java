package core;

import java.io.FileOutputStream;
import java.security.KeyStore;

/**
 * Created with IntelliJ IDEA.
 */
public class KeyStoreCreator {

    public static void CreateKeyStore(char[] password, String keyStoreFileName) throws Exception {
        //Create KeyStore
        KeyStore keyStore;
        keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, password);

        //Save KeyStore to file
        FileOutputStream keyStoreOutputStream;
        keyStoreOutputStream = new FileOutputStream(keyStoreFileName, false);
        keyStore.store(keyStoreOutputStream, password);
        keyStoreOutputStream.close();
    }

}
