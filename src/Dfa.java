import java.util.ArrayList;

class Dfa{
  private Tuple<ArrayList<String>, ArrayList<String>, Transitions, String, ArrayList<String>> tuple;
  Dfa(Tuple<ArrayList<String>, ArrayList<String>, Transitions, String, ArrayList<String>> tuple) {
    this.tuple = tuple;
  }
  boolean process(ArrayList<String> inputString) throws InvalidStringException {
    ArrayList<String> alphabets = tuple.getSecond();
    Transitions transitions = tuple.getThird();
    String nextState = tuple.getFourth();
    ArrayList<String> finalStates = tuple.getFifth();
    if(!isStringValid(inputString, alphabets)){
      throw new InvalidStringException();
    }
    for (String alphabet : inputString) {
      nextState = transitions.process(nextState, alphabet);
    }
    return finalStates.contains(nextState);
  }

  private boolean isStringValid(ArrayList<String> inputString, ArrayList<String> alphabets) {
    return alphabets.containsAll(inputString);
  }
}
