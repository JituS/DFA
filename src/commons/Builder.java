package commons;
import com.thoughtworks.testrunner.FiniteAutomata;
import com.thoughtworks.testrunner.IBuilder;
import finiteAutomata.DFA;
import finiteAutomata.NFA;
import org.json.simple.JSONObject;

import java.util.List;

import static utils.GenericMapper.*;

public class Builder implements IBuilder {

  private JSONObject jsonObject;

  public Builder(JSONObject jsonObject) {
    this.jsonObject = jsonObject;
  }

  @Override
  public FiniteAutomata buildFA() {
    String name = (String) jsonObject.get("name");
    String type = (String) jsonObject.get("type");
    String faName = name.toUpperCase() + " : "+ type.toUpperCase();
    JSONObject tuple = (JSONObject) jsonObject.get("tuple");
    switch (type) {
      case "nfa":
        return new NFA(new NfaTupleParser().parseTuple(tuple), faName);
      case "nfa-to-dfa":
        return new NFA(new NfaTupleParser().parseTuple(tuple), faName).toDFA();
      default:
        return new DFA(new DfaTupleParser().parseTuple(tuple), faName);
    }
  }

  @Override
  public List<String> getPassCases() {
    return mapToList(jsonObject.get("pass-cases"));
  }

  @Override
  public List<String> getFailCases() {
    return mapToList(jsonObject.get("fail-cases"));
  }
}
