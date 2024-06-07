package com.gomora.typeclicker.component;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PositionedComponent {
    protected int x;
    protected int y;

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
