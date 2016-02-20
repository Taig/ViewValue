package io.taig.android.viewvalue.ops

import android.view.View
import io.taig.android.viewvalue.{ Attribute, Extraction, Injection }

final class error[V <: View]( view: V ) {
    def error( implicit e: Extraction[Attribute.Error, V, Option[String]] ): Option[String] = {
        e.extract( view )
    }

    def error_=( value: Option[CharSequence] )( implicit i: Injection[Attribute.Error, V, Option[CharSequence]] ): Unit = {
        i.inject( view, value )
    }

    def error_=( value: CharSequence )( implicit i: Injection[Attribute.Error, V, Option[CharSequence]] ): Unit = {
        error = Option( value )
    }
}