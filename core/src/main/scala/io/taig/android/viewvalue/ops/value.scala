package io.taig.android.viewvalue.ops

import android.view.View
import io.taig.android.viewvalue.{ Injection, Extraction }

final class value[V <: View]( view: V ) {
    def value: Builder = new Builder

    class Builder {
        def apply[T]( implicit e: Extraction[V, T] ): T = e.extract( view )
    }

    def value_=[T]( value: T )( implicit i: Injection[V, T] ): Unit = i.inject( view, value )
}