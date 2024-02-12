package com.realistikosu.bancho.protocol.structures;

import com.realistikosu.osu.PacketId;

public class ServerReceiveMessage extends SerialisablePacket {
    String fromName;
    String message;
    String targetName;
    int userId;

    public ServerReceiveMessage(String fromName, String message, String targetName, int userId) {
        this.fromName = fromName;
        this.message = message;
        this.targetName = targetName;
        this.userId = userId;
    }

    @Override
    public PacketId packetId() {
        return PacketId.SRV_SEND_MESSAGE;
    }
}
