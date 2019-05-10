package com.github.johanneshaberlah.alpaka;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

import java.util.UUID;

/**
 * RemotePlayer class to ensure absolute application independence. Since there will be an
 * implementation of this framework for various platforms like Discord or BungeeCord, we need here a
 * class with some information that should be available in all systems.
 */
public class RemotePlayer {

  private UUID uniqueId;
  private String name;

  /**
   * Private constructor of the class. This is called in the static factory method to ensure
   * preconditions and not to have too much responsibility on the constructor.
   *
   * @param uniqueId The uniqueid of the player
   * @param name The name of the player
   */
  private RemotePlayer(UUID uniqueId, String name) {
    this.uniqueId = uniqueId;
    this.name = name;
  }

  public UUID getUniqueId() {
    return uniqueId;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RemotePlayer that = (RemotePlayer) o;
    return Objects.equal(uniqueId, that.uniqueId) && Objects.equal(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(uniqueId, name);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("uuid", uniqueId).add("name", name).toString();
  }

  public static RemotePlayer create(UUID uniqueId, String name) {
    Preconditions.checkNotNull(name);
    Preconditions.checkNotNull(uniqueId);
    return new RemotePlayer(uniqueId, name);
  }
}
