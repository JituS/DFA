package commons;

import java.util.*;

class NFATransition extends ITransition<States> {
  private final String epsilon = "e";
  private Map<State, Map<String, States>> transitions = new HashMap<>();

  @Override
  public void setTransition(State state1, State state2, String alphabet) {
    transitions.putIfAbsent(state1, new HashMap<>());
    transitions.get(state1).putIfAbsent(alphabet, new States());
    transitions.get((state1)).get(alphabet).add(state2);
  }

  @Override
  public States process(States states, String alphabet) {
    States nextStates = processStates(states, alphabet);
    States connectedEpsilon = new States();
    populateAllConnectedEs(nextStates, new State(""), connectedEpsilon);
    nextStates.addAll(connectedEpsilon);
    return nextStates;
  }

  private void populateAllConnectedEs(States allTransitStates, State previousState, States allConnectedEs) {
    Iterator<State> stateIterator = allTransitStates.iterator();
    while (stateIterator.hasNext()) {
      State transitState = stateIterator.next();
      try {
        States allEs = transitions.get(transitState).get(epsilon);
        allEs.remove(previousState);
        populateAllConnectedEs(allEs, transitState, allConnectedEs);
        allConnectedEs.addAll(allEs);
      } catch (Exception ignored) {
      }
    }
  }

  private States processStates(States states, String alphabet) {
    if (alphabet.equals("")) return states;
    States transitStates = new States();
    Iterator<State> stateIterator = states.iterator();
    while (stateIterator.hasNext()) {
      State state = stateIterator.next();
      Map<String, States> possibleStates = transitions.get(state);
      try { transitStates.addAll(possibleStates.get(alphabet));}
      catch (Exception ignored) {}
    }
    try {
      if (!states.isEmpty()) {
        States allConnectedEs = new States();
        populateAllConnectedEs(states, new State(""), allConnectedEs);
        transitStates.addAll(processStates(allConnectedEs, alphabet));
      }
    } catch (Exception ignored) {
    }
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
