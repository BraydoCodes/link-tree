package braydo.linktree;

import java.util.List;

/**
 * Translates states and messages to print for the graph creation process
 * @param <String>
 */
public class GraphTranslator<String> implements Translator<String>{
    State currentState = State.CREATION;

    public java.lang.String getCurrentState() {
        return currentState.toString() + " | ";
    }
    public void setCurrentState(State newState){
        currentState = newState;
    }

    @Override
    public void printList(List<String> list) {
        System.out.printf(getCurrentState() + "Printing a list the size of: %d%n",list.size());
        for(String str : list)
            System.out.print(getCurrentState()+":  " + str);
    }

    @Override
    public void printSingle(String var) {
        System.out.println(getCurrentState() + var);
    }

    @SafeVarargs
    @Override
    public final void printMultiple(String... vars) {
        for (String v : vars) {
            printSingle(v);
        }
        System.out.println();
    }
}
