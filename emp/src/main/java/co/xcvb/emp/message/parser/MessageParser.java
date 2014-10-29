package co.xcvb.emp.message.parser;

import co.xcvb.emp.message.Message;

/**
 * <p>
 * Parses some Message from a value set in the constructor of the
 * implementation.
 * </p>
 * 
 * @author Calum Gardiner
 * @since 28/10/2014
 * @see Message
 *
 * @param <T>
 */
public interface MessageParser<T extends Message> {

    /**
     * Parse message from the input.
     * 
     * @return Message
     */
    public T parseMessage();

}
