package uq.ecosoft.ctrack.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHelper {
    private static final String secretSeed = "harryguthrie007";

    /**
     * Compute the SHA-256 hash of a given string
     * This should be salted first - see other functions in this class
     * @param input the string to hash
     * @return SHA-256 hash of the input string
     */
    public static String computeHash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(input.getBytes("UTF-8"));

            byte[] hashedBytes = digest.digest(input.getBytes());
            return new String(hashedBytes, "UTF-8");
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * Similar to computeHash(), but with additional salt
     * @param input input string to hash
     * @param salt additional salt to be appended to the string
     * @return hashed version of input + salt
     */
    public static String computeHashWithSalt(String input, String salt) {
        return computeHash(input + salt);
    }

    /**
     * Similar to computeHash(), but with additional salt and a secret seed
     * This should be the ideal function for securing passwords
     * @param input input string to hash
     * @param salt additional salt to be appended to the string
     * @return secure hash with input, salt, and secret seed
     */
    public static String computeHashSecretSalt(String input, String salt) {
        return computeHash(input + salt + secretSeed);
    }
}
