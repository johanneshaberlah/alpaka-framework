package com.github.johanneshaberlah.alpaka;

/** Class to find the appropriate command for a given text. */
public final class CommandMatcher {

  private CommandMatcher() {}

  /**
   * Specifies whether the specified arguments match the expected arguments.
   *
   * @param usage The required arguments
   * @param arguments The supplied arguments
   * @return true / false
   */
  boolean matches(String[] usage, String[] arguments) {
    int matchingArguments = 0;
    for (int i = 0; i < usage.length; i++) {
      String usageArgument = usage[i];
      if (usageArgument.startsWith("<")
          || usageArgument.startsWith("(")
          || usageArgument.startsWith("[")) {
        matchingArguments++;
        continue;
      }
      if (usageArgument.startsWith("/")) {
        usageArgument = usageArgument.substring(1);
      }
      String givenArgument = arguments[i];
      if (givenArgument.startsWith("/")) {
        givenArgument = givenArgument.substring(1);
      }
      if (usageArgument.equalsIgnoreCase(givenArgument)) {
        matchingArguments++;
      }
    }
    return matchingArguments == arguments.length;
  }

  public boolean matches(Command command, CommandContext context) {
    String usage = "";
    if (command.isSubCommand()) {
      SubCommandInfo subCommandInfo = command.getClass().getAnnotation(SubCommandInfo.class);
      usage = subCommandInfo.usage();
    } else {
      CommandInfo commandInfo = command.getClass().getAnnotation(CommandInfo.class);
      usage = commandInfo.usage();
    }
    if (!this.matches(usage.split(" "), context.arguments().raw())) {
      return false;
    }
    return true;
  }

  public static CommandMatcher create() {
    return new CommandMatcher();
  }
}
