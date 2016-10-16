import dfa.Builder;
import dfa.FiniteAutomata;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class NFAIntegrationTest {
  @Test
  public void shouldReturnTrueIfStringIsAccepted() throws Exception {
    String jsonString ="{\"name\":\"0*1* or 1*0*\",\"type\":\"nfa\",\"tuple\":{\"states\":[\"q1\",\"q3\",\"q2\",\"q5\",\"q4\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"e\":[\"q2\",\"q4\"]},\"q2\":{\"0\":[\"q2\"],\"e\":[\"q3\"]},\"q3\":{\"1\":[\"q3\"]},\"q4\":{\"1\":[\"q4\"],\"e\":[\"q5\"]},\"q5\":{\"0\":[\"q5\"]}},\"start-state\":\"q1\",\"final-states\":[\"q3\",\"q5\"]},\"pass-cases\":[\"\",\"0\",\"1\",\"00\",\"11\",\"001\",\"110\",\"011\",\"100\",\"0011\",\"1100\"],\"fail-cases\":[\"101\",\"010\",\"11001\",\"00110\",\"0101\",\"1010\"]}";

    JSONObject FAJson = (JSONObject) new JSONParser().parse(jsonString);
    Builder builder = new Builder(FAJson);

    FiniteAutomata finiteAutomata = builder.buildFA();
    ArrayList<String> passCases = builder.getPassCases();
    ArrayList<String> failCases = builder.getFailCases();
    testCases(finiteAutomata, passCases, failCases);
  }

  private void testCases(FiniteAutomata nfa, ArrayList<String> passCases, ArrayList<String> failCases) {
    for (String passCase : passCases) {
      ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(passCase.split("")));
      Assert.assertTrue(nfa.verify(arrayList));
    }
    for (String failCase : failCases) {
      ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(failCase.split("")));
      boolean verify = nfa.verify(arrayList);
      Assert.assertFalse(verify);
    }
  }
}
