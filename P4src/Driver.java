  import java.util.Scanner;
  import java.io.File;
  import java.nio.file.NotDirectoryException;
  import java.util.ArrayList;
  import java.util.NoSuchElementException;

  /** Class to act as driver for FileExplorer program.
   * 
   * @author Michelle
   *
   */

  public class Driver {

    private static Scanner cin = new Scanner(System.in);
    
    private static void printMenu()
    {
      System.out.println("Please select from the following list of commands: ");
      System.out.println("\t 1) List Immediate Folder Contents");
      System.out.println("\t 2) List Deep Folder Contents");
      System.out.println("\t 3) Get a Specific File's Path");
      System.out.println("\t 4) Search for Files by Key");
      System.out.println("\t 5) Search for Files by Size");
      System.out.println("\t 6) Quit");
      
    }
    
    private static void printArrayList(ArrayList<String> arrList)
    {
      for(String s: arrList)
        System.out.print(s+", ");
    }
    
    private static void getImmediateContents()
    {
      System.out.print("Please designate a folder: ");
      cin.nextLine();
      File folder = new File(cin.nextLine());
      try {
        ArrayList<String> contents = FileExplorer.listContents(folder);
        
        System.out.print("Here is the folder's contents: ");
        printArrayList(contents);
        System.out.println();
        
      }catch (NotDirectoryException e)
      {
        System.out.println(e.getMessage());
      }
      
    }
    
    private static void getDeepContents()
    {
      System.out.print("Please designate a folder: ");
      
      cin.nextLine();
      File folder = new File(cin.nextLine());
      try
      {
        ArrayList<String> contents = FileExplorer.deepListContents(folder);
        System.out.print("Here is the folder's contents: ");
        printArrayList(contents);
        System.out.println();
      }
      catch (NotDirectoryException e)
      {
        System.out.println(e.getMessage());
      }
    }
    
    private static void getFilePath()
    {
      System.out.print("Please designate a folder: ");
      cin.nextLine();
      File folder = new File(cin.nextLine()); 
      System.out.print("Please designate a file, including its extention: ");
      String fileName = cin.nextLine();
      
      try {
        
        String path = FileExplorer.searchByName(folder,fileName);
        System.out.println("Here is the file's path: " + path);
        
      }catch (NoSuchElementException e)
      {
        System.out.println(e.getMessage());
      }
    }
    
    private static void getByKey()
    {
      System.out.print("Please designate a folder: ");
      cin.nextLine();
      File folder = new File(cin.nextLine()); 
      System.out.print("Please designate a search key: ");
      String key = cin.nextLine();
      
      try 
      {  
        ArrayList<String> matches = FileExplorer.searchByKey(folder, key);
        System.out.print("Here are the files that match: ");
        printArrayList(matches);
        System.out.println(); 
      }
      catch (NoSuchElementException e)
      {
        System.out.println(e.getMessage());
      }
    }
    
    private static void getBySize()
    {
      System.out.print("Please designate a folder: ");
      cin.nextLine();
      File folder = new File(cin.nextLine()); 
      System.out.print("Please designate minimum size: ");
      long min = cin.nextLong();
      System.out.print("Please designate maximum size: ");
      long max = cin.nextLong();
      
      try {
        ArrayList<String> matches = FileExplorer.searchBySize(folder, min, max);
        System.out.print("Here are the files that match: ");
        printArrayList(matches);
        System.out.println();
      }
      catch(NoSuchElementException e)
      {
        System.out.println(e.getMessage());
      }
    }
    
    private static void handleCommand(int command)
    {
      switch(command)
      {
        case 1:
          getImmediateContents();
          break;
        case 2:
          getDeepContents();
          break;
        case 3:
          getFilePath();
          break;
        case 4:
          getByKey();
          break;
        case 5:
          getBySize();
          break;
        case 6:
          break;
        default:
          System.out.println("Invalid Command!");
          break;
      }
    }
    
    public static void main(String[] args)
    {
      System.out.println("=== Welcome to Bucky's File Explorer! === \n");
      int command = 0;   
      
      do
      {
        printMenu();
        System.out.print("\n Enter command:");
        command = cin.nextInt();
        handleCommand(command);
      }while (command != 6);
      
      cin.close();
      System.out.println("================ Goodbye!================");  
    }
  }

