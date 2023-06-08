//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P08 LIFO Inbox Reader
// Course: CS 300 Fall 2020
//
// Author: Andrew McAvoy
// Email: apmcavoy@wisc.edu
// Lecturer: (Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////
public class Inbox {
  private MessageStack readMessageBox;
  private MessageStack unreadMessageBox;

  /**
   * default constructor for inbox
   */
  public Inbox() {
    readMessageBox = new MessageStack();
    unreadMessageBox = new MessageStack();
  }

  /**
   * reads the first message in unreadMessageBox and puts it in readMessageBox
   * 
   * @return formatted string containing the message's info
   */
  public String readMessage() {
    if (unreadMessageBox.isEmpty())
      return "Nothing in Unread";
    Message reading = unreadMessageBox.pop();
    readMessageBox.push(reading);
    return reading.toString();
  }

  /**
   * looks at the topmost message on readMessageBox's stack
   * 
   * @return String containing the message's info
   */
  public String peekReadMessage() {
    if (readMessageBox.isEmpty())
      return "Nothing in Read";
    return readMessageBox.peek().toString();
  }

  /**
   * empties all messages from unreadMessageBox and puts them in readMessageBox
   * 
   * @return number of messages marked as read
   */
  public int markAllMessagesAsRead() {
    int messagesRead = 0;
    while (!unreadMessageBox.isEmpty()) {
      Message reading = unreadMessageBox.pop();
      readMessageBox.push(reading);
      messagesRead++;
    }
    return messagesRead;


  }

  /**
   * adds a new message to the unreadMessageBox
   * 
   * @param newMessage message to be added to unreadMessageBox
   */
  public void receiveMessage(Message newMessage) {
    unreadMessageBox.push(newMessage);
  }

  /**
   * removes all messages from readMessageBox
   * 
   * @return number of messages cleared from readMessageBox
   */
  public int emptyReadMessageBox() {
    int messagesCleared = 0;
    while (!readMessageBox.isEmpty()) {
      readMessageBox.pop();
      messagesCleared++;
    }
    return messagesCleared;
  }

  /**
   * @return formatted string that tells the user how many read and unread messages are in the
   *         inboxes
   */
  public String getStatistics() {
    return "Unread " + unreadMessageBox.size() + "\n" + "Read " + readMessageBox.size();
  }

  /**
   * traverses the unreadMessagebox and returns a string with all of the messages' data
   * 
   * @return formatted string containing data of all of the messages
   */
  public String traverseUnreadMessages() {
    String data = "Unread(" + unreadMessageBox.size() + ")\n";
    for (Message current : unreadMessageBox) {
      data += current.getID() + " " + current.getSUBJECT() + "\n";
    }
    return data;
  }

  /**
   * traverses the readMessagebox and returns a string with all of the messages' data
   * 
   * @return formatted string containing data of all of the messages
   */
  public String traverseReadMessages() {
    String data = "Read(" + readMessageBox.size() + ")\n";
    for(Message current : readMessageBox) {
      data += current.getID() + " " + current.getSUBJECT() + "\n";
    }
    return data;
  }
}
