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
    public void read(Reader reader) throws IOException {
        this.loginUserId = reader.readInt();
    }

    @Override
    public PacketId packetId() {
        return PacketId.SRV_LOGIN_REPLY;
    }

    @Override
    public Writer write() throws IOException {
        Writer writer = new Writer();

        // Header
        writer.writeShort(
                packetId().getValue()
        );
        writer.writeByte((byte)0);
        writer.writeInt(minimumLength());

        // Body
        writer.writeInt(this.loginUserId);

        return writer;
    }

    @Override
    public int minimumLength() {
        return 4;
    }
}
