package com.realistikosu.bancho.protocol.structures;

import com.realistikosu.bancho.protocol.io.Writer;
import com.realistikosu.bancho.protocol.io.Reader;
import com.realistikosu.osu.PacketId;

/**
 * An abstract class defining the API for a packet structure.
 */
public abstract class SerialisablePacket {
    /**
     * Deserialises a bytestream into the packet form.
     * @param reader - A reader with the header read.
     */
    public SerialisablePacket(Reader reader) {}

    /**
     * Writes the given packet with its data into a writer object.
     * @return - A writer with the packet data and the header written.
     */
    public abstract Writer write();

    /**
     * The Packet ID corresponding to the structure.
     * @return - The packet enum.
     */
    public abstract PacketId packetId();

    /**
     * Calculates the minimum size the packet structure can occupy.
     * @return - The minimum size in bytes.
     */
    public abstract int minimumLength();
}
