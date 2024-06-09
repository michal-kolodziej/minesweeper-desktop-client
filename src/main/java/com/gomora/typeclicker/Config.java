package com.gomora.typeclicker;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Config {
    private final String serverIp;
    private final int serverPort;
}
