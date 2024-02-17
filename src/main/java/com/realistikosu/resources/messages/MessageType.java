package com.realistikosu.resources.messages;

public enum MessageType {
    Private((byte)0),
    Public((byte)1);
    private final byte _value;

    MessageType(byte value) {
        this._value = value;
    }
}
