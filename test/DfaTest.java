import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

public class DfaTest {
  Transitions transitions;
  State q1;
  State q2;
  State q3;
  State q4;
  State q5;
  State q6;
  @Before
  public void setUp() throws Exception {
    q1 = new State("q1");
    q2 = new State("q2");
    q3 = new State("q3");
    q4 = new State("q4");
    q5 = new State("q5");
    q6 = new State("q6");
    transitions = new Transitions();
  }

  @org.junit.Test
  public void shouldReturnTrueIfLanguageAcceptTheString() throws Exception, InvalidStringException {
    transitions.setTransition(q1, q2, "0");
    List<State> states = new ArrayList<State>(){{add(q1);add(q2);}};
    ArrayList<String> alphabets = new ArrayList<String>(){{add("0"); add("1");}};
    State initialState = q1;
    List<State> finalStates = new ArrayList<State>() {{
      add(q2);
    }};
    Tuple<List<State>, ArrayList<String>, Transitions, State, List<State>> tuple = new Tuple<>(states, alphabets, transitions, initialState, finalStates);
    Dfa dfa = new Dfa(tuple);
    ArrayList<String> inputString = new ArrayList<String>(){{add("0");}};
    boolean result = dfa.process(inputString);
    Assert.assertTrue(result);
  }

  @org.junit.Test
  public void shouldReturnTrueIfLanguageAcceptTheStringContainsOneOnOddPlaces() throws Exception, InvalidStringException {
    transitions.setTransition(q1, q2, "1");
    transitions.setTransition(q1, q3, "0");
    transitions.setTransition(q2, q1, "0");
    transitions.setTransition(q2, q1, "1");
    transitions.setTransition(q3, q3, "1");
    transitions.setTransition(q3, q3, "0");

    ArrayList<State> states = new ArrayList<State>(){{add(q1);add(q2);add(q3);}};
    ArrayList<String> alphabets = new ArrayList<String>(){{add("1");add("0");}};
    State initialState = q1;
    List<State> finalStates = new ArrayList<State>() {{
      add(q1);
      add(q2);
    }};
    Tuple<List<State>, ArrayList<String>, Transitions, State, List<State>> tuple = new Tuple<>(states, alphabets, transitions, initialState, finalStates);
    Dfa dfa = new Dfa(tuple);
    ArrayList<String> inputString = new ArrayList<String>(){{add("1");add("0");add("1");add("0");}};
    boolean result = dfa.process(inputString);
    Assert.assertTrue(result);
  }

  @org.junit.Test
  public void AllStringsThatBeginWithOneAndContainTheString001() throws Exception, InvalidStringException {
    transitions.setTransition(q1, q2, "1");
    transitions.setTransition(q1, q6, "0");
    transitions.setTransition(q2, q2, "1");
    transitions.setTransition(q2, q3, "0");
    transitions.setTransition(q3, q2, "1");
    transitions.setTransition(q3, q4, "0");
    transitions.setTransition(q4, q4, "0");
    transitions.setTransition(q4, q5, "1");
    transitions.setTransition(q5, q5, "1");
    transitions.setTransition(q5, q5, "0");
    transitions.setTransition(q6, q6, "0");
    transitions.setTransition(q6, q6, "1");


    List<State> states = new ArrayList<State>(){{add(q1);add(q2);add(q3);add(q4);add(q5);add(q6);}};
    ArrayList<String> alphabets = new ArrayList<String>(){{add("1");add("0");}};
    State initialState = q1;
    List<State> finalStates = new ArrayList<State>() {{
      add(q5);
    }};
    Tuple<List<State>, ArrayList<String>, Transitions, State, List<State>> tuple = new Tuple<>(states, alphabets, transitions, initialState, finalStates);
    Dfa dfa = new Dfa(tuple);

    ArrayList<String> inputString = new ArrayList<String>(){{add("1");add("0");add("0");add("1");add("0");}};
    boolean result = dfa.process(inputString);
    Assert.assertTrue(result);
  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void shouldThrowExceptionIfInputStringIsInvalid() throws Exception, InvalidStringException {
    thrown.equals(InvalidStringException.class);
    thrown.expectMessage("Invalid String");

    transitions.setTransition(q1, q2, "1");
    List<State> states = new ArrayList<State>(){{add(q1);add(q2);}};
    ArrayList<String> alphabets = new ArrayList<String>(){{add("1");add("0");}};
    State initialState = q1;
    List<State> finalStates = new ArrayList<State>() {{
      add(q2);
    }};
    Tuple<List<State>, ArrayList<String>, Transitions, State, List<State>> tuple = new Tuple<>(states, alphabets, transitions, initialState, finalStates);
    Dfa dfa = new Dfa(tuple);
    boolean process = dfa.process(new ArrayList<String>() {{
      add("2");
    }});
  }
}