package commons;

import java.util.List;
import java.util.Set;

public class Tuple {
  private Set<State> states;
  private List<String> alphabets;
  private ITransition transitions;
  private State initialState;
  private Set<State> finalStates;

  public Tuple(Set<State> states, List<String> alphabets, ITransition transitions, State initialState, Set<State> finalStates){
    this.states = states;
    this.alphabets = alphabets;
    this.transitions = transitions;
    this.initialState = initialState;
    this.finalStates = finalStates;
  }

  public ITransition getTransitions() {
    return transitions;
  }

  public State getInitialState() {
    return initialState;
  }

  public Set<State> getFinalStates() {
    return finalStates;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tuple tuple = (Tuple) o;
    return states != null ? states.equals(tuple.states) : tuple.states == null && (alphabets != null
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
