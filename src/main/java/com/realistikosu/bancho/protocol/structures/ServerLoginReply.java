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
}
