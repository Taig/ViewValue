package io.taig.android.viewvalue.base

import android.graphics.drawable.Drawable
import android.widget.{ CompoundButton, ImageView, RadioGroup, TextView }
import io.taig.android.viewvalue.core.Extraction._
import io.taig.android.viewvalue.core.syntax.map._
import io.taig.android.viewvalue.core.{ Attribute, Extraction }

trait extraction {
    implicit val extractionErrorTextView: Error[TextView] = {
        instance[Attribute.Error, TextView, CharSequence]( _.getError )
            .map( Option( _ ).map( _.toString ) )
    }

    implicit val extractionValueCompoundButtonBoolean: Value[CompoundButton, Boolean] = {
        instance( _.isChecked )
    }

    implicit val extractionValueImageViewDrawable: Value[ImageView, Drawable] = {
        instance( _.getDrawable )
    }

    implicit val extractionValueRadioGroupInt: Value[RadioGroup, Int] = {
        instance( _.getCheckedRadioButtonId )
    }

    implicit val extractionValueRadioGroupOptionInt: Value[RadioGroup, Option[Int]] = {
        Extraction[Attribute.Value, RadioGroup, Int].map {
            case -1 ⇒ None
            case id ⇒ Some( id )
        }
    }

    implicit val extractionValueTextViewString: Value[TextView, String] = {
        instance( _.getText.toString )
    }

    implicit val extractionValueTextViewOptionString: Value[TextView, Option[String]] = {
        Extraction[Attribute.Value, TextView, String].map {
            case text if text.length == 0 ⇒ None
            case text                     ⇒ Some( text.toString )
        }
    }
}

object extraction extends extraction