package io.taig.android.viewvalue.core.operation

import android.view.View
import io.taig.android.viewvalue.core.{ Attribute, Extraction, Injection }

final class value[V <: View]( view: V ) {
    def value: Builder = new Builder

    class Builder {
        def apply[T]( implicit e: Extraction.Value[V, T] ): T = {
            e.extract( view )
        }
    }

    def value_=[T]( value: T )( implicit i: Injection.Value[V, T] ): Unit = {
        i.inject( view, value )
    }
}