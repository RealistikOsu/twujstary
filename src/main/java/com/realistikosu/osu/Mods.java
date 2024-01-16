package com.realistikosu.osu;

public enum Mods {
    NO_MOD(0),
    NO_FAIL(1),
    EASY(2),
    TOUCHSCREEN(4),
    HIDDEN(8),
    HARD_ROCK(16),
    SUDDEN_DEATH(32),
    DOUBLE_TIME(64),
    RELAX(128),
    HALFTIME(256),
    NIGHTCORE(512),
    FLASHLIGHT(1024),
    AUTOPLAY(2048),
    SPUN_OUT(4096),
    AUTOPILOT(8192),
    PERFECT(16384),
    KEY4(32768),
    KEY5(65536),
    KEY6(131072),
    KEY7(262144),
    KEY8(524288),
    FADEIN(1048576),
    RANDOM(2097152),
    CINEMA(4194304),
    TARGET(8388608),
    KEY9(16777216),
    KEY_COOP(33554432),
    KEY1(67108864),
    KEY3(134217728),
    KEY2(268435456),
    SCORE_V2(536870912),
    MIRROR(1073741824);

    private final int _value;

    Mods(int value) {
        _value = value;
    }

    public int getValue() {
        return _value;
    }

    /**
     * Checks whether a single mod or any of the mods given match the mod enum.
     * For exact matches, use `containsMods`.
     * @param m - The mod/mods to compare against this enum.
     * @return - Bool, checking whether at least one of the mods matched.
     */
    public boolean containsMod(Mods m) {
        return (_value & m._value) > 0;
    }

    /**
     * Checks if all the mods present in `m` are present here.
     * @param m - The mod/mods to compare against this enum.
     * @return - Bool, checking whether all the mods matched.
     */
    public boolean containsMods(Mods m) {
        return (_value & m._value) == m._value;
    }
}
