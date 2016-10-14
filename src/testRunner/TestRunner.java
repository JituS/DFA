package testRunner;

import dfa.Builder;
import dfa.FiniteAutomata;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TestRunner {

  private static class PassCase implements Cases{
    @Override
    public boolean verify(FiniteAutomata dfa, ArrayList<String> eachCase){
      return dfa.verify(eachCase);
    }
  }

  private static class FailCase implements Cases{
    @Override
    public boolean verify(FiniteAutomata dfa, ArrayList<String> eachCase){
      return !dfa.verify(eachCase);
    }
  }

  private static void testCases(FiniteAutomata dfa, ArrayList<String> cases, Cases passCase) {
    for (String eachCase : cases) {
      ArrayList<String> eachCaseSplit = new ArrayList<>(Arrays.asList(eachCase.split("")));
      boolean result = passCase.verify(dfa, eachCaseSplit);
      printStatus(eachCase, result);
    }
  }

  private static void printStatus(String passCase, boolean result) {
    if(result){
      System.out.println("\t" + passCase + " : Passed ");
    }else {
      System.out.println("\t" + passCase + " : Failed");
    }
  }

  public static void main(String[] args) throws ParseException, FileNotFoundException {
    Scanner scanner = new Scanner(new File("data/examples.json"));
    StringBuilder jsonText = new StringBuilder();
    while(scanner.hasNextLine()){
      jsonText.append(scanner.nextLine());
    }
    String stripedJson = jsonText.toString().substring(1, jsonText.toString().length() - 1).replace("\\", "");

    JSONArray FAJson = (JSONArray) new JSONParser().parse(stripedJson);

    for (Object fa : FAJson) {
      Builder builder = new Builder((JSONObject) fa);
      FiniteAutomata dfaObject = builder.buildFA();
      ArrayList<String> passCases = builder.getPassCases();
      ArrayList<String> failCases = builder.getFailCases();
      System.out.println("Passing Cases For : " + dfaObject.getName());
      testCases(dfaObject, passCases, new PassCase());
      System.out.println("failing Cases For : " + dfaObject.getName());
      testCases(dfaObject, failCases, new FailCase());
    }
  }
}
