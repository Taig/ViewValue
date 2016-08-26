package io.taig.android.viewvalue.design

import android.support.design.widget.TextInputLayout
import android.widget.TextView
import io.taig.android.viewvalue.syntax.contramap._
import io.taig.android.viewvalue.syntax.map._
import io.taig.android.viewvalue.{ Attribute, Extraction }
import io.taig.android.viewvalue.Extraction._

trait extraction {
    implicit val extractionErrorTextInputLayout: Error[TextInputLayout] = {
        Extraction.instance[Attribute.Error, TextInputLayout, CharSequence]( _.getError )
            .map( Option( _ ).map( _.toString ) )
    }

    implicit val extractionErrorTextViewParent: Error[TextView] = {
        Extraction.instance { textView ⇒
            textView.getParent match {
                case textInputLayout: TextInputLayout ⇒
                    extractionErrorTextInputLayout.extract( textInputLayout )
                case _ ⇒
                    extractionErrorTextView.extract( textView )
            }
        }
    }

    implicit val extractionValueTextInputLayoutString: Value[TextInputLayout, String] = {
        Extraction[Attribute.Value, TextView, String].contramap( _.getEditText )
    }

    implicit val extractionValueTextInputLayoutOptionString: Value[TextInputLayout, Option[String]] = {
        Extraction[Attribute.Value, TextView, Option[String]].contramap( _.getEditText )
    }
}

object extraction extends extraction