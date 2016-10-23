package finiteAutomata;

import com.thoughtworks.testrunner.FiniteAutomata;
import commons.DFATransition;
import commons.ITransition;
import commons.State;
import commons.Tuple;

import java.util.ArrayList;
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
    State initialState = tuple.getInitialState();
    Set<State> initialStates = (Set<State>) tuple.getTransitions().process(new HashSet<State>(){{add(initialState);}}, "");
    Tuple dfaTuple = new Tuple(new HashSet<State>() {{
      add(createStateName(initialStates));
    }}, new ArrayList<>(), new DFATransition(), createStateName(initialStates), new HashSet<>());

    if(isFinalState(initialStates)) {
      dfaTuple.getFinalStates().add(createStateName(initialStates));
    }
    HashSet<Set<State>> startingStates = new HashSet<>();
    startingStates.add(initialStates);
    populateDFATuple(startingStates, dfaTuple);
    return new DFA(dfaTuple, name);
  }

  private Tuple populateDFATuple(HashSet<Set<State>> currentStates, Tuple dfaTuple) {
    HashSet<Set<State>> allNextStates = new HashSet<>();
    for (String alphabet : tuple.getAlphabets()) {
      for (Set<State> currentState : currentStates) {
        Set<State> nextState = ((ITransition<Set<State>>) tuple.getTransitions()).process(currentState, alphabet);
        if (nextState.size() == 0) continue;
        State nextStateName = createStateName(nextState);
        ITransition<State> dfaTransitions = dfaTuple.getTransitions();
        dfaTransitions.setTransition(createStateName(currentState), nextStateName, alphabet);
        if(isFinalState(nextState)) {
          dfaTuple.getFinalStates().add(nextStateName);
        }
        Set<State> dfaStates = dfaTuple.getStates();
        if(!dfaStates.contains(nextStateName)){
          allNextStates.add(nextState);
          dfaStates.add(nextStateName);
        }
      }
    }
    return (allNextStates.size() == 0) ? dfaTuple : populateDFATuple(allNextStates, dfaTuple);
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
