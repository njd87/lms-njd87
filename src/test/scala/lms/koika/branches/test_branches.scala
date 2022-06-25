package lms
package branches

import scala.annotation.implicitNotFound

import scala.collection.{mutable,immutable}

import lms.core._
import lms.util.GraphUtil

import Backend._

object BranchFrontEnd extends Adapter {
    // array for storing "sub-graphs" with anticipated if-statements
    val ifBranches = new mutable.ArrayBuffer[Graph]

    // this should be the return branch, chagnes in findRes
    var workingBranch = Branch

    // go through all branches in ifBranches to see if outputs are the same
    def findRes() : Unit = {
        if (ifBranches.forall(_ == ifBranches.head.res)) {
            // something
        } else {
            // something
        }
    }

    override def IF(c: BOOL)(a: => INT)(b: => INT): INT = {
        val aBlock = g.reifyHere(a.x)
        val bBlock = g.reifyHere(b.x)
        // compute effect (aBlock || bBlock)
        val pure = aBlock.isPure && bBlock.isPure
        if (pure)
        INT(g.reflect("?",c.x,aBlock,bBlock))
        else
        INT(g.reflectEffectSummaryHere("?",c.x,aBlock,bBlock)(g.mergeEffKeys(aBlock, bBlock)))
    }

    override def program(body: => Block): Graph = {
        assert(g == null)
        g = mkGraphBuilder()
        try {
        val block = body
        Graph(g.globalDefs, block, g.globalDefsCache.toMap)
        } finally {g = null}
  }

}

object BranchBaseTypeLess extends BaseTypeLess {

}