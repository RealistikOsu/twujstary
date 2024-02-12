package com.realistikosu.bancho.protocol.structures;

import com.realistikosu.osu.PacketId;

public class ServerNotification extends SerialisablePacket {
    String message;

    public ServerNotification(String message) {
        this.message = message;
    }

    @Override
    public PacketId packetId() {
        return PacketId.SRV_NOTIFICATION;
    }
}
