public class Tuple {
  private String[] states;
  private String[] alphabets;
  private Transitions transitions;
  private String initialState;
  private String[] finalStates;

  public Tuple(String[] states, String[] alphabets, Transitions transitions, String initialState, String[] finalStates) {
    this.states = states;
    this.alphabets = alphabets;
    this.transitions = transitions;
    this.initialState = initialState;
    this.finalStates = finalStates;
  }
}
