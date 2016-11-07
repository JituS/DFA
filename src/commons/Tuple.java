package commons;

import java.util.List;

public class Tuple<T> {
  private States states;
  private List<String> alphabets;
  private ITransition<T> transitions;
  private State initialState;
  private States finalStates;

  public List<String> getAlphabets() {
    return alphabets;
  }

  public States getStates() {
    return states;
  }

  public Tuple(States states, List<String> alphabets, ITransition<T> transitions, State initialState, States finalStates){
    this.states = states;
    this.alphabets = alphabets;
    this.transitions = transitions;
    this.initialState = initialState;
    this.finalStates = finalStates;
  }

  public ITransition<T> getTransitions() {
    return transitions;
  }

  public State getInitialState() {
    return initialState;
  }

  public States getFinalStates() {
    return finalStates;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Tuple tuple = (Tuple) o;

    return states != null
      ? states.equals(tuple.states) : tuple.states == null && (alphabets != null
      ? alphabets.equals(tuple.alphabets) : tuple.alphabets == null && (transitions != null
      ? transitions.equals(tuple.transitions) : tuple.transitions == null && (initialState != null
      ? initialState.equals(tuple.initialState) : tuple.initialState == null && (finalStates != null
      ? finalStates.equals(tuple.finalStates) : tuple.finalStates == null))));

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
