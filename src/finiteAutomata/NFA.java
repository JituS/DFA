package finiteAutomata;
import com.thoughtworks.testrunner.FiniteAutomata;
import commons.*;
import java.util.HashSet;
import java.util.Set;

public class NFA implements FiniteAutomata {
  private Tuple<States> tuple;
  private String machineName;

  public NFA(Tuple<States> tuple, String machineName) {
    this.tuple = tuple;
    this.machineName = machineName;
  }

  @Override
  public boolean verify(String inputString) {
    String[] split = inputString.split("");
    ITransition<States> transitions = tuple.getTransitions();
    States finalStates = tuple.getFinalStates();
    States states = new States();
    states.add(tuple.getInitialState());
    for (String character : split) {
        states = transitions.process(states, character);
    }
    states.retainAll(finalStates);
    return (states.size() != 0);
  }

  @Override
  public String getName() {
    return machineName;
  }

  private void addFinalState(States states, States finalStates) {
    if (states.containsAny(tuple.getFinalStates())) {
      finalStates.add(states.stateName());
    }
  }

  public DFA toDFA() {
    ITransition<States> nfaTransitions = tuple.getTransitions();
    States dfaInitialStates = nfaTransitions.process(new States(){{add(tuple.getInitialState());}}, "");
    States dfaStates = new States(){{dfaInitialStates.stateName();}};
    States dfaFinalStates = new States();
    addFinalState(dfaInitialStates, dfaFinalStates);
    Set<States> allMachinesStates = new HashSet<States>() {{
      add(dfaInitialStates);
    }};
    Tuple<State> tuple = getDfaTuple(dfaStates, dfaInitialStates.stateName(), new DFATransition(), dfaFinalStates, allMachinesStates);
    return new DFA(tuple, machineName);
  }

  private Tuple<State> getDfaTuple(States states, State initialState, DFATransition transition, States finalStates, Set<States> allMachinesStates) {
    Set<States> allMachines = new HashSet<>();
    for (String alphabet : tuple.getAlphabets()) {
      for (States machineState : allMachinesStates) {
        ITransition<States> transitions = tuple.getTransitions();
        States nextState = transitions.process(machineState, alphabet);
        if (nextState.size() == 0) continue;
        State nextStateName = nextState.stateName();
        transition.setTransition(machineState.stateName(), nextStateName, alphabet);
        addFinalState(nextState, finalStates);
        if(!states.contains(nextStateName)){
          allMachines.add(nextState);
          states.add(nextStateName);
        }
      }
    }
    return (allMachines.size() == 0) ? new Tuple<>(states, tuple.getAlphabets(), transition, initialState, finalStates)
      : getDfaTuple(states, initialState, transition, finalStates, allMachines);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NFA dfa = (NFA) o;
    return tuple != null ? tuple.equals(dfa.tuple) : dfa.tuple == null && (machineName != null ? machineName.equals(dfa.machineName) : dfa.machineName == null);
  }

  @Override
  public int hashCode() {
    int result = tuple != null ? tuple.hashCode() : 0;
    result = 31 * result + (machineName != null ? machineName.hashCode() : 0);
    return result;
  }
}
