import java.util.HashMap;
import java.util.Map;

public class Transitions {

  Map<String, Map<Character, String>> transitions = new HashMap<>();
  public void setTransition(String state1, String state2, Character alphabet) {
    transitions.putIfAbsent(state1, new HashMap<Character, String>(){{put(alphabet, state2);}});
  }

  public String process(String state, Character alphabet) {
    if(transitions.containsKey(state)) return transitions.get(state).get(alphabet);
    return null;
  }
}
