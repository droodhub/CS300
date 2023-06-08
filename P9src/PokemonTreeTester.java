import java.util.NoSuchElementException;

////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title:    P09 Pokemon Catalog
//Course:   CS 300 Fall 2020
//
//Author:   Andrew McAvoy
//Email:    apmcavoy@wisc.edu
//Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
//Persons:         (identify each by name and describe how they helped)
//Online Sources:  zybooks -- hleped with search + add algorithms in pokemontree
//
///////////////////////////////////////////////////////////////////////////////
/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * PokemonTree.
 *
 */

public class PokemonTreeTester {

  /**
   * Checks the correctness of the implementation of both addPokemon() and toString() methods
   * implemented in the PokemonTree class. This unit test considers at least the following
   * scenarios. (1) Create a new empty PokemonTree, and check that its size is 0, it is empty, and
   * that its string representation is an empty string "". (2) try adding one Pokemon and then check
   * that the addPokemon() method call returns true, the tree is not empty, its size is 1, and the
   * .toString() called on the tree returns the expected output. (3) Try adding another Pokemon
   * which is more powerful than the one at the root, (4) Try adding a third Pokemon which is less
   * powerful than the one at the root, (5) Try adding at least two further Pokemons such that one
   * must be added at the left subtree, and the other at the right subtree. For all the above
   * scenarios, and more, double check each time that size() method returns the expected value, the
   * add method call returns true, and that the .toString() method returns the expected string
   * representation of the contents of the binary search tree in an ascendant order from the most
   * powerful Pokemon to the least powerful one. (6) Try adding a Pokemon whose CP value was used as
   * a key for a Pokemon already stored in the tree. Make sure that the addPokemon() method call
   * returned false, and that the size of the tree did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddPokemonToStringSize() {
    PokemonTree bst = new PokemonTree();
    if ((!bst.isEmpty()) || bst.size() != 0 || bst.toString() != "")
      return false;
    if (!(bst.addPokemon(new Pokemon("Pikachu", "1,2,3"))) || bst.size() != 1
        || !bst.toString().equals("[Pikachu CP:123 (A:1 S:2 D:3)]\n"))
      return false;
    String expected = "[Pikachu CP:123 (A:1 S:2 D:3)]\n[Eevee CP:224 (A:2 S:2 D:4)]\n";
    if (!bst.addPokemon(new Pokemon("Eevee", "2,2,4")) || bst.size() != 2
        || !bst.toString().equals(expected))
      return false;
    expected =
        "[Koffing CP:111 (A:1 S:1 D:1)]\n[Pikachu CP:123 (A:1 S:2 D:3)]\n[Eevee CP:224 (A:2 S:2 D:4)]\n";
    if (!bst.addPokemon(new Pokemon("Koffing", "1,1,1")) || bst.size() != 3
        || !bst.toString().equals(expected))
      return false;
    expected =
        "[Koffing CP:111 (A:1 S:1 D:1)]\n[Psyduck CP:114 (A:1 S:1 D:4)]\n[Pikachu CP:123 (A:1 S:2 D:3)]\n[Eevee CP:224 (A:2 S:2 D:4)]\n";
    if (!bst.addPokemon(new Pokemon("Psyduck", "1,1,4")) || bst.size() != 4
        || !bst.toString().equals(expected))
      return false;
    expected =
        "[Koffing CP:111 (A:1 S:1 D:1)]\n[Psyduck CP:114 (A:1 S:1 D:4)]\n[Pikachu CP:123 (A:1 S:2 D:3)]\n[Eevee CP:224 (A:2 S:2 D:4)]\n[Charmeleon CP:283 (A:2 S:8 D:3)]\n";
    if (!bst.addPokemon(new Pokemon("Charmeleon", "2,8,3")) || bst.size() != 5
        || !bst.toString().equals(expected))
      return false;
    if (bst.addPokemon(new Pokemon("Clone", "1,1,1")) || bst.size() == 6)
      return false;
    return true;
  }

  /**
   * This method checks mainly for the correctness of the PokemonTree.lookup() method. It must
   * consider at least the following test scenarios. (1) Create a new PokemonTree. Then, check that
   * calling the lookup() method with any valid CP value must throw a NoSuchElementException. (2)
   * Consider a PokemonTree of height 3 which consists of at least 5 PokemonNodes. Then, try to call
   * lookup() method to search for the Pokemon at the root of the tree, then a Pokemon at the right
   * and left subtrees at different levels. Make sure that the lookup() method returns the expected
   * output for every method call. (3) Consider calling .lookup() method on a non-empty PokemonTree
   * with a CP value not stored in the tree, and ensure that the method call throws a
   * NoSuchElementException.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddPokemonAndLookup() {
    PokemonTree bst = new PokemonTree();
    try {
      bst.lookup(145);
      return false;
    } catch (NoSuchElementException e) {
      System.out.println("Successfully caught exception when lookup() was called on an empty tree");
    }
    Pokemon pikachu = new Pokemon("Pikachu", "1,2,3");
    Pokemon eevee = new Pokemon("Eevee", "2,2,3");
    Pokemon snorlax = new Pokemon("Snorlax", "4,2,3");
    Pokemon psyduck = new Pokemon("Psyduck", "0,8,3");
    Pokemon onyx = new Pokemon("Onyx", "2,1,1");
    bst.addPokemon(pikachu);
    bst.addPokemon(eevee);
    bst.addPokemon(snorlax);
    bst.addPokemon(psyduck);
    bst.addPokemon(onyx);
    if (bst.lookup(123) != pikachu)
      return false;
    if (bst.lookup(83) != psyduck)
      return false;
    if (bst.lookup(423) != snorlax)
      return false;
    try {
      bst.lookup(699);
      return false;
    } catch (NoSuchElementException e) {
      System.out.println(
          "Successfully caught exception when lookup() was called with a nonexistent CP value");
    }
    return true;
  }

  /**
   * Checks for the correctness of PokemonTree.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty Pokemon tree is zero. (2) ensures
   * that the height of a tree which consists of only one node is 1. (3) ensures that the height of
   * a PokemonTree with the following structure for instance, is 4. (*) / \ (*) (*) \ / \ (*)(*) (*)
   * / (*)
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    PokemonTree bst = new PokemonTree();
    if (bst.height() != 0)
      return false;
    Pokemon pikachu = new Pokemon("Pikachu", "1,2,3");
    Pokemon eevee = new Pokemon("Eevee", "2,2,3");
    Pokemon snorlax = new Pokemon("Snorlax", "4,2,3");
    Pokemon psyduck = new Pokemon("Psyduck", "0,8,3");
    Pokemon onyx = new Pokemon("Onyx", "2,1,1");
    bst.addPokemon(pikachu);
    if (bst.height() != 1)
      return false;
    bst.addPokemon(eevee);
    bst.addPokemon(snorlax);
    bst.addPokemon(psyduck);
    bst.addPokemon(onyx);
    if (bst.height() != 3)
      return false;

    return true;
  }

  /**
   * Checks for the correctness of PokemonTree.getLeastPowerfulPokemon() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetLeastPowerfulPokemon() {
    PokemonTree bst = new PokemonTree();
    Pokemon pikachu = new Pokemon("Pikachu", "1,2,3");
    Pokemon eevee = new Pokemon("Eevee", "2,2,3");
    Pokemon snorlax = new Pokemon("Snorlax", "4,2,3");
    Pokemon psyduck = new Pokemon("Psyduck", "0,8,3");
    Pokemon onyx = new Pokemon("Onyx", "2,1,1");
    Pokemon sobble = new Pokemon("Sobble", "0,4,3");
    bst.addPokemon(pikachu);
    bst.addPokemon(eevee);
    bst.addPokemon(snorlax);
    bst.addPokemon(psyduck);
    bst.addPokemon(onyx);
    bst.addPokemon(sobble);
    if (bst.getLeastPowerfulPokemon() != sobble)
      return false;
    return true;
  }

  /**
   * Checks for the correctness of PokemonTree.getMostPowerfulPokemon() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetMostPowerfulPokemon() {
    PokemonTree bst = new PokemonTree();
    Pokemon pikachu = new Pokemon("Pikachu", "1,2,3");
    Pokemon eevee = new Pokemon("Eevee", "2,2,3");
    Pokemon snorlax = new Pokemon("Snorlax", "4,2,3");
    Pokemon psyduck = new Pokemon("Psyduck", "0,8,3");
    Pokemon onyx = new Pokemon("Onyx", "2,1,1");
    Pokemon charizard = new Pokemon("Charizard", "6,8,8");
    bst.addPokemon(pikachu);
    bst.addPokemon(eevee);
    bst.addPokemon(snorlax);
    bst.addPokemon(psyduck);
    bst.addPokemon(onyx);
    bst.addPokemon(charizard);
    if (bst.getMostPowerfulPokemon() != charizard)
      return false;
    return true;
  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testAddPokemonToStringSize(): " + testAddPokemonToStringSize());
    System.out.println("testAddPokemonAndLookup(): " + testAddPokemonAndLookup());
    System.out.println("testHeight(): " + testHeight());
    System.out.println("testGetMostPowerfulPokemon: " + testGetMostPowerfulPokemon());
    System.out.println("testGetLeastPowerfulPokemon: " + testGetLeastPowerfulPokemon());
  }
}
