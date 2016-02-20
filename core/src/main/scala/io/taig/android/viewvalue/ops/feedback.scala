package io.taig.android.viewvalue.ops

import android.view.View
import io.taig.android.viewvalue.{ Attribute, Extraction, Injection }

final class feedback[V <: View]( view: V ) {
    def feedback( implicit e: Extraction[Attribute.Feedback, V, Option[CharSequence]] ): Option[CharSequence] = {
        e.extract( view )
    }

    def feedback_=( value: Option[CharSequence] )( implicit i: Injection[Attribute.Feedback, V, Option[CharSequence]] ): Unit = {
        i.inject( view, value )
    }

    def feedback_=( value: CharSequence )( implicit i: Injection[Attribute.Feedback, V, Option[CharSequence]] ): Unit = {
        feedback = Option( value )
    }
}