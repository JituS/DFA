package commons;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NFATransition extends ITransition<Set<State>> {

  private final String epsilon = "e";
  private Map<State, Map<String, Set<State>>> transitions = new HashMap<>();

  @Override
  public void setTransition(State state1, State state2, String alphabet) {
    transitions.putIfAbsent(state1, new HashMap<>());
    transitions.get(state1).putIfAbsent(alphabet, new HashSet<>());
    transitions.get((state1)).get(alphabet).add(state2);
  }

  @Override
  public Set<State> process(Set<State> states, String alphabet) {
    Set<State> nextStates = processStates(states, alphabet);
    HashSet<State> allEs = new HashSet<>();
    populateAllConnectedEs(nextStates, new State(""), allEs);
    nextStates.addAll(allEs);
    return nextStates;
  }

  private void populateAllConnectedEs(Set<State> allTransitStates, State state, HashSet<State> allConnectedEs) {
    for (State transitState : allTransitStates) {
      try {
        Set<State> allEs = transitions.get(transitState).get(epsilon);
        allEs.remove(state);
        populateAllConnectedEs(allEs, transitState, allConnectedEs);
        allConnectedEs.addAll(allEs);
      } catch (Exception ignored) {
      }
    }
  }

  private Set<State> processStates(Set<State> states, String alphabet) {
    Set<State> transitStates =  new HashSet<>();
    for (State state : states) {
      if (alphabet.equals("")) {
        transitStates.add(state);
        return transitStates;
      }
      Map<String, Set<State>> connectedStates = transitions.get(state);
      try {
        transitStates.addAll(connectedStates.get(alphabet));
      } catch (Exception ignored) {}
    }
    try {
      if(!states.isEmpty()) {
        HashSet<State> allConnectedEs = new HashSet<>();
        populateAllConnectedEs(states, new State(""), allConnectedEs);
        transitStates.addAll(processStates(allConnectedEs, alphabet));
      }
    } catch (Exception ignored) {}
    return transitStates;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NFATransition that = (NFATransition) o;
    return epsilon.equals(that.epsilon) && (transitions != null ? transitions.equals(that.transitions) : that.transitions == null);
  }

  @Override
  public int hashCode() {
    int result = epsilon.hashCode();
    result = 31 * result + (transitions != null ? transitions.hashCode() : 0);
    return result;
  }
}
