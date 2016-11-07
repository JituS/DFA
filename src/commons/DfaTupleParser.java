package commons;

import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

import static utils.GenericMapper.mapToList;
import static utils.GenericMapper.mapToStates;

class DfaTupleParser implements ITupleParser<State> {
  @Override
  public ITransition<State> parseTransitions(JSONObject tuple) {
    Map<String, Map<String, String>> transitions = (Map<String, Map<String, String>>) tuple.get("delta");
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

  @Override
  public Tuple<State> parseTuple(JSONObject tuple) {
    States states = mapToStates(tuple.get("states"));
    List<String> alphabets = mapToList(tuple.get("alphabets"));
    State initialState = new State((String) tuple.get("start-state"));
    States finalStates = mapToStates(tuple.get("final-states"));
    ITransition<State> delta = parseTransitions(tuple);
    return new Tuple<>(states, alphabets, delta, initialState, finalStates);
  }
}
