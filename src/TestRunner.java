import dfa.DFA;
import dfa.JsonToFAParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TestRunner {

  private static void testPassingCases(DFA dfa) {
    ArrayList<String> passCases = dfa.getPassCases();
    System.out.println(dfa.getName());
    for (String passCase : passCases) {
      ArrayList<String> eachPassCase = new ArrayList<>(Arrays.asList(passCase.split("")));
      boolean result = dfa.verify(eachPassCase);
      printStatus(passCase, result);
    }
  }

  private static void testFailingCases(DFA dfa) {
    ArrayList<String> failCases = dfa.getFailCases();
    System.out.println(dfa.getName());
    for (String failCase : failCases) {
      ArrayList<String> eachFailCase = new ArrayList<>(Arrays.asList(failCase.split("")));
      boolean result = dfa.verify(eachFailCase);
      printStatus(failCase, !result);
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

    ArrayList<DFA> allDFAs = new JsonToFAParser().parse(stripedJson);
    System.out.println("For PassCases :");
    for (DFA dfa : allDFAs) {
      testPassingCases(dfa);
    }
    System.out.println("For FailCases :");
    for (DFA dfa : allDFAs) {
      testFailingCases(dfa);
    }
  }

}
