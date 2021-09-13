package funsets

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite extends munit.FunSuite:

  import FunSets.*

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets:
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)

  /**
   * This test is currently disabled (by using @Ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remove the
   * .ignore annotation.
   */
  test("singleton sets contain one, two, three and not each other") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets:
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "s1 contains 1")
      assert(contains(s2, 2), "s2 contains 2")
      assert(contains(s3, 3), "s3 contains 3")
      assert(!contains(s1, 2), "s1 does not contain 2")
      assert(!contains(s2, 3), "s2 does not contain 3")
      assert(!contains(s3, 1), "s3 does not contain 1")
  }

  test("union contains all elements of each set") {
    new TestSets:
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
  }

  test("intersect contains intersection of both sets") {
    new TestSets:
      val greaterThanOneSet = (x: Int) => x > 1
      val intersectGreaterThanOneAnds1 = intersect(s1, greaterThanOneSet)
      val intersectGreaterThanOneAnds2 = intersect(s2, greaterThanOneSet)
      assert(!contains(intersectGreaterThanOneAnds1, 1), "intersect of x > 1 and x = 1 does not have 1")
      assert(contains(intersectGreaterThanOneAnds2, 2), "intersect of x > 1 and x = 2 does have 2")
      assert(!contains(intersectGreaterThanOneAnds2, 3), "intersect of x > 1 and x = 2 does not have 3")
  }

  test("diff contains diff of sets") {
    new TestSets:
      val s = diff(s1, s2)
      assert(contains(s, 1), "diff contains 1")
      assert(!contains(s, 2), "diff does not contain 2")
  }

  test("filter selects set matching function") {
    new TestSets:
      val greaterThanOne = (x: Int) => x > 1
      val filterGreaterThanOneAnds1 = filter(s1, greaterThanOne)
      val filterGreaterThanOneAnds2 = filter(s2, greaterThanOne)
      assert(!contains(filterGreaterThanOneAnds1, 1), "filter of x > 1 and x = 1 does not have 1")
      assert(contains(filterGreaterThanOneAnds2, 2), "filter of x > 1 and x = 2 does have 2")
      assert(!contains(filterGreaterThanOneAnds2, 3), "filter of x > 1 and x = 2 does not have 3")
  }

  test("forall tests") {
    new TestSets:
      assert(forall(s1, (x: Int) => x > 0), "all members of s1 are positive")
      assert(!forall(s1, (x: Int) => x < 0), "not all members of s1 are negative")
  }

  test("exist tests") {
    new TestSets:
      assert(exists(s1, (x: Int) => x > 0), "at least one member of s1 is positive")
      assert(!exists(s1, (x: Int) => x < 0), "no members of s1 are negative")
  }

  test("map tests") {
    new TestSets:
      val s = map(s2, (x: Int) => x * x)
      assert(!contains(s, 2), "map s2 => s2**2 does not contain 2")
      assert(contains(s, 4), "map s2 => s2**2 contains 4")
  }



  import scala.concurrent.duration.*
  override val munitTimeout = 10.seconds
