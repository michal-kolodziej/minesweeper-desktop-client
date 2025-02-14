package com.gomora.typeclicker.connection;

import com.gomora.typeclicker.GamePApplet;
import org.apache.commons.lang3.tuple.Pair;
import processing.core.PApplet;

import java.util.HashMap;
import java.util.Map;

public class PlayerCursorPositionManager {
    public Map<String, MousePosition> clientIdToMousePositionCoordMap = new HashMap<>();

    public void updateCoordsForClient(String clientId, Pair<Integer, Integer> mousePosCoords) {
        if (clientIdToMousePositionCoordMap.containsKey(clientId)) {
            MousePosition mousePosition = clientIdToMousePositionCoordMap.get(clientId);
            mousePosition.setX(mousePosCoords.getLeft());
            mousePosition.setY(mousePosCoords.getRight());
        } else {
            clientIdToMousePositionCoordMap.put(clientId, new MousePosition(mousePosCoords.getLeft(), mousePosCoords.getRight()));
        }
    }

    public static class MousePosition {
        int oldX;
        int oldY;

        int newX;
        int newY;

        MousePosition(int x, int y) {
            this.oldX = x;
            this.newX = x;
            this.oldY = y;
            this.newY = y;
        }

        public int getX() {
            oldX = (int) PApplet.lerp(oldX, newX, 0.25f);
            return oldX;
        }

        public int getY() {
            oldY = (int) PApplet.lerp(oldY, newY, 0.25f);
            return oldY;
        }

        void setX(int x) {
            this.newX = x;
        }

        void setY(int y) {
            this.newY = y;
        }
    }
}
