package braydo.linktree;

import java.util.List;

/**
 * a class that is design to print the information of a generic type in the ways that it frequently appears
 * @param <T> any common type (such as Int, String) with unique implementation for insights
 */
public interface Translator<T> {
    /**
     * iterates through a list (likely to use printSingle)
     * @param list a list object that must be iterable.
     */
    void printList(List<T> list);

    /**
     * with a single variable, implements a single print
     * @param var a variable in the generic type
     */
    void printSingle(T var);

    /**
     * Prints 1..* variables to the screen
     * @param vars can be one to many
     */
    void printMultiple(T ... vars);
}
