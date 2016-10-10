import java.util.ArrayList;

class Dfa {
  private Tuple tuple;
  Dfa(Tuple tuple) {
    this.tuple = tuple;
  }

  boolean process(){
    String nextState = tuple.getInitialState();
    ArrayList<String> finalStates = tuple.finalStates;
    Transitions transitions = tuple.getTransitions();
    ArrayList<String> alphabets = tuple.getAlphabets();
    for (String alphabet : alphabets) {
      nextState = transitions.process(nextState, alphabet);
    }
    return finalStates.contains(nextState);
  }
}
