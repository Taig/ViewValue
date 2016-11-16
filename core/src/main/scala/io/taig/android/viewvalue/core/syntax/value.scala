package io.taig.android.viewvalue.core.syntax

import android.view.View
import io.taig.android.viewvalue.core._

import scala.language.implicitConversions

trait value {
    implicit def viewValueSyntax[V <: View]( view: V ): operation.value[V] = {
        new operation.value( view )
    }
}

object value extends value