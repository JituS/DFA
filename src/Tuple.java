import java.util.ArrayList;

public class Tuple {
  public String[] states;
  public Character[] alphabets;
  public Transitions transitions;
  public String initialState;
  public ArrayList<String> finalStates;

  public Tuple(String[] states, Character[] alphabets, Transitions transitions, String initialState, ArrayList<String> finalStates) {
    this.states = states;
    this.alphabets = alphabets;
    this.transitions = transitions;
    this.initialState = initialState;
    this.finalStates = finalStates;
  }
}
