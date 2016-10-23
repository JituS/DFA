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
    String jsonString ="{\"name\":\"either even number of zeroes or even number of ones\",\"type\":\"nfa-to-dfa\",\"tuple\":{\"states\":[\"q1\",\"q3\",\"q2\",\"q5\",\"q4\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"e\":[\"q2\",\"q4\"]},\"q2\":{\"0\":[\"q3\"],\"1\":[\"q2\"]},\"q3\":{\"0\":[\"q2\"],\"1\":[\"q3\"]},\"q4\":{\"0\":[\"q4\"],\"1\":[\"q5\"]},\"q5\":{\"0\":[\"q5\"],\"1\":[\"q4\"]}},\"start-state\":\"q1\",\"final-states\":[\"q2\",\"q4\"]},\"pass-cases\":[\"00\",\"0000\",\"0101010\",\"00010\",\"11\",\"1111\",\"110101\",\"10101010\"],\"fail-cases\":[\"0001\",\"1110\",\"111000\",\"01\",\"10\",\"000111\"]}";
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