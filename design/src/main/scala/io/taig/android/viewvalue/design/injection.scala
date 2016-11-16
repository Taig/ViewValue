package io.taig.android.viewvalue.design

import android.support.design.widget.TextInputLayout
import android.widget.TextView
import io.taig.android.viewvalue.base.injection._
import io.taig.android.viewvalue.core.syntax.contramap._
import io.taig.android.viewvalue.core.{ Attribute, Injection }
import io.taig.android.viewvalue.core.Injection._

trait injection {
    implicit val injectionErrorTextInputLayoutOptionCharSequence: Error[TextInputLayout, Option[CharSequence]] = {
        instance { ( view, error ) ⇒
            view.setErrorEnabled( error.isDefined )
            view.setError( error.orNull )
        }
    }

    implicit val injectionErrorTextViewOptionCharSequenceParent: Error[TextView, Option[CharSequence]] = {
        instance { ( view, error ) ⇒
            findParentTextInputLayout( view ) match {
                case Some( view ) ⇒
                    injectionErrorTextInputLayoutOptionCharSequence
                        .inject( view, error )
                case None ⇒
                    injectionErrorTextViewOptionCharSequence
                        .inject( view, error )
            }
        }
    }

    implicit val injectionValueTextInputLayoutCharSequence: Injection.Value[TextInputLayout, CharSequence] = {
        Injection[Attribute.Value, TextView, CharSequence]
            .contramapL { ( textInputLayout, _ ) ⇒
                textInputLayout.getEditText
            }
    }

    implicit val injectionValueTextInputLayoutResource: Injection.Value[TextInputLayout, Int] = {
        Injection[Attribute.Value, TextInputLayout, CharSequence]
            .contramapR( _.getContext.getString( _ ) )
    }

    implicit val injectionValueTextInputLayoutOptionCharSequence: Injection.Value[TextInputLayout, Option[CharSequence]] = {
        Injection[Attribute.Value, TextView, Option[CharSequence]]
            .contramapL( ( textView, _ ) ⇒ textView.getEditText )
    }
}

object injection extends injection