import org.junit.Assert;

import java.util.ArrayList;

public class DfaTest {
  @org.junit.Test
  public void shouldReturnTrueIfLanguageAcceptTheString() throws Exception {
    Transitions transitions = new Transitions();
    transitions.setTransition("q1", "q2", '0');
    String[] states = {"q1", "q2"};
    Character[] alphabets = {'0'};
    String initialState = "q1";
    ArrayList<String> finalStates = new ArrayList<String>() {{
      add("q2");
    }};
    Tuple tuple = new Tuple(states, alphabets, transitions, initialState, finalStates);
    Dfa dfa = new Dfa(tuple);
    boolean result = dfa.process();
    Assert.assertTrue(result);
  }

  @org.junit.Test
  public void shouldReturnTrueIfLanguageAcceptTheStringContainsOneOnOddPlaces() throws Exception {
    Transitions transitions = new Transitions();
    transitions.setTransition("q1", "q2", '1');
    transitions.setTransition("q1", "q3", '0');
    transitions.setTransition("q2", "q1", '0');
    transitions.setTransition("q2", "q1", '1');
    transitions.setTransition("q3", "q3", '1');
    transitions.setTransition("q3", "q3", '0');

    String[] states = {"q1", "q2", "q3"};
    Character[] alphabets = {'1', '0', '1', '0'};
    String initialState = "q1";
    ArrayList<String> finalStates = new ArrayList<String>() {{
      add("q1");
      add("q2");
    }};
    Tuple tuple = new Tuple(states, alphabets, transitions, initialState, finalStates);
    Dfa dfa = new Dfa(tuple);
    boolean result = dfa.process();
    Assert.assertTrue(result);
  }

  @org.junit.Test
  public void AllStringsThatBeginWithOneAndContainTheString001() throws Exception {
    Transitions transitions = new Transitions();
    transitions.setTransition("q1", "q2", '1');
    transitions.setTransition("q1", "q6", '0');
    transitions.setTransition("q2", "q2", '1');
    transitions.setTransition("q2", "q3", '0');
    transitions.setTransition("q3", "q2", '1');
    transitions.setTransition("q3", "q4", '0');
    transitions.setTransition("q4", "q4", '0');
    transitions.setTransition("q4", "q5", '1');
    transitions.setTransition("q5", "q5", '1');
    transitions.setTransition("q5", "q5", '0');
    transitions.setTransition("q6", "q6", '0');
    transitions.setTransition("q6", "q6", '1');

    String[] states = {"q1", "q2", "q3", "q4", "q5", "q6"};
    Character[] alphabets = {'1', '0', '0', '1', '0'};
    String initialState = "q1";
    ArrayList<String> finalStates = new ArrayList<String>() {{
      add("q5");
    }};
    Tuple tuple = new Tuple(states, alphabets, transitions, initialState, finalStates);
    Dfa dfa = new Dfa(tuple);
    boolean result = dfa.process();
    Assert.assertTrue(result);
  }
}