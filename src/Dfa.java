import java.util.ArrayList;
import java.util.HashSet;

class Dfa {
  private Tuple tuple;
  Dfa(Tuple tuple) {
    this.tuple = tuple;
  }

  boolean process(){
    String nextState = tuple.getInitialState();
    HashSet<String> finalStates = tuple.getFinalStates();
    Transitions transitions = tuple.getTransitions();
    ArrayList<String> alphabets = tuple.getAlphabets();
    for (String alphabet : alphabets) {
      nextState = transitions.process(nextState, alphabet);
    }
    return finalStates.contains(nextState);
  }
}
