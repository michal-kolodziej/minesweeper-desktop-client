package com.gomora.typeclicker.connection;

public class OutgoingMessageFactory {
    public static String mousePos(int mouseX, int mouseY) {
        return "MOUSEPOS,X," + mouseX + ",Y," + mouseY;
    }

    public static String click(int cellX, int cellY){
        return "CLICK,X," + cellX + ",Y," + cellY;
    }

    public static String flag(int cellX, int cellY){
        return "FLAG,X," + cellX + ",Y," + cellY;
    }
}
