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
        Extraction.instance { view ⇒
            findParentTextInputLayout( view ) match {
                case Some( view ) ⇒
                    extractionErrorTextInputLayout.extract( view )
                case None ⇒
                    extractionErrorTextView.extract( view )
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