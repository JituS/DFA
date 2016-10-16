package dfa;

import org.json.simple.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class Builder {

  private JSONObject jsonObject;

  public Builder(JSONObject jsonObject) {
    this.jsonObject = jsonObject;
  }

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
      return new NFA(tupleObject, type + ":" + name);
    }
    allTransitions = mapToDFATransitions(transitions);
    Tuple tupleObject = mapToTuple(tuple, allTransitions);
    return new DFA(tupleObject, type + ":" + name);
  }

  public ArrayList<String> getPassCases() {
    return  (ArrayList<String>) jsonObject.get("pass-cases");
  }

  public ArrayList<String> getFailCases() {
    return  (ArrayList<String>) jsonObject.get("fail-cases");
  }
}
