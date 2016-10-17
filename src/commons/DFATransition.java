package commons;

import java.util.HashMap;

public class DFATransition extends ITransition<State> {

  @Override
  public State process(State state, String alphabet) {
    State nextState = null;
    try { nextState = transitions.get(state).get(alphabet); }
    catch (Exception ignored){}
    return nextState;
  }

  @Override
  public void setTransition(State state1, State state2, String alphabet) {
    transitions.putIfAbsent(state1, new HashMap<>());
    transitions.get(state1).putIfAbsent(alphabet, state2);
  }
}
