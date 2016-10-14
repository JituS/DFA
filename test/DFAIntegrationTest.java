import dfa.Builder;
import dfa.FiniteAutomata;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class DFAIntegrationTest {
  @Test
  public void shouldReturnTrueIfStringIsAccepted() throws Exception {
    String jsonString = "{\"name\":\"odd number of zeroes\"," +
      "\"type\":\"dfa\"," +
      "\"tuple\":{\"states\":[\"q1\",\"q2\"]," +
      "\"alphabets\":[\"1\",\"0\"]," +
      "\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}}," +
      "\"start-state\":\"q1\"," +
      "\"final-states\":[\"q2\"]}," +
      "\"pass-cases\":[\"0\",\"000\",\"00000\",\"10\",\"101010\",\"010101\"]," +
      "\"fail-cases\":[\"00\",\"0000\",\"1001\",\"1010\",\"001100\"]}";

    JSONObject FAJson = (JSONObject) new JSONParser().parse(jsonString);
    Builder builder = new Builder(FAJson);

    FiniteAutomata finiteAutomata = builder.buildFA();
    ArrayList<String> passCases = builder.getPassCases();
    ArrayList<String> failCases = builder.getFailCases();
    testCases(finiteAutomata, passCases, failCases);
  }

  private void testCases(FiniteAutomata dfa, ArrayList<String> passCases, ArrayList<String> failCases) {
    for (String passCase : passCases) {
      ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(passCase.split("")));
      Assert.assertTrue(dfa.verify(arrayList));
    }
    for (String failCase : failCases) {
      ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(failCase.split("")));
      Assert.assertFalse(dfa.verify(arrayList));
    }
  }
}
