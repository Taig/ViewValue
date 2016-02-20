package io.taig.android.viewvalue.ops

import android.view.View
import io.taig.android.viewvalue.{ Attribute, Extraction, Injection }

final class value[V <: View]( view: V ) {
    def value: Builder = new Builder

    class Builder {
        def apply[T]( implicit e: Extraction[Attribute.Value, V, T] ): T = e.extract( view )
    }

    def value_=[T]( value: T )( implicit i: Injection[Attribute.Value, V, T] ): Unit = i.inject( view, value )
}