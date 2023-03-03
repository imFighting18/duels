package me.imfighting.duels.nms;

public enum ServerVersion {
    v1_8_R3,
    REFLECTED,
    UNKNOWN;

    @Override
    public String toString() {
        return name();
    }
}
