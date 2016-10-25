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
    JSONObject transitions = (JSONObject) tuple.get("delta");

    Tuple mappedTuple;

    switch (type) {
      case "nfa":
        mappedTuple = mapToFATuple(tuple, getNFATransition(transitions));
        return new NFA(mappedTuple, faName);
      case "nfa-to-dfa":
        mappedTuple = mapToFATuple(tuple, getNFATransition(transitions));
        return new NFA(mappedTuple, faName).toDFA();
      default:
        mappedTuple = mapToFATuple(tuple, getDFATransition(transitions));
        return new DFA(mappedTuple, faName);
    }
  }

  @Override
  public List<String> getPassCases() {
    return mapObjectListToStringList(jsonObject.get("pass-cases"));
  }

  @Override
  public List<String> getFailCases() {
    return mapObjectListToStringList(jsonObject.get("fail-cases"));
  }
}
