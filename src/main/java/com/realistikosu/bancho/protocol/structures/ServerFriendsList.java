package com.realistikosu.bancho.protocol.structures;

import com.realistikosu.osu.PacketId;

public class ServerFriendsList extends SerialisablePacket {
    int[] friendUserIds;

    public ServerFriendsList(int[] friendUserIds) {
        this.friendUserIds = friendUserIds;
    }

    @Override
    public PacketId packetId() {
        return PacketId.SRV_FRIENDS_LIST;
    }
}
