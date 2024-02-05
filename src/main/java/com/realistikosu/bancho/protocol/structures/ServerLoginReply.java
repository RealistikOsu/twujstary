package com.realistikosu.bancho.protocol.structures;

import com.realistikosu.bancho.protocol.io.Reader;
import com.realistikosu.bancho.protocol.io.Writer;
import com.realistikosu.osu.PacketId;

import java.io.IOException;

// TODO: Automatically generate most of this with a processor.
// TODO: Move a lot of this logic to the abstract class.
public class ServerLoginReply extends SerialisablePacket {
    public int loginUserId;

    public ServerLoginReply(int loginUserId) {
        this.loginUserId = loginUserId;
    }

    public ServerLoginReply() {}

    @Override
    public SerialisablePacket read(Reader reader) throws IOException {
        return null;
    }

    @Override
    public PacketId packetId() {
        return PacketId.SRV_LOGIN_REPLY;
    }
}
