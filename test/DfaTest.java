import org.junit.Assert;

import java.util.ArrayList;

public class DfaTest {
  @org.junit.Test
  public void shouldReturnTrueIfLanguageAcceptTheString() throws Exception {
    Transitions transitions = new Transitions();
    transitions.setTransition("q1", "q2", "0");
    ArrayList<String> states = new ArrayList<String>(){{add("q1");add("q2");}};
    ArrayList<String> alphabets = new ArrayList<String>(){{add("0");}};
    String initialState = "q1";
    ArrayList<String> finalStates = new ArrayList<String>() {{
      add("q2");
    }};
    Tuple<ArrayList<String>, ArrayList<String>, Transitions, String, ArrayList<String>> tuple = new Tuple<>(states, alphabets, transitions, initialState, finalStates);
    Dfa dfa = new Dfa(tuple);
    boolean result = dfa.process();
    Assert.assertTrue(result);
  }

  @org.junit.Test
  public void shouldReturnTrueIfLanguageAcceptTheStringContainsOneOnOddPlaces() throws Exception {
    Transitions transitions = new Transitions();
    transitions.setTransition("q1", "q2", "1");
    transitions.setTransition("q1", "q3", "0");
    transitions.setTransition("q2", "q1", "0");
    transitions.setTransition("q2", "q1", "1");
    transitions.setTransition("q3", "q3", "1");
    transitions.setTransition("q3", "q3", "0");

    ArrayList<String> states = new ArrayList<String>(){{add("q1");add("q2");add("q3");}};
    ArrayList<String> alphabets = new ArrayList<String>(){{add("1");add("0");add("1");add("0");}};
    String initialState = "q1";
    ArrayList<String> finalStates = new ArrayList<String>() {{
      add("q1");
      add("q2");
    }};
    Tuple<ArrayList<String>, ArrayList<String>, Transitions, String, ArrayList<String>> tuple = new Tuple<>(states, alphabets, transitions, initialState, finalStates);
    Dfa dfa = new Dfa(tuple);
    boolean result = dfa.process();
    Assert.assertTrue(result);
  }

  @org.junit.Test
  public void AllStringsThatBeginWithOneAndContainTheString001() throws Exception {
    Transitions transitions = new Transitions();
    transitions.setTransition("q1", "q2", "1");
    transitions.setTransition("q1", "q6", "0");
    transitions.setTransition("q2", "q2", "1");
    transitions.setTransition("q2", "q3", "0");
    transitions.setTransition("q3", "q2", "1");
    transitions.setTransition("q3", "q4", "0");
    transitions.setTransition("q4", "q4", "0");
    transitions.setTransition("q4", "q5", "1");
    transitions.setTransition("q5", "q5", "1");
    transitions.setTransition("q5", "q5", "0");
    transitions.setTransition("q6", "q6", "0");
    transitions.setTransition("q6", "q6", "1");


    ArrayList<String> states = new ArrayList<String>(){{add("q1");add("q2");add("q3");add("q4");add("q5");add("q6");}};
    ArrayList<String> alphabets = new ArrayList<String>(){{add("1");add("0");add("0");add("1");add("0");}};
    String initialState = "q1";
    ArrayList<String> finalStates = new ArrayList<String>() {{
      add("q5");
    }};
    Tuple<ArrayList<String>, ArrayList<String>, Transitions, String, ArrayList<String>> tuple = new Tuple<>(states, alphabets, transitions, initialState, finalStates);
    Dfa dfa = new Dfa(tuple);
    boolean result = dfa.process();
    Assert.assertTrue(result);
  }
}