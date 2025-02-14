package com.gomora.typeclicker.connection;

public class MousePosIncomingMessage {
    private final int x;
    private final int y;
    private final String clientId;

    public MousePosIncomingMessage(int x, int y, String clientId) {
        this.x = x;
        this.y = y;
        this.clientId = clientId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getClientId() {
        return clientId;
    }
}
