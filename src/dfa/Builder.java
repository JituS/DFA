package dfa;

import org.json.simple.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class Builder {

  private JSONObject jsonObject;

  public Builder(JSONObject jsonObject) {
    this.jsonObject = jsonObject;
  }

//  {\"name\":\"any number of zeroes followed by any number of ones\",
// \"type\":\"nfa\",\"tuple\":{\"states\":[\"q1\",\"q2\"],
// \"alphabets\":[\"1\",\"0\"],
// \"delta\":{\"q1\":{\"e\":[\"q2\"],\"0\":[\"q1\"]},\"q2\":{\"1\":[\"q2\"]}},
// \"start-state\":\"q1\",
// \"final-states\":[\"q2\"]},
// \"pass-cases\":[\"\",\"0\",\"1\",\"00\",\"001\",\"0011\",\"0001\",\"011\",\"000111\"],
// \"fail-cases\":[\"10\",\"1110\",\"010\",\"10101\",\"1101\"]}

  private Tuple mapToTuple(JSONObject tuple, Transitions allTransitions) {
    Set<State> states = ((List<String>) tuple.get("states")).stream().map(State::new).collect(Collectors.toSet());
    List<String> alphabets = (ArrayList<String>) tuple.get("alphabets");
    State initialState = new State((String) tuple.get("start-state"));
    Set<State> finalStates = ((List<String>) tuple.get("final-states")).stream().map(State::new).collect(Collectors.toSet());

    return new Tuple(states, alphabets, allTransitions, initialState, finalStates);
  }

  private Transitions mapToDFATransitions(Map<String, Map<String, String>> transitions) {
    Transitions mappedTransition = new Transitions();
    transitions.entrySet().stream().forEach(m -> {
      State state = new State(m.getKey());
      Map<String, String> transitionsOfState = m.getValue();
      transitionsOfState.forEach((alphabet, nextState) -> mappedTransition.setTransition(state, new State(nextState), alphabet));
    });
    return mappedTransition;
  }

  private Transitions mapToNFATransitions(Map<String, Map<String, ArrayList<String>>> transitions) {
    Transitions mappedTransition = new Transitions();
    transitions.entrySet().stream().forEach(m -> {
      State state = new State(m.getKey());
      Map<String, ArrayList<String>> transitionsOfState = m.getValue();
      transitionsOfState.forEach((alphabet, nextState) -> {
        nextState.forEach(eachState -> mappedTransition.setTransition(state, new State(eachState), alphabet));
      });
    });
    return mappedTransition;
  }

  public FiniteAutomata buildFA() {
    String name = (String) jsonObject.get("name");
    String type = (String) jsonObject.get("type");
    JSONObject tuple = (JSONObject) jsonObject.get("tuple");
    JSONObject transitions = (JSONObject) tuple.get("delta");
    Transitions allTransitions;
    if(type.equals("nfa")){
      allTransitions = mapToNFATransitions(transitions);
      Tuple tupleObject = mapToTuple(tuple, allTransitions);
      return new NFA(tupleObject, name);
    }
    allTransitions = mapToDFATransitions(transitions);
    Tuple tupleObject = mapToTuple(tuple, allTransitions);
    return new DFA(tupleObject, name);
  }

  public ArrayList<String> getPassCases() {
    return  (ArrayList<String>) jsonObject.get("pass-cases");
  }

  public ArrayList<String> getFailCases() {
    return  (ArrayList<String>) jsonObject.get("fail-cases");
  }
}
