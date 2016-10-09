import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser {
  public Tuple parse(String jsonText) throws ParseException {
    JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonText);
    JSONArray states = (JSONArray)jsonObject.get("states");
    JSONArray alphabets = (JSONArray) jsonObject.get("alphabets");
    String initialState = (String) jsonObject.get("initialState");
    JSONArray finalStates = (JSONArray) jsonObject.get("finalStates");
    JSONArray transitions = (JSONArray) jsonObject.get("transitions");
    Transitions allTransitions = new Transitions();
    for(Object transition: transitions){
      JSONArray eachTransition = (JSONArray) transition;
      allTransitions.setTransition((String) eachTransition.get(0), (String) eachTransition.get(1), (String) eachTransition.get(2));
    }
    return new Tuple(states, alphabets, allTransitions, initialState, finalStates);
  }
}
