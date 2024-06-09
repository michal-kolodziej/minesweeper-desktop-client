package com.gomora.typeclicker;

import lombok.SneakyThrows;
import processing.core.PApplet;

import java.util.Arrays;
import java.util.Optional;

public class GameEntryPoint {


    @SneakyThrows
    public static void main(String[] args) {
        Config myConfig = new Config(
                getRequiredArgument("serverIp", args),
                Integer.parseInt(getRequiredArgument("serverPort", args))
                );
        PApplet gamePApplet = new GamePApplet(myConfig);
        PApplet.runSketch(new String[]{"TypeClicker"}, gamePApplet);
    }

    private static String getRequiredArgument(String argName, String[] args) {
        return findAndParseArgument(argName, args).orElseThrow(() -> new RuntimeException("no argument called "+ argName +" was passed"));
    }

    private static Optional<String> findAndParseArgument(String argNam, String[] args) {
        return Arrays.stream(args)
                .filter(arg -> arg.startsWith("-" + argNam + "="))
                .filter(arg -> arg.split("=").length == 2)
                .map(arg -> arg.split("=")[1])
                .findFirst();
    }
}
