import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class SMD5 {
    public static final int MD5_LENGTH = 16;

    //パスワードの比較
    public static boolean comparePassword(byte[] receivedPassword,
            byte[] encryptedStoredPassword) {
        int saltLength = encryptedStoredPassword.length - MD5_LENGTH;
        byte[] hashedPassword = new byte[MD5_LENGTH];
        byte[] salt = new byte[saltLength];
        split(encryptedStoredPassword, 0, hashedPassword, salt);

        byte[] encryptedReceivedPassword = encryptPassword(receivedPassword, salt);
        return Arrays.equals(encryptedReceivedPassword, encryptedStoredPassword);
    }
    
    //パスワードにソルトを加えた物を返す
    public static byte[] encryptPassword(String password) {
        return encryptPassword(password.getBytes(), null);
    }

    //ソルトの生成付与
    public static byte[] encryptPassword(byte[] password, byte[] salt) {
        if (salt == null) {
            salt = new byte[8];
            new SecureRandom().nextBytes(salt);
        }
        byte[] hashedPassword = digest(password, salt);
        byte[] hashedPasswordWithSalt = new byte[hashedPassword.length + salt.length];
        merge(hashedPasswordWithSalt, hashedPassword, salt);
        return hashedPasswordWithSalt;
    }

    private static byte[] digest(byte[] password, byte[] salt) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

        if (salt != null) {
            md.update(salt);
        }
        md.update(password);
        return md.digest();
    }

    private static void merge(byte[] all, byte[] left, byte[] right) {
        System.arraycopy(left, 0, all, 0, left.length);
        System.arraycopy(right, 0, all, left.length, right.length);
    }

    private static void split(byte[] all, int offset, byte[] left, byte[] right) {
        System.arraycopy(all, offset, left, 0, left.length);
        System.arraycopy(all, offset + left.length, right, 0, right.length);
    }

}