package braydo.linktree;

import java.util.List;

public interface Translator<T> {
    void printList(List<T> list);
    void printSingle(T var);
    void printMultiple(T ... vars);
}
