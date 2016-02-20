package io.taig.android.viewvalue

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.{ ImageView, CompoundButton, RadioGroup, TextView }

trait Extraction[A <: Attribute, -V, +T] {
    def extract( view: V ): T

    def map[U]( f: T ⇒ U ): Extraction[A, V, U] = Extraction.instance( view ⇒ f( extract( view ) ) )

    def contramap[W <: View]( f: W ⇒ V ): Extraction[A, W, T] = Extraction.instance( view ⇒ extract( f( view ) ) )
}

object Extraction {
    def apply[A <: Attribute, V, T]( implicit e: Extraction[A, V, T] ): Extraction[A, V, T] = e

    def instance[A <: Attribute, V, T]( f: V ⇒ T ): Extraction[A, V, T] = new Extraction[A, V, T] {
        override def extract( view: V ): T = f( view )
    }

    implicit val extractionErrorTextView: Extraction[Attribute.Error, TextView, Option[String]] = {
        instance[Attribute.Error, TextView, CharSequence]( _.getError ).map( Option( _ ).map( _.toString ) )
    }

    implicit val extractionValueCompoundButtonBoolean: Extraction[Attribute.Value, CompoundButton, Boolean] = {
        instance( _.isChecked )
    }

    implicit val extractionValueImageViewDrawable: Extraction[Attribute.Value, ImageView, Drawable] = {
        instance( _.getDrawable )
    }

    implicit val extractionValueRadioGroupInt: Extraction[Attribute.Value, RadioGroup, Int] = {
        instance( _.getCheckedRadioButtonId )
    }

    implicit val extractionValueRadioGroupOptionInt: Extraction[Attribute.Value, RadioGroup, Option[Int]] = {
        Extraction[Attribute.Value, RadioGroup, Int].map {
            case -1 ⇒ None
            case id ⇒ Some( id )
        }
    }

    implicit val extractionValueTextViewString: Extraction[Attribute.Value, TextView, String] = {
        instance( _.getText.toString )
    }

    implicit val extractionValueTextViewOptionString: Extraction[Attribute.Value, TextView, Option[String]] = {
        Extraction[Attribute.Value, TextView, String].map {
            case text if text.length == 0 ⇒ None
            case text                     ⇒ Some( text.toString )
        }
    }
}