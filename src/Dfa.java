import java.util.ArrayList;

public class Dfa {
  private Tuple tuple;
  public Dfa(Tuple tuple) {
    this.tuple = tuple;
  }

  public boolean process(){
    String initialState = tuple.initialState;
    String nextState = initialState;
    ArrayList<String> finalStates = tuple.finalStates;
    Transitions transitions = tuple.transitions;
    ArrayList<String> alphabets = tuple.alphabets;
    for (String alphabet : alphabets) {
      nextState = transitions.process(nextState, alphabet);
    }
    return finalStates.contains(nextState);
  }
}
