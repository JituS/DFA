import java.util.ArrayList;
import java.util.List;

class Dfa{

  private Tuple<List<State>, ArrayList<String>, Transitions, State, List<State>> tuple;

  Dfa(Tuple<List<State>, ArrayList<String>, Transitions, State, List<State>> tuple) {
    this.tuple = tuple;
  }
  boolean process(ArrayList<String> inputString) throws InvalidStringException {
    ArrayList<String> alphabets = tuple.getSecond();
    Transitions transitions = tuple.getThird();
    State nextState = tuple.getFourth();
    List<State> finalStates = tuple.getFifth();

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
