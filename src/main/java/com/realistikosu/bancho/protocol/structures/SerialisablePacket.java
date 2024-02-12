package com.realistikosu.bancho.protocol.structures;

import com.realistikosu.bancho.protocol.io.Writer;
import com.realistikosu.bancho.protocol.io.Reader;
import com.realistikosu.osu.PacketId;
import com.squareup.javapoet.MethodSpec;

import java.io.IOError;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * An abstract class defining the API for a packet structure.
 */
public abstract class SerialisablePacket {
    public SerialisablePacket() {

    }
    /**
     * Deserialises a byte stream into the packet form.
     * @param reader - A reader with the header read.
     */
    public void read(Reader reader) throws IOException {
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field f : fields) {
            Class type = f.getType();

            try {
                // YandereDev tribute
                if (type == byte.class)
                    f.set(this, reader.readByte());
                else if (type == short.class)
                    f.set(this, reader.readShort());
                else if (type == int.class)
                    f.set(this, reader.readInt());
                else if (type == long.class)
                    f.set(this, reader.readLong());
                else if (type == float.class)
                    f.set(this, reader.readFloat());
                else if (type == String.class)
                    f.set(this, reader.readString());
                else if (type == int[].class)
                    f.set(this, reader.readIntList())
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Writes the given packet with its data into a writer object.
     * @return - A writer with the packet data and the header written.
     */
    public Writer write() throws IOException {
        Writer writer = new Writer();
        writer.writeShort(packetId().getValue());
        writer.writeByte((byte) 0); // Pad
        writer.writeInt(currentLength());

        // Write body.
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field f : fields) {
            Class type = f.getType();

            try {
                Object value = f.get(this);
                if (type == boolean.class) {
                    writer.writeBoolean(
                            (boolean)value
                    );
                } else if (type == byte.class) {
                    writer.writeByte(
                            (byte)value
                    );
                } else if (type == short.class) {
                    writer.writeShort(
                            (short)value
                    );
                } else if (type == int.class) {
                    writer.writeInt(
                            (int)value
                    );
                } else if (type == long.class) {
                    writer.writeLong(
                            (long)value
                    );
                } else if (type == float.class) {
                    writer.writeFloat(
                            (float)value
                    );
                } else if (type == String.class) {
                    writer.writeString(
                            (String)value
                    );
                } else if (type == int[].class) {
                    writer.writeIntList(
                            (int[])value
                    );
                }
            } catch (IllegalAccessException | IOError e) {
                throw new RuntimeException(e);
            }
        }

        return writer;
    }

    /**
     * The Packet ID corresponding to the structure.
     * @return - The packet enum.
     */
    public abstract PacketId packetId();

    /**
     * Computes the current size of the packet body as if it were written.
     * @return - The packet body
     */
    public int currentLength() {
        Field[] fields = this.getClass().getDeclaredFields();
        int length = 0;

        for (Field f : fields) {
            Class type = f.getType();

            // I tried making this a switch statement, but it didn't work.
            if (type == boolean.class)
                ++length;
            else if (type == byte.class)
                ++length;
            else if (type == short.class)
                length += 2;
            else if (type == int.class)
                length += 4;
            else if (type == long.class)
                length += 8;
            else if (type == String.class) {
                String writtenString = null;
                try {
                    writtenString = (String)f.get(this);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                double stringLength = (double)writtenString.length();

                // Uleb header size. Borrowed from https://github.com/infernalfire72/bancho-protocol
                length += (int)Math.floor(Math.log(stringLength / 7d) / Math.log(2) + 2d);
                length += (int) stringLength;
            } else if (type == int[].class) {
                int[] writtenList = new int[0];
                try {
                    writtenList = (int[])f.get(this);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                // i16 prefix
                length += 2;
                // i32 list
                length += writtenList.length * 4;
            }
        }

        return length;
    }
}
