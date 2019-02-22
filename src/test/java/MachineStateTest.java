import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MachineStateTest {

    @Test
    public void start() throws IOException {
        MachineState machineState = new MachineState();
        machineState.configure("machineConfig.txt", "tape.txt");
        assertEquals(1, machineState.start());
    }
}