package com.gomora.typeclicker.connection;

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

public class PlayerCursorPositionManager {
    public Map<String, Pair<Integer, Integer>> clientIdToMousePositionCoordMap = new HashMap<>();

    public void updateCoordsForClient(String clientId, Pair<Integer, Integer> mousePosCoords){
        clientIdToMousePositionCoordMap.put(clientId, mousePosCoords);
    }
}
