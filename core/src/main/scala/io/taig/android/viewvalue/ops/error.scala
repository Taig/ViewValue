package io.taig.android.viewvalue.ops

import android.view.View
import io.taig.android.viewvalue.{ Attribute, Extraction, Injection }

final class error[V <: View]( view: V ) {
    def error( implicit e: Extraction[Attribute.Error, V, Option[String]] ): Option[String] = {
        e.extract( view )
    }

    def error_=[T]( value: T )( implicit i: Injection[Attribute.Error, V, T] ): Unit = {
        i.inject( view, value )
    }
}