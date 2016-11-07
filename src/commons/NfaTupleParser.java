package commons;

import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

import static utils.GenericMapper.mapToList;
import static utils.GenericMapper.mapToStates;

class NfaTupleParser implements ITupleParser<States> {
  @Override
  public ITransition<States> parseTransitions(JSONObject tuple) {
    Map<String, Map<String, List<String>>> transitions = (Map<String, Map<String, List<String>>>) tuple.get("delta");
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

  @Override
  public Tuple<States> parseTuple(JSONObject tuple) {
    States states = mapToStates(tuple.get("states"));
    List<String> alphabets = mapToList(tuple.get("alphabets"));
    State initialState = new State((String) tuple.get("start-state"));
    States finalStates = mapToStates(tuple.get("final-states"));
    ITransition<States> delta = parseTransitions(tuple);
    return new Tuple<>(states, alphabets, delta, initialState, finalStates);
  }
}
