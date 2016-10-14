package dfa;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class JsonToFAParser {

  public ArrayList<DFA> parse(String jsonText) throws ParseException {
    ArrayList<DFA> allDFAs = new ArrayList<>();
    JSONArray DFAJson = (JSONArray) new JSONParser().parse(jsonText);

    for (Object dfa : DFAJson) {
      DFA dfaObject = new DFABuilder((JSONObject) dfa).build();
      allDFAs.add(dfaObject);
    }

    return allDFAs;
  }

}
