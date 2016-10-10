import java.util.ArrayList;
import java.util.HashSet;

class Tuple {
  private HashSet<String> states;
  private ArrayList<String> alphabets;
  private Transitions transitions;
  private String initialState;
  private HashSet<String> finalStates;

  Tuple(HashSet<String> states, ArrayList<String> alphabets, Transitions transitions, String initialState, HashSet<String> finalStates) {
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
            (getFinalStates() != null ? getFinalStates().equals(tuple.getFinalStates()) : tuple.getFinalStates() == null))));

  }

  @Override
  public int hashCode() {
    int result = getStates() != null ? getStates().hashCode() : 0;
    result = 31 * result + (getAlphabets() != null ? getAlphabets().hashCode() : 0);
    result = 31 * result + (getTransitions() != null ? getTransitions().hashCode() : 0);
    result = 31 * result + (getInitialState() != null ? getInitialState().hashCode() : 0);
    result = 31 * result + (getFinalStates() != null ? getFinalStates().hashCode() : 0);
    return result;
  }

  private HashSet<String> getStates() {
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

  public HashSet<String> getFinalStates() {
    return finalStates;
  }

}
