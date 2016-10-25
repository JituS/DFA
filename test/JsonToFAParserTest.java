import com.thoughtworks.testrunner.FiniteAutomata;
import commons.*;
import finiteAutomata.DFA;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonToFAParserTest {
  @Test
  public void shouldParseJsonAndReturnLanguageObject() throws Exception {
    String jsonString = "{\"name\":\"odd number of zeroes\"," +
      "\"type\":\"finiteAutomata\"," +
      "\"tuple\":{\"states\":[\"q1\"]," +
      "\"alphabets\":[\"1\"]," +
      "\"delta\":{\"q1\":{\"0\":\"q1\",\"1\":\"q1\"}}," +
      "\"start-state\":\"q1\"," +
      "\"final-states\":[\"q1\"]}," +
      "\"pass-cases\":[\"0\"]," +
      "\"fail-cases\":[\"00\"]}";

    JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);

    Builder builder = new Builder(jsonObject);

    FiniteAutomata actualFA = builder.buildFA();

    Assert.assertEquals(getExpectedFA(), actualFA);
  }

  private DFA getExpectedFA() {
    State q1 = new State("q1");
    ITransition<State> transitions = new DFATransition();
    transitions.setTransition(q1, q1, "1");
    transitions.setTransition(q1, q1, "0");
    States states = new States();
    states.add(q1);
    List<String> alphabets = new ArrayList<String>() {{add("1");}};
    States finalStates = new States();
    finalStates.add(q1);
    Tuple expectedTuple = new Tuple(states, alphabets, transitions, q1, finalStates);

    return new DFA(expectedTuple, "odd number of zeroes");
  }
}