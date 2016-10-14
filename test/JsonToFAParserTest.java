import dfa.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class JsonToFAParserTest {
  @Test
  public void shouldParseJsonAndReturnLanguageObject() throws Exception {
    String jsonString = "[{\"name\":\"odd number of zeroes\"," +
      "\"type\":\"dfa\"," +
      "\"tuple\":{\"states\":[\"q1\"]," +
      "\"alphabets\":[\"1\"]," +
      "\"delta\":{\"q1\":{\"0\":\"q1\",\"1\":\"q1\"}}," +
      "\"start-state\":\"q1\"," +
      "\"final-states\":[\"q1\"]}," +
      "\"pass-cases\":[\"0\"]," +
      "\"fail-cases\":[\"00\"]}]";

    ArrayList<DFA> actualParsedObjects = new JsonToFAParser().parse(jsonString);

    ArrayList<DFA> expectedParsedJson = new ArrayList<DFA>(){{add(getExpectedLanguageObject());}};

    Assert.assertEquals(expectedParsedJson, actualParsedObjects);
  }

  private DFA getExpectedLanguageObject() {
    State q1 = new State("q1");
    Transitions transitions = new Transitions();
    transitions.setTransition(q1, q1, "1");
    transitions.setTransition(q1, q1, "0");
    Set<State> states = new HashSet<State>() {{add(q1);}};
    ArrayList<String> alphabets = new ArrayList<String>() {{add("1");}};
    Set<State> finalStates = new HashSet<State>() {{add(q1);}};
    Tuple expectedTuple = new Tuple(states, alphabets, transitions, q1, finalStates);

    return new DFA(expectedTuple, "odd number of zeroes", "dfa", new ArrayList<String>() {{
      add("0");
    }}, new ArrayList<String>() {{
      add("00");
    }});
  }
}