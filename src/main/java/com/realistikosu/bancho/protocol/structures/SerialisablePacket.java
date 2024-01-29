package com.realistikosu.bancho.protocol.structures;

import com.realistikosu.bancho.protocol.io.Writer;
import com.realistikosu.bancho.protocol.io.Reader;

public abstract class SerialisablePacket {
    public SerialisablePacket(Reader reader) {}

    public abstract Writer write();
}
