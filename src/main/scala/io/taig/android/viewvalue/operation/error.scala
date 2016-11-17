package io.taig.android.viewvalue.operation

import android.view.View
import io.taig.android.viewvalue.{ Extraction, Injection }

final class error[V <: View]( view: V ) {
    def error( implicit e: Extraction.Error[V] ): Option[String] = {
        e.extract( view )
    }

    def error_=[T]( value: T )( implicit i: Injection.Error[V, T] ): Unit = {
        i.inject( view, value )
    }
}