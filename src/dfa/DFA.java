package dfa;

import java.util.List;
import java.util.Set;

public class DFA implements FiniteAutomata {
  private Tuple tuple;
  private String name;
  private String type;


  public DFA(Tuple tuple, String name, String type) {
    this.tuple = tuple;
    this.name = name;
    this.type = type;
  }

  @Override
  public boolean verify(List<String> inputString) {
    List<String> alphabets = tuple.getAlphabets();
    Transitions transitions = tuple.getTransitions();
    State currentState = tuple.getInitialState();
    Set<State> finalStates = tuple.getFinalStates();
    if(!isStringValid(inputString, alphabets)){
      return false;
    }
    State nextState = currentState;
    for (String character : inputString) {
      nextState = transitions.process(nextState, character);
    }
    return finalStates.contains(nextState);
  }

  private boolean isStringValid(List<String> inputString, List<String> alphabets) {
    return alphabets.containsAll(inputString);
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

    if (tuple != null ? !tuple.equals(dfa.tuple) : dfa.tuple != null) return false;
    if (name != null ? !name.equals(dfa.name) : dfa.name != null) return false;
    return type != null ? type.equals(dfa.type) : dfa.type == null;

  }

  @Override
  public int hashCode() {
    int result = tuple != null ? tuple.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (type != null ? type.hashCode() : 0);
    return result;
  }
}
