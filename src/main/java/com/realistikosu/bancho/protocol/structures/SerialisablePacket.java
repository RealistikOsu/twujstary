package com.realistikosu.bancho.protocol.structures;

import com.realistikosu.bancho.protocol.io.Writer;
import com.realistikosu.bancho.protocol.io.Reader;
import com.realistikosu.osu.PacketId;

import java.io.IOException;

/**
 * An abstract class defining the API for a packet structure.
 */
public abstract class SerialisablePacket {
    /**
     * Deserialises a byte stream into the packet form.
     * @param reader - A reader with the header read.
     */
    public abstract SerialisablePacket read(Reader reader) throws IOException;

    /**
     * Writes the given packet with its data into a writer object.
     * @return - A writer with the packet data and the header written.
     */
    public Writer write() throws IOException {
        Writer writer = new Writer();
        writer.writeShort(packetId().getValue());
        writer.writeByte((byte) 0); // Pad
        writer.writeInt(currentLength());

        // Read body.

        return writer;
    }

    /**
     * The Packet ID corresponding to the structure.
     * @return - The packet enum.
     */
    public abstract PacketId packetId();

    /**
     * Calculates the minimum size the packet body can occupy.
     * @return - The minimum size in bytes.
     */
    public static int minimumLength() {

    }

    /**
     * Computes the current size of the packet body as if it were written.
     * @return - The packet body
     */
    public int currentLength() {

    }
}
