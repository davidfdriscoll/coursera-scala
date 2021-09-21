package patmat

class HuffmanSuite extends munit.FunSuite:
  import Huffman.*

  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
    val t3 = Fork(Leaf('a',8),Fork(Fork(Fork(Leaf('c',1),Leaf('d',1),List('c', 'd'),2),Fork(Leaf('e',1),Leaf('f',1),List('e', 'f'),2),List('c', 'd', 'e', 'f'),4),Fork(Fork(Leaf('g',1),Leaf('h',1),List('g', 'h'),2),Leaf('b',3),List('g', 'h', 'b'),5),List('c', 'd', 'e', 'f', 'g', 'h', 'b'),9),List('a', 'c', 'd', 'e', 'f', 'g', 'h', 'b'),17)
  }

  test("weight of a larger tree (10pts)") {
    new TestTrees:
      assertEquals(weight(t1), 5)
  }


  test("chars of a larger tree (10pts)") {
    new TestTrees:
      assertEquals(chars(t2), List('a','b','d'))
  }

  test("string2chars hello world") {
    assertEquals(
      string2Chars("hello, world"), 
      List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd')
    )
  }

  test("times helloworld") {
    assertEquals(
      times(string2Chars("helloworld")), 
      List(('h',1), ('e',1), ('l',3), ('o',2), ('w',1), ('r',1), ('d',1))
    )
  }


  test("make ordered leaf list for some frequency table (15pts)") {
    assertEquals(
      makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))), 
      List(Leaf('e',1), Leaf('t',2), Leaf('x',3))
    )
  }

  test("test singleton returns true for list of one CodeTree") {
    assertEquals(singleton(List(Leaf('c', 1))), true)
  }

  test("test singleton returns false for list of two CodeTrees") {
    assertEquals(singleton(List(Leaf('c', 1), Leaf('d', 1))), false)
  }


  test("combine of some leaf list (15pts)") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assertEquals(combine(leaflist), List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("convert tree") {
    new TestTrees:
      assertEquals(
        convert(t3),
        List(('a',List(0)), ('c',List(1, 0, 0, 0)), ('d',List(1, 0, 0, 1)), ('e',List(1, 0, 1, 0)), ('f',List(1, 0, 1, 1)), ('g',List(1, 1, 0, 0)), ('h',List(1, 1, 0, 1)), ('b',List(1, 1, 1)))
      )
  }

  test("decode and encode a very short text should be identity (10pts)") {
    new TestTrees:
      assertEquals(decode(t1, encode(t1)("ab".toList)), "ab".toList)
  }

  test("encode cab on tree") {
    new TestTrees:
      assertEquals(
        encode(t3)(string2Chars("cab")),
        List(1,0,0,0,0,1,1,1)
      )
  }

  test("decode and quickEncode a very short text should be identity (10pts)") {
    new TestTrees:
      assertEquals(decode(t1, quickEncode(t1)("ab".toList)), "ab".toList)
  }

  test("quickEncode cab on tree") {
    new TestTrees:
      assertEquals(
        quickEncode(t3)(string2Chars("cab")),
        List(1,0,0,0,0,1,1,1)
      )
  }


  import scala.concurrent.duration.*
  override val munitTimeout = 10.seconds
