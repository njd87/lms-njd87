package lms
package branches

import scala.annotation.implicitNotFound

import scala.collection.{mutable,immutable}

import lms.core._
import lms.util.GraphUtil

import Backend._

object BranchFrontEnd extends Adapter {


}

object BranchBaseTypeLess extends BaseTypeLess {
    override def IF(c: BOOL)(a: => TOP)(b: => TOP)(implicit __pos: SourceContext): TOP = {
        val aBlock = Adapter.g.reifyHere(a.x)
        val bBlock = Adapter.g.reifyHere(b.x)
        // compute effect (aBlock || bBlock)
        val pure = aBlock.isPure && bBlock.isPure
        val aManifest = Adapter.typeMap(aBlock.res)
        val bManifest = Adapter.typeMap(bBlock.res)
        // NOTE(feiw) hope to check the equality of the `aManifest` and `bManifest` but
        // that might not consider subtypes
        if (pure)
            TOP(Adapter.g.reflect("?",c.x,aBlock,bBlock), aManifest)
            Branch.ifBranches +=  // graphbuilder with all the history up to this point
            Branch.ifBranches +=  // graphbuilder with all the history up to this point
        else
            TOP(Adapter.g.reflectEffectSummaryHere("?",c.x,aBlock,bBlock)(Adapter.g.mergeEffKeys(aBlock, bBlock)), aManifest)
    }
}

object Branch {
    // array for storing "sub-graphs" with anticipated if-statements
    // TODO: need to find where I can make use of this in the frontend code
    val ifBranches = new mutable.ArrayBuffer[Graph]

    // array for keeping track of final outputs of each selection of viewed branches
    // TODO: Need to find a way to find out if an output is the final output of a given block
    val outs = new mutable.ArrayBuffer[Any]

    // go through all branches in ifBranches to see if outputs are the same
    def findRes() : Unit = {
        if (ifBranches.forall(_ == ifBranches.head.res)) {
            // something
        } else {
            // something
        }
    }
}
