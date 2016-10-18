import com.thoughtworks.testrunner.TestRunner;
import commons.Builder;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MainClass {
  public static void main(String[] args) throws FileNotFoundException, ParseException {
    ArrayList<JSONObject> jsonObjects = TestRunner.readJsonFile(new File("data/examples.json"));
    for (JSONObject jsonObject : jsonObjects) {
      Builder builder = new Builder(jsonObject);
      TestRunner testRunner = new TestRunner(builder);
      testRunner.runTestCase();
    }
  }
}
