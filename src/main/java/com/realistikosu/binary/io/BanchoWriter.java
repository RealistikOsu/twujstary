package com.realistikosu.binary.io;

import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

// A class managing the writing of a byte stream following the "osu!" protocol.
public class BanchoWriter {
    private ByteArrayOutputStream _out;
    private DataOutputStream _writer;

    public BanchoWriter() {
        _out = new ByteArrayOutputStream();
        _writer = new DataOutputStream(
                _out
        );
    }

    public void write_uleb128(long value) throws IOException {
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

    /**
     * Writes a string `value` onto the buffer, adding the required metadata
     * for "osu!" to be able to read it.
     * @param value - The string to write onto the buffer.
     * @throws IOException - TODO
     */
    public void write_string(String value) throws IOException {
        if (value.isEmpty()) {
            _writer.writeByte(0x0);
            return;
        }

        _writer.writeByte(0xb);
        write_uleb128(value.length());
        _writer.writeBytes(value);
    }

    // Primitive types.
    public void write_u8(int value) throws IOException {
        _writer.writeByte(value);
    }
}
