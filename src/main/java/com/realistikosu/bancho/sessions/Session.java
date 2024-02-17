package com.realistikosu.bancho.sessions;

import com.realistikosu.bancho.sessions.SessionType;
import com.realistikosu.osu.Modes;
import com.realistikosu.osu.Mods;
import com.realistikosu.resources.messages.Message; // TODO: Make a message interaction.
import com.realistikosu.bancho.interactions.Notification;

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
    public abstract void receiveMessage(Message message);
    public abstract void receiveNotification(Notification notification);

    /**
     * Notifies the client that the session is invalid
     */
    public abstract void notifyClosed();
}
