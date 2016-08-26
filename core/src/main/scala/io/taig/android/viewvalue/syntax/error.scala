package io.taig.android.viewvalue.syntax

import android.view.View
import io.taig.android.viewvalue.ops

import scala.language.implicitConversions

trait error {
    implicit def viewErrorSyntax[V <: View]( view: V ): ops.error[V] = {
        new ops.error( view )
    }
}

object error extends error