package com.realistikosu.bancho.protocol.processor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.realistikosu.bancho.protocol.io.Reader;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface BanchoPacket {
}
