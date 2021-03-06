package finiteAutomata;

import com.thoughtworks.testrunner.FiniteAutomata;
import commons.ITransition;
import commons.State;
import commons.States;
import commons.Tuple;

public class DFA implements FiniteAutomata {
  private Tuple<State> tuple;
  private String name;

  public DFA(Tuple<State> tuple, String name) {
    this.tuple = tuple;
    this.name = name;
  }

  @Override
  public boolean verify(String inputString) {
    String[] split = inputString.split("");
    ITransition<State> transitions = tuple.getTransitions();
    State initialState = tuple.getInitialState();
    States finalStates = tuple.getFinalStates();
    State nextState = initialState;
    for (String character : split) {
      nextState = transitions.process(nextState, character);
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
