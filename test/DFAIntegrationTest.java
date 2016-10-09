import org.junit.Assert;
import org.junit.Test;

public class DFAIntegrationTest {
  @Test
  public void shouldReturnTrieIfStringIsAccepted() throws Exception {
    String json = "{\"states\":[\"q1\", \"q2\",\"q3\", \"q4\",\"q5\"], \"alphabets\":[\"1\", \"0\", \"1\", \"0\"], \"initialState\":\"q1\", " +
      "\"finalStates\":[\"q4\"], \"transitions\":[[\"q1\", \"q2\", \"1\"],[\"q1\", \"q2\", \"0\"],[\"q2\", \"q3\", \"1\"],[\"q2\", \"q3\", \"0\"]" +
      ",[\"q3\", \"q4\", \"1\"],[\"q3\", \"q5\", \"0\"],[\"q4\", \"q4\", \"1\"],[\"q4\", \"q4\", \"0\"],[\"q5\", \"q5\", \"1\"],[\"q5\", \"q5\", \"0\"]]}";

    Tuple tuple = new JsonParser().parse(json);
    boolean result = new Dfa(tuple).process();

    Assert.assertTrue(result);
  }
}
