package com.machinomy.crdt.state

import cats.kernel.Eq
import org.scalatest.FunSuite
import cats.syntax.all._

class GSetSuite extends FunSuite {
  val eq = implicitly[Eq[GSet[Int]]]

  test("Just created GSet is empty") {
    val gSet = GSet[Int]()
    assert(gSet.value.isEmpty)
  }

  test("GSet calculates value") {
    val a = GSet[Int]()
    val b = a + 3
    val c = b + 1
    val d = c + 3
    assert(d.value === Set(1, 3))
  }

  test("GSets can be merged") {
    val a = GSet[Int](Set(1, 2, 3))
    val b = GSet[Int](Set(2, 3, 4))
    val result = a |+| b
    assert(result.value === Set(1, 2, 3, 4))
  }

  test("equality") {
    val a = GSet[Int]()
    val b = GSet[Int]()
    assert(eq.eqv(a, b))

    val a1 = a + 1
    assert(a1 !== b)

    val b1 = b + 1
    assert(eq.eqv(a1, b1))
  }
}
