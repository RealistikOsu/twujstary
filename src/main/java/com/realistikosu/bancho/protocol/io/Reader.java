package com.realistikosu.bancho.protocol.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

// A class supporting the reading of an
public class Reader {
    private final DataInputStream _reader;

    public Reader(InputStream stream) {
        _reader = new DataInputStream(stream);
    }

    public void skip(long amount) throws IOException {
        _reader.skip(amount);
    }

    public long readUleb128() throws IOException {
        long result = 0;
        long shift = 0;
        byte b = 0;

        while (true) {
            b = readByte();
            result |= (long)(b & 0b01111111) << shift;

            if ((b & 0b10000000) == 0) {
                break;
            }

            shift += 7;
        }

        return result;
    }

    public String readString() throws IOException {
        if (readByte() != 0xb) {
            return "";
        }

        long length = readUleb128();

        return new String(_reader.readNBytes((int)length));
    }

    public byte readByte() throws IOException {
        return _reader.readByte();
    }

    public short readShort() throws IOException {
        return _reader.readShort();
    }

    public int readInt() throws IOException {
        return _reader.readInt();
    }

    public long readLong() throws IOException {
        return _reader.readLong();
    }

    // Unsigned copium: https://stackoverflow.com/a/7830654
    public int readUnsignedShort() throws IOException {
        int res = (int) readShort();

        return res & 0xFFFF;
    }

    public long readUnsignedInt() throws IOException {
        long res = (long) readInt();

        return res & 0xFFFFFFFFL;
    }

    public boolean readBool() throws IOException {
        return readByte() == 1;
    }

    public boolean isExhausted() throws IOException {
        return _reader.available() != 0;
    }
}
