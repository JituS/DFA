import com.thoughtworks.testrunner.FiniteAutomata;
import commons.Builder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class NfaToDfaTest {

  @Test
  public void shouldPassAllTestCasesOfNfaAfterConversionToDfa() throws Exception {
    String jsonString ="{\"name\":\"(01)*(10)* U 11*\",\"type\":\"nfa-to-dfa\",\"tuple\":{\"states\":[\"q1\",\"q3\",\"q9\",\"q7\",\"q2\",\"q8\",\"q5\",\"q6\",\"q4\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"e\":[\"q2\",\"q4\"]},\"q3\":{\"0\":[\"q3\"]},\"q9\":{\"e\":[\"q7\"]},\"q7\":{\"1\":[\"q8\"],\"e\":[\"q9\"]},\"q2\":{\"0\":[\"q3\"]},\"q8\":{\"0\":[\"q9\"]},\"q5\":{\"1\":[\"q6\"]},\"q6\":{\"e\":[\"q7\",\"q4\"]},\"q4\":{\"0\":[\"q5\"],\"e\":[\"q6\"]}},\"start-state\":\"q1\",\"final-states\":[\"q3\",\"q9\",\"q6\"]},\"pass-cases\":[\"0\",\"000\",\"01\",\"10\",\"0110\"],\"fail-cases\":[\"1\",\"11\",\"111\",\"1101\",\"0111\"]}";
    JSONObject FAJson = (JSONObject) new JSONParser().parse(jsonString);
    Builder builder = new Builder(FAJson);
    FiniteAutomata finiteAutomata = builder.buildFA();
    List<String> passCases = builder.getPassCases();
    List<String> failCases = builder.getFailCases();
    testCases(finiteAutomata, passCases, failCases);
  }
  private void testCases(FiniteAutomata finiteAutomata, List<String> passCases, List<String> failCases) {
    for (String passCase : passCases) {
      assertTrue(finiteAutomata.verify(passCase));
    }
    for (String failCase : failCases) {
      assertFalse(finiteAutomata.verify(failCase));
    }
  }
}