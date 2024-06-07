package com.gomora.typeclicker.connection;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;

public class IncomingMessageParser {
    public static String[][] boardUpdate(String message) {
        String[] split = message.split(",");
        int rows = Integer.parseInt(split[2]);
        int cols = Integer.parseInt(split[4]);
        String[][] board = new String[rows][cols];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = split[row * cols + col + 5];
            }
        }
        return board;
    }

    public static Triple<Integer, Integer, String> mousePos(String message) {
        String[] split = message.split(",");
        int x = Integer.parseInt(split[2]);
        int y = Integer.parseInt(split[4]);
        String clientId = split[6];
        return new ImmutableTriple<>(x, y, clientId);
    }

    public static List<Pair<Integer, Integer>> gameOver(String message) {
        String[] split = message.split(",");
        String username = split[2];
        Pair<Integer, Integer> clickedMineCoords = new ImmutablePair<>(Integer.parseInt(split[4]), Integer.parseInt(split[6]));
        List<Pair<Integer, Integer>> mineLocations = new ArrayList<>();
        for(int mine = 8; mine < split.length; mine +=2){
            mineLocations.add(new ImmutablePair<>(Integer.parseInt(split[mine]), Integer.parseInt(split[mine+1])));
        }
        return mineLocations;
    }
}
