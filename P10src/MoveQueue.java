//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P10 Battle System
// Course:   CS 300 Fall 2020
//
// Author:   Andrew McAvoy
// Email:    apmcavoy@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         none
// Online Sources:  zybooks gave assistane with percolateUp/down and heapify algos
//
///////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;

public class MoveQueue implements PriorityQueueADT<BattleCharacter> {
  private BattleCharacter[] data;
  private int size;

  public MoveQueue(int capacity) {
    if (capacity <= 0)
      throw new IllegalArgumentException("Capacity must be greater than 0");
    data = new BattleCharacter[capacity];
    size = 0;
  }

  public MoveQueue() {
    data = new BattleCharacter[10];
    size = 0;
  }

  /**
   * Checks if this priority queue is empty. Returns true if it is empty and false otherwise.
   */
  @Override
  public boolean isEmpty() {
    if (size == 0 && data[0] == null)
      return true;
    return false;
  }

  /**
   * Returns the current size of this priority queue.
   * 
   * @return the size of this priority queue
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Adds the given element to the priority queue in the correct position based on the natural
   * ordering of the elements.
   * 
   * @param element to be added to this queue
   * @throws IllegalArgumentException if element is null
   * @throws IllegalStateException    of this priority queue is full
   */
  @Override
  public void enqueue(BattleCharacter element) {
    if (data[data.length - 1] != null)
      throw new IllegalStateException("Array is full");
    if (element == null)
      throw new IllegalArgumentException("Cannot add null to array");
    if (size < data.length) {
      data[size] = element;
      percolateUp(size);
      size++;
    }



  }

  /**
   * Returns and removes the element at the front (aka root position) of this queue (the element
   * having the highest priority).
   * 
   * @return the removed element
   * @throws NoSuchElementException if this queue is empty
   */
  @Override
  public BattleCharacter dequeue() {
    if (isEmpty())
      throw new NoSuchElementException("Array is empty");
    BattleCharacter returning = data[0];
    data[0] = data[size - 1];
    data[size - 1] = null;
    percolateDown(0);
    size--;
    return returning;
  }

  /**
   * Returns without removing the element at the front (aka root position) of this queue (the
   * element having the highest priority).
   * 
   * @return the element with the highest priority in this queue
   * @throws NoSuchElementException if this queue is empty
   */
  @Override
  public BattleCharacter peekBest() {
    if (isEmpty())
      throw new NoSuchElementException("Array is empty");
    return data[0];
  }

  /**
   * Removes all the elements from this priority queue. The queue must be empty after this method
   * returns.
   */
  @Override
  public void clear() {
    for (int i = 0; i < size; i++) {
      data[i] = null;
    }
    size = 0;
  }

  /**
   * checks upwards in a max-heap for violations of max-heap rules
   * 
   * @param i index to percolate up
   */
  protected void percolateUp(int i) {
    if (i > 0) {
      int parentIndex = (i - 1) / 2;
      if (data[i].compareTo(data[parentIndex]) < 0)
        return;
      else {
        BattleCharacter temp = data[parentIndex];
        data[parentIndex] = data[i];
        data[i] = temp;
        percolateUp(parentIndex);
      }
    }
  }

  /**
   * recursively checks downwards for violations of max-heap rules
   * 
   * @param i index to percolate down
   */
  protected void percolateDown(int i) {
    int leftChild = 2 * i + 1;
    int rightChild = 2 * i + 2;
    int largerValue = i;
    if (leftChild >= size() || data[leftChild] == null)
      return;
    else if (rightChild >= size() || data[rightChild] == null) {
      if (data[largerValue].compareTo(data[leftChild]) < 0) {
        largerValue = leftChild;
      }
      if (largerValue != i) {
        BattleCharacter temp = data[i];
        data[i] = data[largerValue];
        data[largerValue] = temp;
        percolateDown(largerValue);
      }
    } else {
      if (data[largerValue].compareTo(data[leftChild]) < 0) {
        largerValue = leftChild;
      }
      if (data[largerValue].compareTo(data[rightChild]) < 0) {
        largerValue = rightChild;
      }
      if (largerValue != i) {
        BattleCharacter temp = data[i];
        data[i] = data[largerValue];
        data[largerValue] = temp;
        percolateDown(largerValue);
      }
    }
  }

  /**
   * converts queue into proper max-heap
   */
  protected void heapify() {
    if (size() < 1)
      return;
    for (int i = size() / 2 - 1; i >= 0; i--) {
      percolateDown(i);
    }
  }

  /**
   * updates a BattleCharacter's statistics with a new reference
   * 
   * @param updateChara character to replace the old reference with
   */
  public void updateCharacter(BattleCharacter updateChara) {
    for (int i = 0; i < size; i++) {
      if (updateChara.equals(data[i])) {
        if (data[i].isAlive()) {
          data[i] = updateChara;
          heapify();
        } else {
          data[i] = data[size - 1]; // gapIndex is the index of the dead character
          data[size - 1] = null;
          size--;
          heapify();
        }
        break;
      }
    }
  }

  /***
   * Returns a String representation of the current contents of the MoveQueue * in order from first
   * to last. * @author Michelle
   */
  @Override
  public String toString() {
    String s = ("[ ");
    for (int i = 0; i < size; i++) {
      s += (data[i].toString() + " | ");
    }
    s += ("]");
    return s;
  }

}
