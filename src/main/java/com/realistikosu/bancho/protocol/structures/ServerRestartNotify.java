package com.realistikosu.bancho.protocol.structures;

import com.realistikosu.osu.PacketId;

public class ServerRestartNotify extends SerialisablePacket {
    int timeToRelog; // TODO: Figure out the unit lmfaoo

    public ServerRestartNotify(int timeToRelog) {
        this.timeToRelog = timeToRelog;
    }

    @Override
    public PacketId packetId() {
        return PacketId.SRV_RESTART;
    }
}
