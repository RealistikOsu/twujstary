package com.realistikosu.bancho.sessions;

import com.realistikosu.bancho.sessions.SessionType;

/**
 * Represents an active connection to the server that supports a defined set
 * of interactions that are supported by all connections.
 */
public abstract class Session {
    // Getters/Setters
    public abstract int getUserId();
    public abstract String getSessionId();
    public abstract String getName();
    public abstract SessionType getSessionType();


    // Interactions
    public abstract void receivePrivateMessage(String message); // TODO: Use a message object or context.
    public abstract void receivePublicMessage(String message); // TODO: Use a message object or context.

    /**
     * Notifies the client that the session is invalid
     */
    public abstract void notifyClosed();
}
