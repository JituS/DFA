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
    JSONObject tuple = (JSONObject) jsonObject.get("tuple");
    JSONObject transitions = (JSONObject) tuple.get("delta");
    String faName = name.toUpperCase();
    String FAType = type.toUpperCase();
    if (type.equals("nfa")) {
      Tuple tupleObject = mapToFATuple(tuple, getNFATransition(transitions));
      return new NFA(tupleObject, FAType + " : " + faName);
    }
    Tuple tupleObject = mapToFATuple(tuple, getDFATransition(transitions));
    return new DFA(tupleObject, FAType + " : " + faName);
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
