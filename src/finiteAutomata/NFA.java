package finiteAutomata;

import com.thoughtworks.testrunner.FiniteAutomata;
import commons.DFATransition;
import commons.ITransition;
import commons.State;
import commons.Tuple;

import java.util.HashSet;
import java.util.Set;

public class NFA implements FiniteAutomata {
  private Tuple tuple;
  private String name;

  public NFA(Tuple tuple, String name) {
    this.tuple = tuple;
    this.name = name;
  }

  @Override
  public boolean verify(String inputString) {
    String[] split = inputString.split("");
    ITransition<Set<State>> transitions = tuple.getTransitions();
    Set<State> finalStates = tuple.getFinalStates();
    Set<State> nextStates = new HashSet<State>() {{
      add(tuple.getInitialState());
    }};
    for (String character : split) {
        nextStates = transitions.process(nextStates, character);
    }
    nextStates.retainAll(finalStates);
    return (nextStates.size() != 0);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NFA dfa = (NFA) o;
    return tuple != null ? tuple.equals(dfa.tuple) : dfa.tuple == null && (name != null ? name.equals(dfa.name) : dfa.name == null);
  }

  @Override
  public int hashCode() {
    int result = tuple != null ? tuple.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  public DFA toDFA() {
    ITransition<Set<State>> transitions = tuple.getTransitions();
    Set<State> initialStates = transitions.process(new HashSet<State>(){{add(tuple.getInitialState());}}, "");
    HashSet<State> dfaFinalStates = new HashSet<>();
    if(isFinalState(initialStates)) {
      dfaFinalStates.add(createStateName(initialStates));
    }
    Tuple dfaTuple = new Tuple(new HashSet<State>() {{
      add(createStateName(initialStates));
    }}, tuple.getAlphabets(), new DFATransition(), createStateName(initialStates), dfaFinalStates);

    populateDFATuple(new HashSet<Set<State>>(){{add(initialStates);}}, dfaTuple);
    return new DFA(dfaTuple, name);
  }

  private Tuple populateDFATuple(HashSet<Set<State>> currentStates, Tuple dfaTuple) {
    HashSet<Set<State>> nextStates = new HashSet<>();
    for (String alphabet : tuple.getAlphabets()) {
      for (Set<State> currentState : currentStates) {
        ITransition<Set<State>> transitions = tuple.getTransitions();
        Set<State> nextState = transitions.process(currentState, alphabet);
        if (nextState.size() == 0) continue;
        State nextStateName = createStateName(nextState);
        ITransition<State> dfaTransitions = dfaTuple.getTransitions();
        dfaTransitions.setTransition(createStateName(currentState), nextStateName, alphabet);
        if(isFinalState(nextState)) {
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

  private boolean isFinalState(Set<State> nextState) {
    for (State nState : nextState) {
      if (tuple.getFinalStates().contains(nState)) return true;
    }
    return false;
  }

  private State createStateName(Set<State> NStates) {
    StringBuilder stringBuilder = new StringBuilder();
    for (State nState : NStates) {
      stringBuilder.append(nState.stateName);
    }
    return new State(stringBuilder.toString());
  }

}
