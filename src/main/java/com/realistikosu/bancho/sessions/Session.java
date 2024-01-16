package com.realistikosu.bancho.sessions;

import com.realistikosu.bancho.sessions.SessionType;
import com.realistikosu.osu.Modes;
import com.realistikosu.osu.Mods;

/**
 * Represents an active connection to the server that supports a defined set
 * of interactions that are supported by all connections.
 */
public abstract class Session {
    // Getters/Setters

    // Base credentials
    public abstract int getUserId();
    public abstract String getSessionId();
    public abstract String getName();
    public abstract SessionType getSessionType();

    // Extra Metadata
    public abstract char getActionId(); // TODO: Action enum
    public abstract String getActionDescription();
    public abstract void setActionDescription(String description);

    public abstract long getBeatmapId();
    public abstract String getBeatmapMd5();
    public abstract Mods getBeatmapMods();
    public abstract Modes getBeatmapMode();

    public abstract long getRankedScore();
    public abstract long getTotalScore();
    public abstract float getAccuracy();
    public abstract double getPerformancePoints();
    public abstract int getLeaderboardRank();

    public abstract String getCountryCode();
    public abstract float getLongitude();
    public abstract float getLatitude();
    public abstract char getTimeZoneOffset();



    // Interactions
    public abstract void receivePrivateMessage(String message); // TODO: Use a message object or context.
    public abstract void receivePublicMessage(String message); // TODO: Use a message object or context.
    public abstract void receiveNotification(String message); // TODO: Maybe use actual objects here?

    /**
     * Notifies the client that the session is invalid
     */
    public abstract void notifyClosed();
}
