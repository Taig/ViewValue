package io.taig.android.viewvalue.syntax

import android.view.View
import io.taig.android.viewvalue.ops

import scala.language.implicitConversions

trait value {
    implicit def viewValueSyntax[V <: View]( view: V ): ops.value[V] = {
        new ops.value( view )
    }
}

object value extends value