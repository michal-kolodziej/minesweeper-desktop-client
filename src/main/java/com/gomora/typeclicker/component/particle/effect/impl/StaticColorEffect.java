package com.gomora.typeclicker.component.particle.effect.impl;

import com.gomora.typeclicker.component.particle.effect.ColorEffect;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class StaticColorEffect implements ColorEffect {
    private final int r;
    private final int g;
    private final int b;

    @Override
    public void update() {}

    @Override
    public boolean keepParticleAlive() {
        return true;
    }
}
