package finiteAutomata;

import commons.ITransition;
import commons.State;
import commons.Tuple;

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
    ITransition<State> Transitions = tuple.getTransitions();
    State initialState = tuple.getInitialState();
    Set<State> finalStates = tuple.getFinalStates();
    State nextState = initialState;
    for (String character : inputString) {
      nextState = Transitions.process(nextState, character);
    }
    return finalStates.contains(nextState);
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
