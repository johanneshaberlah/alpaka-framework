import com.github.johanneshaberlah.alpaka.*;
import com.github.johanneshaberlah.alpaka.example.ExampleObject;
import com.github.johanneshaberlah.alpaka.example.ExampleObjectArgumentType;
import com.github.johanneshaberlah.alpaka.exception.CommandException;
import org.junit.Test;

import java.util.UUID;

public class ExampleCommandTest {

  @Test
  public void test_Command() {
    CommandFactory commandFactory = CommandFactory.create();
    CommandContextFactory contextFactory =
        CommandContextFactory.create(
            (remotePlayer, message) -> System.out.println(remotePlayer.getName() + ": " + message));
    contextFactory.registerAdapter(ExampleObject.class, ExampleObjectArgumentType.create());
    CommandContext commandContext =
        contextFactory.create(
            RemotePlayer.create(UUID.randomUUID(), "TestUser"), new String[] {"example", "print", "Hello"});
    Command command = commandFactory.create(commandContext).get();
    try {
      command.execute(commandContext);
    } catch (CommandException e) {
      e.printStackTrace();
    }
  }
}
