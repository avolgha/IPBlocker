package dev.marius.ipblocker.common;

public enum Messages {

    START("[IPBlocker] Started Plugin"),
    STOP("[IPBlocker] Stopped Plugin");

    public String data;

    Messages(String arg0) {
        data = arg0;
    }

}
