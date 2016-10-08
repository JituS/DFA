import org.junit.Assert;
import org.junit.Test;

public class TransitionsTest {
  @Test
  public void shouldReturnNewNextState() throws Exception {
    Transitions transitions = new Transitions();
    transitions.setTransition("q1", "q2", '1');
    String nextState = transitions.process("q1", '1');
    Assert.assertEquals("q2", nextState);
  }

  @Test
  public void shouldReturnNullIfStateIsNotPresent() throws Exception {
    Transitions transitions = new Transitions();
    transitions.setTransition("q1", "q2", '1');
    String nextState = transitions.process("q2", '1');
    Assert.assertEquals(null, nextState);
  }
}