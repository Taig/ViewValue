package io.taig.android.viewvalue.syntax

import android.view.View
import io.taig.android.viewvalue.ops

import scala.language.implicitConversions

trait feedback {
    implicit def viewFeedbackSyntax[V <: View]( view: V ): ops.feedback[V] = new ops.feedback( view )
}

object feedback extends feedback
