import java.util.ArrayList;

class Tuple {
  private ArrayList<String> states;
  ArrayList<String> alphabets;
  Transitions transitions;
  String initialState;
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

    if (states != null ? !states.equals(tuple.states) : tuple.states != null) return false;
    if (alphabets != null ? !alphabets.equals(tuple.alphabets) : tuple.alphabets != null) return false;
    if (transitions != null ? !transitions.equals(tuple.transitions) : tuple.transitions != null) return false;
    if (initialState != null ? !initialState.equals(tuple.initialState) : tuple.initialState != null) return false;
    return finalStates != null ? finalStates.equals(tuple.finalStates) : tuple.finalStates == null;

  }

  @Override
  public int hashCode() {
    int result = states != null ? states.hashCode() : 0;
    result = 31 * result + (alphabets != null ? alphabets.hashCode() : 0);
    result = 31 * result + (transitions != null ? transitions.hashCode() : 0);
    result = 31 * result + (initialState != null ? initialState.hashCode() : 0);
    result = 31 * result + (finalStates != null ? finalStates.hashCode() : 0);
    return result;
  }
}
