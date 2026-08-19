import braydo.linktree.*;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestTranslator {

    public GraphTranslator<String> createStringTranslator(){
        return new GraphTranslator<String>();
    }
    @Mock private GraphTranslator<String> graphTranslator;
    private String element1 = "e";
    private String element2 = "f";

    @Test
    public void testStateChange(){
        GraphTranslator<String> graphTranslator = createStringTranslator();
        assertEquals(State.CREATION.toString(),graphTranslator.getCurrentState());

        graphTranslator.setCurrentState(State.WORKING);
        assertEquals(State.WORKING.toString(),graphTranslator.getCurrentState());
        assertNotEquals(State.CREATION.toString(), graphTranslator.getCurrentState());

        graphTranslator.setCurrentState(State.DELETION);
        assertEquals(State.DELETION.toString(),graphTranslator.getCurrentState());
        assertNotEquals(State.WORKING.toString(), graphTranslator.getCurrentState());

    }
        //TODO write mock tests for all void functions
    @Test
    public void testPrintFunctions(){
        graphTranslator = spy(new GraphTranslator<String>());
        graphTranslator.printList(List.of(element1, element2));

        verify(graphTranslator).printSingle(element1);
        verify(graphTranslator).printSingle(element2);
    }
}
