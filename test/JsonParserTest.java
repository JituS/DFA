import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class JsonParserTest {
  @Test
  public void shouldParseJsonAndReturnTuple() throws Exception {
    String json = "{\"states\":[\"q1\"], \"alphabets\":[\"1\"], \"initialState\":\"q1\", " +
      "\"finalStates\":[\"q1\"], \"transitions\":[[\"q1\", \"q1\", \"1\"]]}";

    Tuple actualTuple = new JsonParser().parse(json);

    Transitions transitions = new Transitions();
    transitions.setTransition("q1", "q1", "1");
    ArrayList<String> states = new ArrayList<String>() {{add("q1");}};
    ArrayList<String> alphabets = new ArrayList<String>() {{add("1");}};
    String initialState = "q1";
    ArrayList<String> finalStates = new ArrayList<String>() {{add("q1");}};
    Tuple expectedTuple = new Tuple(states, alphabets, transitions, initialState, finalStates);

    Assert.assertEquals(expectedTuple, actualTuple);
  }
}