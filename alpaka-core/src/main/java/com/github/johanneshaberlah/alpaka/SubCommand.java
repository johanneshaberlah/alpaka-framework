package com.github.johanneshaberlah.alpaka;

import com.github.johanneshaberlah.alpaka.exception.CommandException;

/**
 * Subclass of the command class. SubCommand just don't implement the command class to give more
 * overview. In addition, the loadSubCommands method is displayed here as Deprecated, since a
 * SubCommand logically cannot have any more SubCommands.
 */
public interface SubCommand extends Command {

  /**
   * This method is called as soon as the CommandMatcher detects a SubCommand. A PermissionCheck has
   * not yet been performed to allow free handling. However, a PermissionAdapter is passed in the
   * CommandContext. More documentation at Command superclass.
   *
   * @param context Context class that contains some information about the command execution.
   * @throws CommandException Causes a CommandException with faulty arguments or similar. This
   *     should not happen because the command is checked beforehand.
   */
  void execute(CommandContext context) throws CommandException;

  /**
   * Method to load SubCommands. This implementation only deactivates this method, since a
   * SubCommand may no longer have SubCommands.
   */
  @Deprecated
  default void loadSubCommands() {
    System.out.println("Could not load subcommands for a subcommand!");
  }
}
