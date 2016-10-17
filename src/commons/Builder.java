package commons;

import finiteAutomata.DFA;
import finiteAutomata.FiniteAutomata;
import finiteAutomata.NFA;
import org.json.simple.JSONObject;

import java.util.List;

import static utils.GenericMapper.*;

public class Builder {

  private JSONObject jsonObject;

  public Builder(JSONObject jsonObject) {
    this.jsonObject = jsonObject;
  }

  public FiniteAutomata buildFA() {
    String name = (String) jsonObject.get("name");
    String type = (String) jsonObject.get("type");
    JSONObject tuple = (JSONObject) jsonObject.get("tuple");
    JSONObject transitions = (JSONObject) tuple.get("delta");
    if(type.equals("nfa")){
      Tuple tupleObject = mapToFATuple(tuple, getNFATransition(transitions));
      return new NFA(tupleObject,  type + ":" + name);
    }
    Tuple tupleObject = mapToFATuple(tuple, getDFATransition(transitions));
    return new DFA(tupleObject, type + ":" + name);
  }

  public List<String> getPassCases() {
    return  mapObjectListToStringList(jsonObject.get("pass-cases"));
  }

  public List<String> getFailCases() {
    return  mapObjectListToStringList(jsonObject.get("fail-cases"));
  }
}
