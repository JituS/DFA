package commons;

import org.json.simple.JSONObject;

interface ITupleParser<T> {
  ITransition<T> parseTransitions(JSONObject tuple);
  Tuple<T> parseTuple(JSONObject tuple);
}
