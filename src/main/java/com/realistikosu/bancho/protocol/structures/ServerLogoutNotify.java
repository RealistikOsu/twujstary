package com.realistikosu.bancho.protocol.structures;

import com.realistikosu.osu.PacketId;

public class ServerLogoutNotify extends SerialisablePacket {
    int userId;

    public ServerLogoutNotify(int userId) {
        this.userId = userId;
    }

    @Override
    public PacketId packetId() {
        return PacketId.SRV_USER_LOGOUT;
    }
}
