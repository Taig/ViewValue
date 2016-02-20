package io.taig.android.viewvalue.design

import android.support.design.widget.TextInputLayout
import android.widget.TextView
import io.taig.android.viewvalue.syntax.contramap._
import io.taig.android.viewvalue.{ Attribute, Injection }

trait injection {
    implicit val injectionErrorTextInputLayoutCharSequence: Injection[Attribute.Error, TextInputLayout, CharSequence] = {
        Injection.instance( _.setError( _ ) )
    }

    implicit val injectionErrorTextInputLayoutOptionCharSequence: Injection[Attribute.Error, TextInputLayout, Option[CharSequence]] = {
        Injection[Attribute.Error, TextInputLayout, CharSequence].contramapR( ( _, error ) ⇒ error.orNull )
    }

    implicit val injectionErrorTextInputLayoutResource: Injection[Attribute.Error, TextInputLayout, Int] = {
        Injection.instance( ( view, resource ) ⇒ view.setError( view.getContext.getString( resource ) ) )
    }

    implicit val injectionErrorTextViewCharSequenceParent: Injection[Attribute.Error, TextView, CharSequence] = {
        Injection.instance { ( textView, error ) ⇒
            textView.getParent match {
                case textInputLayout: TextInputLayout ⇒
                    injectionErrorTextInputLayoutCharSequence.inject( textInputLayout, error )
                case _ ⇒
                    Injection.injectionErrorTextViewCharSequence.inject( textView, error )
            }
        }
    }

    implicit val injectionValueTextInputLayoutCharSequence: Injection[Attribute.Value, TextInputLayout, CharSequence] = {
        Injection[Attribute.Value, TextView, CharSequence].contramapL { ( textInputLayout, _ ) ⇒
            textInputLayout.getEditText
        }
    }

    implicit val injectionValueTextInputLayoutResource: Injection[Attribute.Value, TextInputLayout, Int] = {
        Injection[Attribute.Value, TextInputLayout, CharSequence].contramapR( _.getContext.getString( _ ) )
    }

    implicit val injectionValueTextInputLayoutOptionCharSequence: Injection[Attribute.Value, TextInputLayout, Option[CharSequence]] = {
        Injection[Attribute.Value, TextView, Option[CharSequence]].contramapL( ( textView, _ ) ⇒ textView.getEditText )
    }
}

object injection extends injection