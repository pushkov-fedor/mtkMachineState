import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
       // Reader reader = new FileReader("machineConfig.txt");
//        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("tape.txt"));
        MachineState machineState = new MachineState();
        machineState.configure("machineConfig.txt", "tape.txt");
        System.out.println(machineState.start());


    }
}
