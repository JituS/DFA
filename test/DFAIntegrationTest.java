import dfa.DFA;
import dfa.JsonToFAParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DFAIntegrationTest {
  @Test
  public void shouldReturnTrueIfStringIsAccepted() throws Exception {
    String jsonString = "[{\"name\":\"odd number of zeroes\"," +
      "\"type\":\"dfa\"," +
      "\"tuple\":{\"states\":[\"q1\",\"q2\"]," +
      "\"alphabets\":[\"1\",\"0\"]," +
      "\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}}," +
      "\"start-state\":\"q1\"," +
      "\"final-states\":[\"q2\"]}," +
      "\"pass-cases\":[\"0\",\"000\",\"00000\",\"10\",\"101010\",\"010101\"]," +
      "\"fail-cases\":[\"00\",\"0000\",\"1001\",\"1010\",\"001100\"]}]";

    ArrayList<DFA> DFAs = new JsonToFAParser().parse(jsonString);
    DFAs.forEach(this::testCases);
  }

  private void testCases(DFA dfa) {
    ArrayList<String> passCases = dfa.getPassCases();
    ArrayList<String> failCases = dfa.getFailCases();
    for (String passCase : passCases) {
      ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(passCase.split("")));
      Assert.assertTrue(dfa.verify(arrayList));
    }
    for (String failCase : failCases) {
      ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(failCase.split("")));
      Assert.assertFalse(dfa.verify(arrayList));
    }
  }

  @Test
  public void shouldPassAllCasesForEachDFA() throws Exception {
    Scanner scanner = new Scanner(new File("data/examples.json"));
    StringBuilder jsonText = new StringBuilder();
    while(scanner.hasNextLine()){
      jsonText.append(scanner.nextLine());
    }

    String stripedJson = jsonText.toString().substring(1, jsonText.toString().length() - 1).replace("\\", "");

    ArrayList<DFA> DFAs = new JsonToFAParser().parse(stripedJson);

    DFAs.forEach(this::testCases);
  }
}
