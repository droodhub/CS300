public class ComparisonMethods {
  //////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
  //
  // Title: P03 Basic Benchmarking
  // Course: CS 300 Fall 2020
  //
  // Author: Andrew McAvoy
  // Email: apmcavoy@wisc.edu
  // Lecturer: Mouna Kacem
  //
  ///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
  //
  // Persons: Rojin Sangsari helped me figure out my try/catch structures
  // Online Sources: None
  //
  ///////////////////////////////////////////////////////////////////////////////
  /**
   * method to calculuate the sum of integers through brute force, influenced by the size of input n
   * 
   * @param n value to calculate the summation of
   * @return the sum of all integers less than or equal to n
   */
  public static long bruteForce(long n) {
    long sum = 0;
    for (int i = 0; i <= n; i++)// add every value of i together
      sum += i;
    return sum;
  }

  /**
   * method intended to calculate the sum of integers at a constant speed regardless of size n
   * 
   * @param n variable to calculate the summation of
   * @return the sum of all integers less than or equal to n
   */
  public static long constantTime(long n) {
    long oneSum = n * (long) (n + 1); // 2 calculation statements because long casting wasn't
                                      // working properly for one sum statement
    long finSum = oneSum / (long) 2; //triangle = (base*height)/2
    return finSum;
  }
}
