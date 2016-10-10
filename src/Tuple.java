import java.util.ArrayList;

class Tuple {
  private ArrayList<String> states;
  private ArrayList<String> alphabets;
  private Transitions transitions;
  private String initialState;
  ArrayList<String> finalStates;

  Tuple(ArrayList<String> states, ArrayList<String> alphabets, Transitions transitions, String initialState, ArrayList<String> finalStates) {
    this.states = states;
    this.alphabets = alphabets;
    this.transitions = transitions;
    this.initialState = initialState;
    this.finalStates = finalStates;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Tuple tuple = (Tuple) o;

    return getStates() != null ? getStates().equals(tuple.getStates()) : tuple.getStates() == null &&
      (getAlphabets() != null ? getAlphabets().equals(tuple.getAlphabets()) : tuple.getAlphabets() == null &&
        (getTransitions() != null ? getTransitions().equals(tuple.getTransitions()) : tuple.getTransitions() == null && (
          getInitialState() != null ? getInitialState().equals(tuple.getInitialState()) : tuple.getInitialState() == null &&
            (finalStates != null ? finalStates.equals(tuple.finalStates) : tuple.finalStates == null))));

  }

  @Override
  public int hashCode() {
    int result = getStates() != null ? getStates().hashCode() : 0;
    result = 31 * result + (getAlphabets() != null ? getAlphabets().hashCode() : 0);
    result = 31 * result + (getTransitions() != null ? getTransitions().hashCode() : 0);
    result = 31 * result + (getInitialState() != null ? getInitialState().hashCode() : 0);
    result = 31 * result + (finalStates != null ? finalStates.hashCode() : 0);
    return result;
  }

  private ArrayList<String> getStates() {
    return states;
  }

  ArrayList<String> getAlphabets() {
    return alphabets;
  }

  Transitions getTransitions() {
    return transitions;
  }

  String getInitialState() {
    return initialState;
  }

}
