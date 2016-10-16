package dfa;

import java.util.*;

public class Transitions {

//  private Map<State, Map<String, State>> transitions = new HashMap<>();
//  public void setTransition(State state1, State state2, String alphabet) {
//    transitions.putIfAbsent(state1, new HashMap<>());
//    transitions.get(state1).put(alphabet, state2);
//  }
//
//  public State process(State state, String alphabet) {
//    if(transitions.containsKey(state)){
//      return transitions.get(state).get(alphabet);
//    }
//    return null;
//  }


  private Map<State, Map<String, Set<State>>> transitions = new HashMap<>();

  public void setTransition(State state1, State state2, String alphabet) {
    transitions.putIfAbsent(state1, new HashMap<>());
    transitions.get(state1).putIfAbsent(alphabet, new HashSet<>());
    transitions.get((state1)).get(alphabet).add(state2);
  }

  public Set<State> process(Set<State> states, String alphabet) {
    HashSet<State> transitStates = new HashSet<>();
    for (State state : states) {
      if (transitions.containsKey(state)) {
        processEStates(states, alphabet, transitStates);
      }
    }
    return transitStates;
  }

  private Set<State> processEStates(Set<State> eStates, String alphabet, Set<State> transitStates) {
    for (State eState : eStates) {
      if(alphabet.equals("")) transitStates.add(eState);
      Map<String, Set<State>> allTransitStates = this.transitions.get(eState);
      if(allTransitStates != null) {
        Set<State> transitions = allTransitStates.get(alphabet);
        if(transitions != null) transitStates.addAll(transitions);
        Set<State> otherEStates = allTransitStates.get("e");
        if (otherEStates != null){
          processEStates(otherEStates, alphabet, transitStates);
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
