package com.gomora.typeclicker.component.game;

import com.gomora.typeclicker.component.Button;

public class Cell {
    private final String state;

    private final Button button;

    public Cell(String state, Button button) {
        this.state = state;
        this.button = button;
    }

    public String getState() {
        return state;
    }

    public Button getButton() {
        return button;
    }

}
