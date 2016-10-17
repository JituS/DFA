package commons;

import java.util.HashMap;
import java.util.Map;

public abstract class ITransition<T> {
  Map<State, Map<String, T>> transitions = new HashMap<>();
  public abstract T process(T state, String alphabet);
  public abstract void setTransition(State state1, State state2, String alphabet);
}
