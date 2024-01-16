package com.realistikosu.osu;

/**
 * Defines the server-level mode implementations.
 */
public enum CustomModes {
    VANILLA(0),
    RELAX(1),
    AUTOPILOT(2);

    private final int _value;

    private CustomModes(int value) {
        _value = value;
    }

    public int get_value() {
        return _value;
    }
}
