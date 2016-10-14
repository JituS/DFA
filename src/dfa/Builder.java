package dfa;

import org.json.simple.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class Builder {

  private JSONObject jsonObject;

  public Builder(JSONObject jsonObject) {
    this.jsonObject = jsonObject;
  }

  private Tuple mapToTuple(JSONObject tuple) {
    Set<State> states = ((List<String>) tuple.get("states")).stream().map(State::new).collect(Collectors.toSet());
    List<String> alphabets = (ArrayList<String>) tuple.get("alphabets");
    JSONObject transitions = (JSONObject) tuple.get("delta");
    Transitions allTransitions = mapToTransitions(transitions);
    State initialState = new State((String) tuple.get("start-state"));
    Set<State> finalStates = ((List<String>) tuple.get("final-states")).stream().map(State::new).collect(Collectors.toSet());

    return new Tuple(states, alphabets, allTransitions, initialState, finalStates);
  }

  private Transitions mapToTransitions(JSONObject transitions) {
    Transitions allTransitions = new Transitions();
    Map<String, Map<String, String>> allTransition = (Map<String, Map<String, String>>) transitions;
    for (Map.Entry<String, Map<String, String>> eachTransition : allTransition.entrySet()) {
      State state = new State(eachTransition.getKey());
      Map<String, String> transitionsOfState = eachTransition.getValue();
      for (Map.Entry<String, String> eachTransitionForState : transitionsOfState.entrySet()) {
        State nextState = new State(eachTransitionForState.getValue());
        String alphabet = eachTransitionForState.getKey();
        allTransitions.setTransition(state, nextState, alphabet);
      }
    }

    return allTransitions;
  }

  public FiniteAutomata buildFA() {
    String name = (String) jsonObject.get("name");
    String type = (String) jsonObject.get("type");
    JSONObject tuple = (JSONObject) jsonObject.get("tuple");
    Tuple tupleObject = mapToTuple(tuple);
    return new DFA(tupleObject, name, type);
  }

  public ArrayList<String> getPassCases() {
    return  (ArrayList<String>) jsonObject.get("pass-cases");
  }

  public ArrayList<String> getFailCases() {
    return  (ArrayList<String>) jsonObject.get("fail-cases");
  }
}
