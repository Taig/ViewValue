package io.taig.android.viewvalue

import android.graphics.drawable.Drawable
import android.widget.{ CompoundButton, ImageView, RadioGroup, TextView }
import io.taig.android.viewvalue.functional.{ Contramap, Map }
import io.taig.android.viewvalue.syntax.map._

import scala.language.reflectiveCalls

trait Extraction[A <: Attribute, -V, +T] {
    def extract( view: V ): T
}

object Extraction {
    implicit def contramapExtraction[A <: Attribute, T] = new Contramap[( { type λ[α] = Extraction[A, α, T] } )#λ] {
        override def contramap[L, U]( fa: Extraction[A, L, T] )( f: U ⇒ L ): Extraction[A, U, T] = {
            instance( view ⇒ fa.extract( f( view ) ) )
        }
    }

    implicit def mapExtraction[A <: Attribute, V] = new Map[( { type λ[α] = Extraction[A, V, α] } )#λ] {
        override def map[L, U]( fa: Extraction[A, V, L] )( f: L ⇒ U ): Extraction[A, V, U] = {
            instance( view ⇒ f( fa.extract( view ) ) )
        }
    }

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