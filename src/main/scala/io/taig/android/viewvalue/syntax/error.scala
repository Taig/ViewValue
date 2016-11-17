package io.taig.android.viewvalue.syntax

import android.view.View
import io.taig.android.viewvalue._

import scala.language.implicitConversions

trait error {
    implicit def viewErrorSyntax[V <: View]( view: V ): operation.error[V] = {
        new operation.error( view )
    }
}

object error extends error