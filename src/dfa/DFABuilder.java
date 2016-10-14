package dfa;

import org.json.simple.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

class DFABuilder {

  private JSONObject jsonObject;

  DFABuilder(JSONObject jsonObject) {
    this.jsonObject = jsonObject;
  }

  private Tuple mapToTuple(JSONObject tuple) {
    Set<State> states = ((List<String>) tuple.get("states")).stream().map(State::new).collect(Collectors.toSet());
    ArrayList<String> alphabets = (ArrayList<String>) tuple.get("alphabets");
    JSONObject transitions = (JSONObject) tuple.get("delta");
    Transitions allTransitions = mapToTransitions(transitions);
    State initialState = new State((String) tuple.get("start-state"));
    Set<State> finalStates = ((List<String>) tuple.get("final-states")).stream().map(State::new).collect(Collectors.toSet());

    return new Tuple(states, alphabets, allTransitions, initialState, finalStates);
  }

  private Transitions mapToTransitions(JSONObject transitions) {
    Transitions allTransitions = new Transitions();
    HashMap<String, HashMap<String, String>> allTransition = (HashMap<String, HashMap<String, String>>) transitions;

    for (Map.Entry<String, HashMap<String, String>> eachTransition : allTransition.entrySet()) {
      State state = new State(eachTransition.getKey());
      HashMap<String, String> transitionsForState = eachTransition.getValue();
      for (Map.Entry<String, String> eachTransitionForState : transitionsForState.entrySet()) {
        State nextState = new State(eachTransitionForState.getValue());
        String alphabet = eachTransitionForState.getKey();
        allTransitions.setTransition(state, nextState, alphabet);
      }
    }

    return allTransitions;
  }


  DFA build() {
    String name = (String) jsonObject.get("name");
    String type = (String) jsonObject.get("type");
    JSONObject tuple = (JSONObject) jsonObject.get("tuple");
    ArrayList<String> passCases = (ArrayList<String>) jsonObject.get("pass-cases");
    ArrayList<String> failCases = (ArrayList<String>) jsonObject.get("fail-cases");
    Tuple tupleObject = mapToTuple(tuple);
    return new DFA(tupleObject, name, type, passCases, failCases);
  }
}
