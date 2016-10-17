package commons;

import java.util.*;

public class Transitions {

  private final String epsilon = "e";
  private Map<State, Map<String, Set<State>>> transitions = new HashMap<>();

  public void setTransition(State state1, State state2, String alphabet) {
    transitions.putIfAbsent(state1, new HashMap<>());
    transitions.get(state1).putIfAbsent(alphabet, new HashSet<>());
    transitions.get((state1)).get(alphabet).add(state2);
  }

  public Set<State> process(Set<State> states, String alphabet) {
    HashSet<State> allTransitStates = new HashSet<>();
    states.stream().filter(state -> transitions.containsKey(state)).forEach(state -> processStates(states, alphabet, allTransitStates, new State("")));
    Set<State> eRelatedStates = new HashSet<>();
    appendAllEStates(allTransitStates, eRelatedStates);
    allTransitStates.addAll(eRelatedStates);
    return allTransitStates;
  }

  private void appendAllEStates(Set<State> allTransitStates, Set<State> eRelatedStates) {
    for (State transitState : allTransitStates) {
      try {
        Set<State> allEs = transitions.get(transitState).get(epsilon);
        eRelatedStates.addAll(allEs);
        appendAllEStates(allEs, eRelatedStates);
      }catch (Exception ignored){}
    }
  }

  private Set<State> processStates(Set<State> states, String alphabet, Set<State> transitStates, State s) {
    for (State state : states) {
      if(alphabet.equals("")) {transitStates.add(state); return transitStates;}
      try { transitStates.addAll(transitions.get(state).get(alphabet));}
      catch (Exception ignored){}
      try {
        Set<State> epsilonLinked = transitions.get(state).get(epsilon);
        epsilonLinked.remove(s);
        processStates(epsilonLinked, alphabet, transitStates, state);}
      catch (Exception ignored){}
    }
    return transitStates;
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
