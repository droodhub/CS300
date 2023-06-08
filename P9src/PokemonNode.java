//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P09 Pokemon Catalog
// Course:   CS 300 Fall 2020
//
// Author:   Andrew McAvoy
// Email:    apmcavoy@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  zybooks -- hleped with search + add algorithms in pokemontree
//
///////////////////////////////////////////////////////////////////////////////
public class PokemonNode {
private Pokemon data;
private PokemonNode leftChild;
private PokemonNode rightChild;
/**
 * @param data pokemon to set as the parent of this node
 */
public PokemonNode(Pokemon data) {
  this.data = data;
  leftChild = null;
  rightChild = null;
}
/**
 * @return pokemon stored in the leftChild data field
 */
public PokemonNode getLeftChild() {
  return leftChild;
}
/**
 * @return pokemon stored in the rightChild data field
 */
public PokemonNode getRightChild() {
  return rightChild;
}
/**
 * @return the pokemon stored in the data field of this node
 */
public Pokemon getPokemon() {
  return data;
}
/**
 * @param left new pokemon to set as the left child
 */
public void setLeftChild(PokemonNode left) {
  leftChild = left;
}
/**
 * @param right new pokemon to set as the right child
 */
public void setRightChild(PokemonNode right) {
  rightChild = right;
}
}
