package com.realistikosu.osu;

public enum Modes {
    STANDARD(0),
    TAIKO(1),
    CATCH(2),
    MANIA(3);

    private final int _value;

    private Modes(int value) {
        _value = value;
    }

    public int getValue() {
        return _value;
    }
}
