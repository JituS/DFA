import java.util.ArrayList;

class Dfa {
  private Tuple tuple;
  Dfa(Tuple tuple) {
    this.tuple = tuple;
  }
  boolean process(){
    String nextState = (String) tuple.getFourth();
    ArrayList<String> finalStates = (ArrayList<String>) tuple.getFifth();
    Transitions transitions = (Transitions) tuple.getThird();
    ArrayList<String> alphabets = (ArrayList<String>) tuple.getSecond();
    for (String alphabet : alphabets) {
      nextState = transitions.process(nextState, alphabet);
    }
    return finalStates.contains(nextState);
  }
}
