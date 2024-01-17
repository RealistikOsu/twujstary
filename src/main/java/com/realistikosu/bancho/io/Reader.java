package com.realistikosu.bancho.io;

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
            b = readU8();
            result |= (long)(b & 0b01111111) << shift;

            if ((b & 0b10000000) == 0) {
                break;
            }

            shift += 7;
        }

        return result;
    }

    public String readString() throws IOException {
        if (readU8() != 0xb) {
            return "";
        }

        long length = readUleb128();

        return new String(_reader.readNBytes((int)length));
    }

    public byte readU8() throws IOException {
        return _reader.readByte();
    }

    public short readI16() throws IOException {
        return _reader.readShort();
    }

    public int readI32() throws IOException {
        return _reader.readInt();
    }

    public long readI64() throws IOException {
        return _reader.readLong();
    }

    // Unsigned copium: https://stackoverflow.com/a/7830654
    public int readU16() throws IOException {
        int res = (int)readI16();

        return res & 0xFFFF;
    }

    public long readU32() throws IOException {
        long res = (long)readI32();

        return res & 0xFFFFFFFFL;
    }

    public boolean isExhausted() throws IOException {
        return _reader.available() != 0;
    }
}
