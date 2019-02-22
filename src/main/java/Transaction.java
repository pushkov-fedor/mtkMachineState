public class Transaction {
    private int startState;
    private String cymbol;
    private int finishState;

    public Transaction(int startState, String cymbol, int finishState){
        this.startState = startState;
        this.cymbol = cymbol;
        this.finishState = finishState;
    }

    public int getStartState() {
        return startState;
    }

    public String getCymbol() {
        return cymbol;
    }

    public int getFinishState() {
        return finishState;
    }

}
