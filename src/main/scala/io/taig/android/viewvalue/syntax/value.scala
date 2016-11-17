package io.taig.android.viewvalue.syntax

import android.view.View
import io.taig.android.viewvalue._

import scala.language.implicitConversions

trait value {
    implicit def viewValueSyntax[V <: View]( view: V ): operation.value[V] = {
        new operation.value( view )
    }
}

object value extends value