package io.taig.android.viewvalue.design

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.widget.TextView
import io.taig.android.viewvalue.{ Attribute, Injection }

trait injection {
    implicit val injectionErrorTextInputLayout: Injection[Attribute.Error, TextInputLayout, Option[CharSequence]] = {
        Injection.instance( ( textInputLayout, error ) ⇒ textInputLayout.setError( error.orNull ) )
    }

    implicit val injectionErrorTextViewParent: Injection[Attribute.Error, TextView, Option[CharSequence]] = {
        Injection.instance { ( textView, error ) ⇒
            textView.getParent match {
                case textInputLayout: TextInputLayout ⇒
                    injectionErrorTextInputLayout.inject( textInputLayout, error )
                case _ ⇒
                    Injection.injectionErrorTextView.inject( textView, error )
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