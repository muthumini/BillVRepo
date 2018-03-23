package lk.dialog.BillVerification.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class BVAUtils {

    private final String secretKey = "Bar12345Bar12345"; //this should be 128 Bits
    private final Cipher cipher = Cipher.getInstance("AES");
    private final Key aesKey = new SecretKeySpec(secretKey.getBytes(), "AES");

    private static BVAUtils as2UtilsInstance = null;

    private BVAUtils() throws NoSuchPaddingException, NoSuchAlgorithmException {
    }

    public static BVAUtils getInstance() throws NoSuchAlgorithmException, NoSuchPaddingException {
        if (as2UtilsInstance == null) {
            as2UtilsInstance = new BVAUtils();
        }

        return as2UtilsInstance;
    }

    public String encode(String password) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        synchronized (cipher) {
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            return Base64.encode(cipher.doFinal(password.getBytes()));
        }

    }

    public String decode(String encryptedPassword) throws BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        synchronized (cipher) {
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            return new String(cipher.doFinal(Base64.decode(encryptedPassword)));
        }
    }
}
