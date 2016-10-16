package dfa;

import java.util.*;

public class Transitions {

  private Map<State, Map<String, Set<State>>> transitions = new HashMap<>();

  public void setTransition(State state1, State state2, String alphabet) {
    transitions.putIfAbsent(state1, new HashMap<>());
    transitions.get(state1).putIfAbsent(alphabet, new HashSet<>());
    transitions.get((state1)).get(alphabet).add(state2);
  }

  public Set<State> process(Set<State> states, String alphabet) {
    HashSet<State> transitStates = new HashSet<>();
    states.stream().filter(state -> transitions.containsKey(state)).forEach(state -> processStates(states, alphabet, transitStates));
    Set<State> eRelatedStates = new HashSet<>();
    appendAllEStates(transitStates, eRelatedStates);
    transitStates.addAll(eRelatedStates);
    return transitStates;
  }

  private void appendAllEStates(Set<State> transitStates, Set<State> eRelatedStates) {
    for (State transitState : transitStates) {
      Map<String, Set<State>> eStates = transitions.get(transitState);
      if(eStates != null){
        Set<State> allEs = eStates.get("e");
        if(allEs != null){
          eRelatedStates.addAll(allEs);
          appendAllEStates(allEs, eRelatedStates);
        }
      }
    }
  }

  private Set<State> processStates(Set<State> states, String alphabet, Set<State> transitStates) {
    for (State state : states) {
      if(alphabet.equals("")) transitStates.add(state);
      Map<String, Set<State>> possibleTransitions = this.transitions.get(state);
      if(possibleTransitions != null) {
        Set<State> transitions = possibleTransitions.get(alphabet);
        if(transitions != null) transitStates.addAll(transitions);
        Set<State> allEStates = possibleTransitions.get("e");
        if (allEStates != null){
          processStates(allEStates, alphabet, transitStates);
        }
      }
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
