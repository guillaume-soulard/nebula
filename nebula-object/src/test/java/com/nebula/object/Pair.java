package com.nebula.object;

public class Pair<RIGHT, LEFT> {

    private RIGHT right;

    private LEFT left;

    public Pair() {
    }

    public Pair(RIGHT right, LEFT left) {
        this.right = right;
        this.left = left;
    }

    public RIGHT getRight() {
        return right;
    }

    public LEFT getLeft() {
        return left;
    }
}
