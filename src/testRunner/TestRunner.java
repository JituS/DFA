package testRunner;

import commons.Builder;
import finiteAutomata.FiniteAutomata;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TestRunner {

  private static JSONArray readJsonFile(File file) throws FileNotFoundException, ParseException {
    Scanner scanner = new Scanner(file);
    StringBuilder jsonText = new StringBuilder();
    while(scanner.hasNextLine()){
      jsonText.append(scanner.nextLine());
    }
    String stripedJson = jsonText.toString().substring(1, jsonText.toString().length() - 1).replace("\\", "");
    return (JSONArray) new JSONParser().parse(stripedJson);
  }

  private static void testCases(FiniteAutomata fa, List<String> cases, Case aCase) {
    for (String tCase : cases) {
      ArrayList<String> testCase = new ArrayList<>(Arrays.asList(tCase.split("")));
      boolean result = aCase.verify(fa, testCase);
      if(result)  System.out.println("\t" + tCase + " : Passed ");
      else System.out.println("\t" + tCase + " : Failed");
    }
  }

  public static void main(String[] args) throws ParseException, FileNotFoundException {
    JSONArray FAJson = readJsonFile(new File("data/examples1.json"));
    for (Object fa : FAJson) {
      Builder builder = new Builder((JSONObject) fa);
      FiniteAutomata faObject = builder.buildFA();
      List<String> passCases = builder.getPassCases();
      List<String> failCases = builder.getFailCases();
      System.out.println("Passing Case For : " + faObject.getName());
      testCases(faObject, passCases, new PassCase());
      System.out.println("failing Case For : " + faObject.getName());
      testCases(faObject, failCases, new FailCase());
    }
  }

}
