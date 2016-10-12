import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonToTupleParserTest {
  @Test
  public void shouldParseJsonAndReturnTuple() throws Exception {
    String json = "{\"states\":[\"q1\"], \"alphabets\":[\"1\"], \"initialState\":\"q1\", " +
      "\"finalStates\":[\"q1\"], \"transitions\":[[\"q1\", \"q1\", \"1\"]]}";

    Tuple actualTuple = new JsonToTupleParser().parse(json);
    State q1 = new State("q1");
    State q2 = new State("q2");
    Transitions transitions = new Transitions();
    transitions.setTransition(q1, q1, "1");
    List<State> states = new ArrayList<State>() {{add(q1);}};
    ArrayList<String> alphabets = new ArrayList<String>() {{add("1");}};
    State initialState = q1;
    List<State> finalStates = new ArrayList<State>() {{add(q1);}};
    Tuple<List<State>, ArrayList<String>, Transitions, State, List<State>> expectedTuple = new Tuple<>(states, alphabets, transitions, initialState, finalStates);

    Assert.assertEquals(expectedTuple, actualTuple);
  }
}