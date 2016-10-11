import java.util.ArrayList;

class Dfa{
  private Tuple<ArrayList<String>, ArrayList<String>, Transitions, String, ArrayList<String>> tuple;
  Dfa(Tuple<ArrayList<String>, ArrayList<String>, Transitions, String, ArrayList<String>> tuple) {
    this.tuple = tuple;
  }
  boolean process(){
    ArrayList<String> alphabets = tuple.getSecond();
    Transitions transitions = tuple.getThird();
    String nextState = tuple.getFourth();
    ArrayList<String> finalStates = tuple.getFifth();

    for (String alphabet : alphabets) {
      nextState = transitions.process(nextState, alphabet);
    }
    return finalStates.contains(nextState);
  }
}
