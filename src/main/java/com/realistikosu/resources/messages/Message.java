package com.realistikosu.resources.messages;

public abstract class Message {
    int id;
    int senderId;
    String senderName; // We will support customisable identities!!

    MessageType type;

    /**
     * The target of the message. Can be a channel or a UserID.
     * @return - Target, numeric or prefixed with a `#` if a channel.
     */
    public abstract String getTarget();

    public MessageType getType() {
        if (getTarget().charAt(0) == '#')
            return MessageType.Public;
        return MessageType.Private;
    }
}
