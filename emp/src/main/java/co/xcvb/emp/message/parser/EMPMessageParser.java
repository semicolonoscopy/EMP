package co.xcvb.emp.message.parser;

import co.xcvb.emp.message.EMPMessage;

/**
 * <p>
 * Implementation for parsing EMPMessage's.
 * </p>
 * 
 * @author Calum Gardiner
 * @since 28/10/2014
 * @see MessageParser
 *
 */
public class EMPMessageParser implements MessageParser<EMPMessage> {

    private String message;

    public EMPMessageParser(String message) {
        this.message = message;
    }

    /*
     * (non-Javadoc)
     * 
     * @see co.xcvb.emp.message.parser.MessageParser#parseMessage()
     */
    public EMPMessage parseMessage() {
        String sessionToken = "";
        int messageNumber = -1;
        String messageBody = "";
        String sha1Hash = "";
        String[] lines = message.split("\n");
        for (int i = 0; i < lines.length; i++) {
            switch (i) {
                case 0:
                    sessionToken = parseValue(lines[i]).trim();
                    break;
                case 1:
                    messageNumber = Integer.parseInt(parseValue(lines[i]));
                    break;
                case 2:
                    sha1Hash = parseValue(lines[i]).trim();
                    break;
                case 3:
                    messageBody = parseValue(lines[i]);
                    break;
                default:
                    messageBody += "\n" + lines[i];
                    break;
            }
        }
        EMPMessage empMessage = new EMPMessage(sessionToken, messageNumber,
                messageBody, sha1Hash);
        return empMessage;
    }

    private String parseValue(String line) {
        return line.split(":")[1];
    }
}
