import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class JsonToTupleParser {
  Tuple parse(String jsonText) throws ParseException {
    JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonText);
    List<State> states = ((List<String>) jsonObject.get("states")).stream().map(State::new).collect(Collectors.toList());
    JSONArray alphabets = (JSONArray) jsonObject.get("alphabets");
    State initialState = new State((String) jsonObject.get("initialState"));
    List<State> finalStates = ((List<String>) jsonObject.get("finalStates")).stream().map(State::new).collect(Collectors.toList());

    JSONArray transitions = (JSONArray) jsonObject.get("transitions");
    Transitions allTransitions = mapToTransitions(transitions);
    return new Tuple<List<State>,ArrayList<String>, Transitions, State, List<State>>(states, alphabets, allTransitions, initialState, finalStates);
  }

  private Transitions mapToTransitions(JSONArray transitions) {
    Transitions allTransitions = new Transitions();
    for(Object transition: transitions){
      JSONArray eachTransition = (JSONArray) transition;
      allTransitions.setTransition(new State((String) eachTransition.get(0)), new State((String) eachTransition.get(1)), (String) eachTransition.get(2));
    }
    return allTransitions;
  }
}
