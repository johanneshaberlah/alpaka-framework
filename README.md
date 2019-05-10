# alpaka-framework
W.I.P lightweight framework for handling commands and subcommands. Support is planned for Discord, BungeeCord and Spigot.

## Installation

Maven:
```xml
<dependency>
  <groupId>com.github.johanneshaberlah</groupId>
  <artifactId>alpaka-<your-prefered-implementation></artifactId>
  <version>0.0.1</version>
</dependency>
```

Gradle:
```xml
  compile group: 'com.github.johanneshaberlah', name: 'alpaka-<your-prefered-implementation>', version: '0.0.1'
```

## Usage

```java
import com.github.johanneshaberlah.alpaka.Command;
import com.github.johanneshaberlah.alpaka.CommandContext;
import com.github.johanneshaberlah.alpaka.CommandInfo;

@CommandInfo(
    usage = "example <message>",
    permission = "example.permission",
    subCommandPackage = "com.github.johanneshaberlah.alpaka.example.subcommand")
public class ExampleCommand implements Command {

  @Override
  public void execute(CommandContext context) {
    ExampleObject exampleObject = context.arguments().require(ExampleObject.class);
    System.out.println("Parsed ExampleObject with content " + exampleObject.getText());
  }
}
```
```
import com.github.johanneshaberlah.alpaka.CommandContext;
import com.github.johanneshaberlah.alpaka.SubCommand;
import com.github.johanneshaberlah.alpaka.SubCommandInfo;
import com.github.johanneshaberlah.alpaka.example.ExampleCommand;
import com.github.johanneshaberlah.alpaka.example.ExampleObject;
import com.github.johanneshaberlah.alpaka.exception.CommandException;

@SubCommandInfo(parent = ExampleCommand.class, usage = "example print <message>", permission = "")
public class ExampleSubCommand implements SubCommand {

  @Override
  public void execute(CommandContext context) throws CommandException {
    context.arguments().skip();
    ExampleObject exampleObject = context.arguments().require(ExampleObject.class);
    System.out.println("ExampleObject Content is: " + exampleObject.getText());
  }
}
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate and please use make sure you are using the Google StyleGuide.

## License
[MIT](https://choosealicense.com/licenses/mit/)
