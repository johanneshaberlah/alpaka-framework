package com.github.johanneshaberlah.alpaka;

import com.google.common.base.Preconditions;

import java.util.Iterator;

/**
 * Util class to incrementally iterate through an array. This class is needed to enable the
 * semantics of the commands.
 *
 * @param <E> The type of the supplied array.
 */
public final class ElementIterator<E> implements Iterator<E> {

  private E[] e;
  private int index = -1;

  private ElementIterator(E[] e) {
    this.e = e;
  }

  private ElementIterator(E[] e, int index) {
    this.e = e;
    this.index = index;
  }

  /** Returns the next object in the specified array. */
  @Override
  public E next() {
    index++;
    return e[index];
  }

  /** Returns whether there is an object left in the array */
  public boolean hasNext() {
    if (index < (e.length - 1)) {
      return false;
    }
    return true;
  }

  public static <E> ElementIterator create(E[] e) {
    Preconditions.checkNotNull(e);
    return new ElementIterator(e);
  }

  public static <E> ElementIterator create(E[] e, int index) {
    Preconditions.checkNotNull(e);
    return new ElementIterator(e, index);
  }
}
