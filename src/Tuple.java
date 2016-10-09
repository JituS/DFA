import java.util.ArrayList;

class Tuple {
  private String[] states;
  Character[] alphabets;
  Transitions transitions;
  String initialState;
  ArrayList<String> finalStates;

  Tuple(String[] states, Character[] alphabets, Transitions transitions, String initialState, ArrayList<String> finalStates) {
    this.states = states;
    this.alphabets = alphabets;
    this.transitions = transitions;
    this.initialState = initialState;
    this.finalStates = finalStates;
  }
}
