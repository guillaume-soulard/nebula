package com.nebula.object;

public class CustomObject {

    private Pair<Integer, String> pair;

    public Pair<Integer, String> getPair() {
        return pair;
    }

    public CustomObject setPair(Pair<Integer, String> pair) {
        this.pair = pair;
        return this;
    }
}
