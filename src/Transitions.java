import java.util.HashMap;
import java.util.Map;

class Transitions {

  private Map<String, Map<String, String>> transitions = new HashMap<>();
  void setTransition(String state1, String state2, String alphabet) {
    transitions.putIfAbsent(state1, new HashMap<>());
    transitions.get(state1).put(alphabet, state2);
  }

  String process(String state, String alphabet) {
    if(transitions.containsKey(state)){
      return transitions.get(state).get(alphabet);
    }
    return null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Transitions that = (Transitions) o;

    return transitions != null ? transitions.equals(that.transitions) : that.transitions == null;

  }

  @Override
  public int hashCode() {
    return transitions != null ? transitions.hashCode() : 0;
  }
}
