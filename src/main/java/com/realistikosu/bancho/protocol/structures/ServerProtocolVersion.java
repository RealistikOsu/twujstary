package com.realistikosu.bancho.protocol.structures;

import com.realistikosu.osu.PacketId;

public class ServerProtocolVersion extends SerialisablePacket {
    int version;

    public ServerProtocolVersion() {}
    public ServerProtocolVersion(int version) {
        this.version = version;
    }

    @Override
    public PacketId packetId() {
        return PacketId.SRV_PROTOCOL_VERSION;
    }
}
