package finiteAutomata;

import com.thoughtworks.testrunner.FiniteAutomata;
import commons.*;

import java.util.HashSet;

public class NFA implements FiniteAutomata {
  private Tuple tuple;
  private String machineName;

  public NFA(Tuple tuple, String machineName) {
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

  public DFA toDFA() {
    ITransition<States> transitions = tuple.getTransitions();

    States initialState = new States();
    initialState.add(tuple.getInitialState());
    States initialStates = transitions.process(initialState, "");

    States dfaFinalStates = new States();

    if(initialStates.containsAny(tuple.getFinalStates())) {
      dfaFinalStates.add(initialStates.stateName());
    }

    States dfaStates = new States();
    dfaStates.add(initialStates.stateName());
    Tuple dfaTuple = new Tuple(dfaStates, tuple.getAlphabets(), new DFATransition(), initialStates.stateName(), dfaFinalStates);

    populateDFATuple(new HashSet<States>(){{add(initialStates);}}, dfaTuple);
    return new DFA(dfaTuple, machineName);
  }

  private Tuple populateDFATuple(HashSet<States> currentStates, Tuple dfaTuple) {
    HashSet<States> nextStates = new HashSet<>();
    for (String alphabet : tuple.getAlphabets()) {
      for (States currentState : currentStates) {
        ITransition<States> transitions = tuple.getTransitions();
        States nextState = transitions.process(currentState, alphabet);
        if (nextState.size() == 0) continue;
        State nextStateName = nextState.stateName();
        ITransition<State> dfaTransitions = dfaTuple.getTransitions();
        dfaTransitions.setTransition(currentState.stateName(), nextStateName, alphabet);
        if(nextState.containsAny(tuple.getFinalStates())) {
          dfaTuple.getFinalStates().add(nextStateName);
        }
        if(!dfaTuple.getStates().contains(nextStateName)){
          nextStates.add(nextState);
          dfaTuple.getStates().add(nextStateName);
        }
      }
    }
    return (nextStates.size() == 0) ? dfaTuple : populateDFATuple(nextStates, dfaTuple);
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
