package com.realistikosu.bancho.protocol.processor;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

import com.realistikosu.bancho.protocol.processor.BanchoPacket;

import java.util.Set;

@SupportedAnnotationTypes("com.realistikosu.bancho.protocol.processor.BanchoPacket")
public class BanchoPacketProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }
}
