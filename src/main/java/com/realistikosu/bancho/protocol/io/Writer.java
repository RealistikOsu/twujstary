package com.realistikosu.bancho.protocol.io;

import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

// A class managing the writing of a byte stream following the "osu!" protocol.
public class Writer {
    private final ByteArrayOutputStream _out;
    private final DataOutputStream _writer;

    public Writer() {
        _out = new ByteArrayOutputStream();
        _writer = new DataOutputStream(
                _out
        );
    }

    public void writeUleb128(long value) throws IOException {
        if (value == 0) {
            _writer.writeByte(0);
            return;
        }

        int temp_val;

        while (value != 0) {
            temp_val = (int) (value & 0b1111111);

            value >>= 7;

            if (value != 0) {
                temp_val |= 0b10000000;
            }

            _writer.writeByte(temp_val);
        }
    }

    public void writeIntList(ArrayList<Integer> numbers) throws IOException {
        writeShort((short)numbers.size());

        for (int number : numbers) {
            writeInt(number);
        }

    }

    public void writeIntList(int[] numbers) throws IOException {
        writeShort((short)numbers.length);

        for (int number : numbers) {
            writeInt(number);
        }

    }

    /**
     * Writes a string `value` onto the buffer, adding the required metadata
     * for "osu!" to be able to read it.
     * @param value - The string to write onto the buffer.
     * @throws IOException - TODO
     */
    public void writeString(String value) throws IOException {
        if (value.isEmpty()) {
            _writer.writeByte(0x0);
            return;
        }

        _writer.writeByte(0xb);
        writeUleb128(value.length());
        _writer.writeBytes(value);
    }

    // Primitive types.
    public void writeByte(byte value) throws IOException {
        _writer.writeByte(value);
    }

    public void writeShort(short value) throws IOException {
        _writer.writeShort(value);
    }

    public void writeInt(int value) throws IOException {
        _writer.writeInt(value);
    }

    public void writeLong(long value) throws IOException {
        _writer.writeLong(value);
    }

    // Dealing with unsigned types: https://stackoverflow.com/a/7830654
    public void writeUnsignedShort(int value) throws IOException {
        _writer.writeShort((short) value);
    }

    public void writeUnsignedInt(long value) throws IOException {
        _writer.writeInt((int) value);
    }

    /**
     * INCORRECTLY writes a U64. This function breaks for values between the
     * i64 and u64 limits.
     */
    public void writeUnsignedLong(long value) throws IOException {
        _writer.writeLong(value);
    }

    public void writeF32(float value) throws IOException {
        _writer.writeFloat(value);
    }
}
