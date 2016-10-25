package utils;

import commons.*;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenericMapper {
  public static List<String> mapObjectListToStringList(Object states) {
    List strings = (List) states;
    ArrayList<String> mappedStrings = new ArrayList<>();
    for (Object string : strings) {
      mappedStrings.add((String) string);
    }
    return mappedStrings;
  }

  public static ITransition<State> getDFATransition(Map<String, Map<String, String>> transitions) {
    ITransition<State> mappedTransition = new DFATransition();

    transitions.entrySet()
      .forEach(transition -> {
        State state = new State(transition.getKey());
        Map<String, String> transitionsOfState = transition.getValue();
        transitionsOfState
          .forEach((alphabet, nextState) -> mappedTransition.setTransition(state, new State(nextState), alphabet));
      });

    return mappedTransition;
  }

  public static ITransition<States> getNFATransition(Map<String, Map<String, List<String>>> transitions) {
    ITransition<States> mappedTransition = new NFATransition();

    transitions.entrySet()
      .forEach(transition -> {
        State state = new State(transition.getKey());
        Map<String, List<String>> transitionsOfState = transition.getValue();
        transitionsOfState.forEach((alphabet, nextState) ->
          nextState.forEach(eachState -> mappedTransition.setTransition(state, new State(eachState), alphabet)));
      });

    return mappedTransition;
  }

  public static Tuple mapToFATuple(JSONObject tuple, ITransition transitions) {
    States states = mapToStates(tuple.get("states"));
    List<String> alphabets = mapObjectListToStringList(tuple.get("alphabets"));
    State initialState = new State((String) tuple.get("start-state"));
    States finalStates = mapToStates(tuple.get("final-states"));
    return new Tuple(states, alphabets, transitions, initialState, finalStates);
  }

  private static States mapToStates(Object tuple) {
    States states = new States();
    mapObjectListToStringList(tuple)
      .forEach(state -> states.add(new State(state)));
    return states;
  }
}