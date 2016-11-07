package commons;

public class State {
  String stateName;

  public State(String stateName) {
    this.stateName = stateName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    State state = (State) o;
    return stateName != null ? stateName.equals(state.stateName) : state.stateName == null;
  }

  @Override
  public int hashCode() {
    return stateName != null ? stateName.hashCode() : 0;
  }
}
