import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class MachineState {

    private ArrayList<Integer> finalStates;
    private ArrayList<Transaction> transactions;
    private int currentState;
    private Queue<String> alphabetTape;

    public MachineState(){
        finalStates = new ArrayList();
        transactions = new ArrayList();
        alphabetTape = new ArrayDeque<>();
        currentState = 0;
    }

    public int start(){
        Iterator<String> iterator = alphabetTape.iterator();
        while(iterator.hasNext()){
            String cymbol = alphabetTape.poll();
            currentState = makeTransaction(currentState, cymbol);
            if(isFinalState(currentState)) return currentState;
        }
        return currentState;
    }

    private boolean isFinalState(int currentState){
        Iterator<Integer> iterator = finalStates.iterator();
        while(iterator.hasNext()){
            int finalState = iterator.next();
            if(currentState == finalState) return true;
        }
        return false;
    }

    private int makeTransaction(int state, String cymbol){
        Iterator<Transaction> iterator = transactions.iterator();
        while(iterator.hasNext()){
            Transaction transaction = iterator.next();
            if(transaction.getStartState() == state && transaction.getCymbol().equals(cymbol)){
                return transaction.getFinishState();
            }
        }
        return state;
    }

    public void configure(String machineConfigFile, String tapeFile) throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(tapeFile).getFile());
        BufferedReader tapeConfig = new BufferedReader(new FileReader(file));
        file = new File(classLoader.getResource(machineConfigFile).getFile());
        BufferedReader machineConfig = new BufferedReader(new FileReader(file));

        String finalStatesStr = machineConfig.readLine();
        for(int i = 0; i < finalStatesStr.length(); i++){
            finalStates.add(Integer.parseInt(Character.toString(finalStatesStr.charAt(i))));
        }
        String transaction;
        while( (transaction = machineConfig.readLine()) != null ){
            int startState = Integer.parseInt(Character.toString(transaction.charAt(0)));
            String cymbol = Character.toString(transaction.charAt(1));
            int finishState = Integer.parseInt(Character.toString(transaction.charAt(2)));
            transactions.add(new Transaction(startState, cymbol, finishState));
        }
        String alphabetTapeStr = tapeConfig.readLine();
        for(int i = 0; i < alphabetTapeStr.length(); i++){
            String cymbol = Character.toString(alphabetTapeStr.charAt(i));
            alphabetTape.offer(cymbol);
        }
    }

}
