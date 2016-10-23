import com.thoughtworks.testrunner.FiniteAutomata;
import commons.Builder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class NFAIntegrationTest {
  @Test
  public void shouldReturnTrueIfStringIsAccepted() throws Exception {
    String jsonString ="{\"name\":\"[10] U ({0*1*} U {1*0*}) )\",\"type\":\"nfa\",\"tuple\":{\"states\":[\"q1\",\"q3\",\"q2\",\"q5\",\"q6\",\"q4\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"0\":[\"q2\",\"q4\"],\"1\":[\"q2\",\"q4\"]},\"q2\":{\"0\":[\"q2\"],\"e\":[\"q3\"]},\"q3\":{\"1\":[\"q3\"],\"e\":[\"q6\"]},\"q4\":{\"1\":[\"q4\"],\"e\":[\"q5\"]},\"q5\":{\"0\":[\"q5\"],\"e\":[\"q6\"]}},\"start-state\":\"q1\",\"final-states\":[\"q6\"]},\"pass-cases\":[\"01100\"],\"fail-cases\":[\"\",\"1010\",\"0101\",\"001100\",\"110010\"]}";

    JSONObject FAJson = (JSONObject) new JSONParser().parse(jsonString);
    Builder builder = new Builder(FAJson);

    FiniteAutomata finiteAutomata = builder.buildFA();
    List<String> passCases = builder.getPassCases();
    List<String> failCases = builder.getFailCases();
    testCases(finiteAutomata, passCases, failCases);
  }

  private void testCases(FiniteAutomata nfa, List<String> passCases, List<String> failCases) {
    for (String passCase : passCases) {
      Assert.assertTrue(nfa.verify(passCase));
    }
    for (String failCase : failCases) {
      Assert.assertFalse(nfa.verify(failCase));
    }
  }
}
