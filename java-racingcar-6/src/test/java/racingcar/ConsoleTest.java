package racingcar;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class ConsoleTest {

  private PrintStream standardOut;
  private OutputStream captor;

  @BeforeEach
  protected final void init() {
    this.standardOut = System.out;
    this.captor = new ByteArrayOutputStream();
    System.setOut(new PrintStream(this.captor));
  }

  @AfterEach
  protected final void printOutput() {
    System.setOut(this.standardOut);
    System.out.println(this.output());
  }

  protected final String output() {
    return this.captor.toString().trim();
  }

  protected final void command(String... args) {
    byte[] buf = String.join("\n", args).getBytes();
    System.setIn(new ByteArrayInputStream(buf));
  }
}
