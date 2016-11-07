package utils;

import commons.State;
import commons.States;

import java.util.ArrayList;
import java.util.List;

public class GenericMapper {
  public static List<String> mapToList(Object states) {
    List strings = (List) states;
    ArrayList<String> mappedStrings = new ArrayList<>();
    for (Object string : strings) {
      mappedStrings.add((String) string);
    }
    return mappedStrings;
  }

  public static States mapToStates(Object tuple) {
    States states = new States();
    mapToList(tuple).forEach(state -> states.add(new State(state)));
    return states;
  }
}