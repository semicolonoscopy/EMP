package co.xcvb.emp.message;

/**
 * <p>
 * Interface describes the default behaviour of a message.
 * </p>
 * 
 * @author Calum Gardiner
 * @since 28/10/2014
 * 
 */
public interface Message {

    /**
     * Get the session token for the message.
     * 
     * @return session token
     */
    public String getSessionToken();

    /**
     * Get the message number for this message.
     * 
     * @return message number
     */
    public int getMessageNumber();

    /**
     * Get the body of this message.
     * 
     * @return message body
     */
    public String getMessageBody();

    /**
     * Get the sha1 hash of the session-token+message-number+message-body.
     * 
     * @return sha1 hash
     */
    public String getSha1Hash();

    /**
     * Returns if the message is valid, message is valid if the generated sha1
     * matches the stored sha1.
     * 
     * @return <tt>boolean</tt> message valid
     */
    public boolean isValid();

}
