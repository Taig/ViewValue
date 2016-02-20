package io.taig.android.viewvalue.design

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.widget.TextView
import io.taig.android.viewvalue.{ Attribute, Injection }

trait injection {
    implicit val injectionFeedbackTextInputLayout: Injection[Attribute.Feedback, TextInputLayout, Option[CharSequence]] = {
        Injection.instance( ( textInputLayout, feedback ) ⇒ textInputLayout.setError( feedback.orNull ) )
    }

    implicit val injectionFeedbackTextViewParent: Injection[Attribute.Feedback, TextView, Option[CharSequence]] = {
        Injection.instance { ( textView, feedback ) ⇒
            textView.getParent match {
                case textInputLayout: TextInputLayout ⇒
                    injectionFeedbackTextInputLayout.inject( textInputLayout, feedback )
                case _ ⇒
                    Injection.injectionFeedbackTextView.inject( textView, feedback )
            }
        }
    }

    implicit val injectionValueTextInputLayoutCharSequence: Injection[Attribute.Value, TextInputLayout, CharSequence] = {
        Injection[Attribute.Value, TextView, CharSequence].contramapL( _.getEditText )
    }

    implicit def injectionValueTextInputLayoutResource( implicit context: Context ): Injection[Attribute.Value, TextInputLayout, Int] = {
        Injection[Attribute.Value, TextView, Int].contramapL( _.getEditText )
    }

    implicit val injectionValueTextInputLayoutOptionCharSequence: Injection[Attribute.Value, TextInputLayout, Option[CharSequence]] = {
        Injection[Attribute.Value, TextView, Option[CharSequence]].contramapL( _.getEditText )
    }
}

object injection extends injection