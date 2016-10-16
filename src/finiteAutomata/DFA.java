package finiteAutomata;

import commons.State;
import commons.Transitions;
import commons.Tuple;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DFA implements FiniteAutomata {
  private Tuple tuple;
  private String name;

  public DFA(Tuple tuple, String name) {
    this.tuple = tuple;
    this.name = name;
  }

  @Override
  public boolean verify(List<String> inputString) {
    Transitions Transitions = tuple.getTransitions();
    State initialState = tuple.getInitialState();
    Set<State> finalStates = tuple.getFinalStates();
    Set<State> nextStates = new HashSet<State>(){{add(initialState);}};
    for (String character : inputString) {
      nextStates = Transitions.process(nextStates, character);
    }
    nextStates.retainAll(finalStates);
    return (nextStates.size() != 0) ;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DFA dfa = (DFA) o;
    return tuple != null ? tuple.equals(dfa.tuple) : dfa.tuple == null && (name != null ? name.equals(dfa.name) : dfa.name == null);
  }

  @Override
  public int hashCode() {
    int result = tuple != null ? tuple.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }
}