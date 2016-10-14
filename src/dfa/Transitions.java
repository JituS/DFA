package dfa;

import java.util.HashMap;
import java.util.Map;

public class Transitions {

  private Map<State, Map<String, State>> transitions = new HashMap<>();
  public void setTransition(State state1, State state2, String alphabet) {
    transitions.putIfAbsent(state1, new HashMap<>());
    transitions.get(state1).put(alphabet, state2);
  }

  public State process(State state, String alphabet) {
    if(transitions.containsKey(state)){
      return transitions.get(state).get(alphabet);
    }
    return null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Transitions that = (Transitions) o;
    return transitions != null ? transitions.equals(that.transitions) : that.transitions == null;
  }

  @Override
  public int hashCode() {
    return transitions != null ? transitions.hashCode() : 0;
  }
}
