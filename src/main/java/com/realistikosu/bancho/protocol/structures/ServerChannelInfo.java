package com.realistikosu.bancho.protocol.structures;

import com.realistikosu.osu.PacketId;

public class ServerChannelInfo extends SerialisablePacket {
    String name;
    String description;
    short memberCount;


    public ServerChannelInfo(String name, String description, short memberCount) {
        this.name = name;
        this.description = description;
        this.memberCount = memberCount;
    }

    @Override
    public PacketId packetId() {
        return PacketId.SRV_CHANNEL_INFO;
    }
}
