package com.realistikosu.bancho.protocol.structures;

import com.realistikosu.osu.PacketId;


public class ServerLoginReply extends SerialisablePacket {
    public int loginUserId;

    public ServerLoginReply(int loginUserId) {
        this.loginUserId = loginUserId;
    }

    public ServerLoginReply() {}

    @Override
    public PacketId packetId() {
        return PacketId.SRV_LOGIN_REPLY;
    }

    // Common builders.
    public static ServerLoginReply loginFailed() {
        return new ServerLoginReply(-1);
    }

    public static ServerLoginReply insufficientPrivileges() {
        return new ServerLoginReply(-3);
    }
}
