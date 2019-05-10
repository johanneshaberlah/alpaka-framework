package com.github.johanneshaberlah.alpaka;

/**
 * PermissionAdapter, which allows to query application independent whether a user has a permission
 * or not. Since all target systems store permission keys as strings, this can also be used here.
 * Implementations of this class can be found in the respective modules.
 */
public interface PermissionAdapter {

  /**
   * Boolean, which indicates whether a user has permission or not. No exception can be thrown here
   * with valid native implementations.
   *
   * @param remotePlayer The user who owns a permission or not.
   * @param permission The requested permission
   * @return true: The player got the permission | false: The player don't got the permission
   */
  boolean hasPermission(RemotePlayer remotePlayer, String permission);
}
