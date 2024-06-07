package com.gomora.typeclicker;

import lombok.SneakyThrows;
import processing.core.PApplet;

public class GameEntryPoint {


    @SneakyThrows
    public static void main(String[] args) {
        PApplet gamePApplet = new GamePApplet();
        PApplet.runSketch(new String[]{"TypeClicker"}, gamePApplet);
    }
}
