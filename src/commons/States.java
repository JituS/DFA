package commons;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class States {
  private Set<State> states;

  public States() {
    states = new HashSet<>();
  }

  public void retainAll(States finalStates) {
    states.retainAll(finalStates.states);
  }

  Iterator<State> iterator(){
    return states.iterator();
  }


  public State stateName() {
    StringBuilder stringBuilder = new StringBuilder();
    for (State nState : states) {
      stringBuilder.append(nState.stateName);
    }
    return new State(stringBuilder.toString());
  }


  public boolean containsAny(States anotherStates) {
    for (State state : states) {
      if (anotherStates.contains(state)) return true;
    }
    return false;
  }

  public boolean contains(State nextState) {
    return states.contains(nextState);
  }

  boolean addAll(States anotherStates) {
    return states.addAll(anotherStates.states);
  }

  boolean remove(State state) {
    return states.remove(state);
  }


  boolean isEmpty() {
    return states.isEmpty();
  }

  public int size() {
    return states.size();
  }

  public boolean add(State state) {
    return states.add(state);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    States states1 = (States) o;

    return states != null ? states.equals(states1.states) : states1.states == null;

  }

  @Override
  public int hashCode() {
    return states != null ? states.hashCode() : 0;
  }
}
