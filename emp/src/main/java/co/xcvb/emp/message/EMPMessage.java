package co.xcvb.emp.message;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

/**
 * <p>
 * EMP implementation of Message.
 * </p>
 * 
 * @author Calum Gardiner
 * @since 28/10/2014
 * @see Message
 *
 */
public class EMPMessage implements Message {

    private String sessionToken;
    private int messageNumber;
    private String messageBody;
    private String sha1Hash;

    /**
     * Given the specified sessionToken, messageNumber and messageBody this
     * constructor will generate the sha1.
     * 
     * @param sessionToken
     * @param messageNumber
     * @param messageBody
     */
    public EMPMessage(String sessionToken, int messageNumber, String messageBody) {
        this.sessionToken = sessionToken;
        this.messageNumber = messageNumber;
        this.messageBody = messageBody;
        this.sha1Hash = generateSha1Hash(sessionToken, messageNumber,
                messageBody);
    }

    /**
     * Takes all fields and build the object.
     * 
     * @param sessionToken
     * @param messageNumber
     * @param messageBody
     * @param sha1Hash
     */
    public EMPMessage(String sessionToken, int messageNumber,
            String messageBody, String sha1Hash) {
        this.sessionToken = sessionToken;
        this.messageNumber = messageNumber;
        this.messageBody = messageBody;
        this.sha1Hash = sha1Hash;
    }

    /*
     * (non-Javadoc)
     * 
     * @see co.xcvb.emp.message.Message#getSessionToken()
     */
    public String getSessionToken() {
        return this.sessionToken;
    }

    /*
     * (non-Javadoc)
     * 
     * @see co.xcvb.emp.message.Message#getMessageNumber()
     */
    public int getMessageNumber() {
        return this.messageNumber;
    }

    /*
     * (non-Javadoc)
     * 
     * @see co.xcvb.emp.message.Message#getMessageBody()
     */
    public String getMessageBody() {
        return this.messageBody;
    }

    /*
     * (non-Javadoc)
     * 
     * @see co.xcvb.emp.message.Message#getSha1Hash()
     */
    public String getSha1Hash() {
        return this.sha1Hash;
    }

    /*
     * (non-Javadoc)
     * 
     * @see co.xcvb.emp.message.Message#isValid()
     */
    public boolean isValid() {
        return generateSha1Hash(sessionToken, messageNumber, messageBody)
                .equals(sha1Hash);
    }

    /**
     * Generate a sha1 hash based on the specified paramters.
     * 
     * @param sessionToken
     * @param messageNumber
     * @param messageBody
     * @return sha1 String
     */
    private String generateSha1Hash(String sessionToken, int messageNumber,
            String messageBody) {
        String message = sessionToken + messageNumber + messageBody;
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.reset();
            messageDigest.update(message.getBytes("utf8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new String(Hex.encodeHex(messageDigest.digest()));
    }
}
