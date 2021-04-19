package com.diamond.kinetics.swing;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum  Column {

    TIMESTAMP("timestamp"),
    AX("ax"),
    AY("ay"),
    AZ("az"),
    WX("wx"),
    WY("wy"),
    WZ("wz");

    @Getter
    private final String columnName;
}
