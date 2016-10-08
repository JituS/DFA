import java.util.HashMap;
import java.util.Map;

class Transitions {

  private Map<String, Map<Character, String>> transitions = new HashMap<>();
  void setTransition(String state1, String state2, Character alphabet) {
    transitions.putIfAbsent(state1, new HashMap<>());
    transitions.get(state1).put(alphabet, state2);
  }

  String process(String state, Character alphabet) {
    if(transitions.containsKey(state)){
      return transitions.get(state).get(alphabet);
    }
    return null;
  }
}
