import dfa.State;
import dfa.Transitions;
import org.junit.Assert;
import org.junit.Test;

public class TransitionsTest {
  @Test
  public void shouldReturnNextState() throws Exception {
    State q1 = new State("q1");
    State q2 = new State("q2");
    Transitions transitions = new Transitions();
    transitions.setTransition(q1, q2, "1");
    State nextState = transitions.process(q1, "1");
    Assert.assertEquals(q2, nextState);
  }

  @Test
  public void shouldReturnNullIfStateIsNotPresent() throws Exception {
    State q1 = new State("q1");
    State q2 = new State("q2");
    Transitions transitions = new Transitions();
    transitions.setTransition(q1, q2, "1");
    State nextState = transitions.process(q2, "1");
    Assert.assertEquals(null, nextState);
  }
}