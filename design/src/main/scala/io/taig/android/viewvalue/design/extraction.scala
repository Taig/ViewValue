package io.taig.android.viewvalue.design

import android.support.design.widget.TextInputLayout
import android.widget.TextView
import io.taig.android.viewvalue.{ Attribute, Extraction }

trait extraction {
    implicit val extractionFeedbackTextInputLayout: Extraction[Attribute.Feedback, TextInputLayout, Option[String]] = {
        Extraction.instance[Attribute.Feedback, TextInputLayout, CharSequence]( _.getError )
            .map( Option( _ ).map( _.toString ) )
    }

    implicit val extractionFeedbackTextViewParent: Extraction[Attribute.Feedback, TextView, Option[String]] = {
        Extraction.instance { textView ⇒
            textView.getParent match {
                case textInputLayout: TextInputLayout ⇒ extractionFeedbackTextInputLayout.extract( textInputLayout )
                case _                                ⇒ Extraction.extractionFeedbackTextView.extract( textView )
            }
        }
    }

    implicit val extractionValueTextInputLayoutString: Extraction[Attribute.Value, TextInputLayout, String] = {
        Extraction[Attribute.Value, TextView, String].contramap( _.getEditText )
    }

    implicit val extractionValueTextInputLayoutOptionString: Extraction[Attribute.Value, TextInputLayout, Option[String]] = {
        Extraction[Attribute.Value, TextView, Option[String]].contramap( _.getEditText )
    }
}

object extraction extends extraction