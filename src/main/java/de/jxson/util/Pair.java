package de.jxson.util;

public class Pair<T, V> {

    private T path;
    private V value;

    public Pair(T path, V value) {
        this.path = path;
        this.value = value;
    }

    public T getPath() {
        return path;
    }

    public void setPath(T path) {
        this.path = path;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
