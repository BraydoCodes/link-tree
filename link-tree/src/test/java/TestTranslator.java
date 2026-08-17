import braydo.linktree.*;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestTranslator {

    public GraphTranslator<String> createStringTranslator(){
        return new GraphTranslator<String>();
    }
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
}
