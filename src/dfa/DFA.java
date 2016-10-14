package dfa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DFA implements FiniteAutomata {
  private Tuple tuple;
  private String name;
  private String type;
  private ArrayList<String> passCases;
  private ArrayList<String> failCases;


  public DFA(Tuple tuple, String name, String type, ArrayList<String> passCases, ArrayList<String> failCases) {
    this.tuple = tuple;
    this.name = name;
    this.type = type;
    this.passCases = passCases;
    this.failCases = failCases;
  }

  public ArrayList<String> getPassCases() {
    return passCases;
  }

  public ArrayList<String> getFailCases() {
    return failCases;
  }

  public boolean verify(List<String> inputString) {
    ArrayList<String> alphabets = tuple.getAlphabets();
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

  private boolean isStringValid(List<String> inputString, ArrayList<String> alphabets) {
    return alphabets.containsAll(inputString);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DFA that = (DFA) o;
    return tuple != null ?
      tuple.equals(that.tuple) : that.tuple == null && (name != null
      ? name.equals(that.name) : that.name == null && (type != null
      ? type.equals(that.type) : that.type == null && (passCases != null
      ? passCases.equals(that.passCases) : that.passCases == null && (failCases != null
      ? failCases.equals(that.failCases) : that.failCases == null))));
  }

  @Override
  public int hashCode() {
    int result = tuple != null ? tuple.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (type != null ? type.hashCode() : 0);
    result = 31 * result + (passCases != null ? passCases.hashCode() : 0);
    result = 31 * result + (failCases != null ? failCases.hashCode() : 0);
    return result;
  }


  public String getName() {
    return name;
  }
}
