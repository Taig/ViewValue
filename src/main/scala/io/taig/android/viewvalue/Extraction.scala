package io.taig.android.viewvalue

import android.graphics.drawable.Drawable
import android.support.design.widget.TextInputLayout
import android.widget.{ CompoundButton, ImageView, RadioGroup, TextView }
import io.taig.android.viewvalue.functional._
import io.taig.android.viewvalue.syntax.all._

import scala.language.reflectiveCalls

trait Extraction[A <: Attribute, -V, +T] {
    def extract( view: V ): T
}

object Extraction {
    type Error[V] = Extraction[Attribute.Error, V, Option[String]]

    type Value[V, T] = Extraction[Attribute.Value, V, T]

    implicit def contramapExtraction[A <: Attribute, T] = {
        new Contramap[( { type λ[α] = Extraction[A, α, T] } )#λ] {
            override def contramap[L, U]( fa: Extraction[A, L, T] )( f: U ⇒ L ): Extraction[A, U, T] = {
                instance( view ⇒ fa.extract( f( view ) ) )
            }
        }
    }

    implicit def mapExtraction[A <: Attribute, V] = {
        new Map[( { type λ[α] = Extraction[A, V, α] } )#λ] {
            override def map[L, U]( fa: Extraction[A, V, L] )( f: L ⇒ U ): Extraction[A, V, U] = {
                instance( view ⇒ f( fa.extract( view ) ) )
            }
        }
    }

    @inline
    def apply[A <: Attribute, V, T](
        implicit
        e: Extraction[A, V, T]
    ): Extraction[A, V, T] = e

    def instance[A <: Attribute, V, T]( f: V ⇒ T ): Extraction[A, V, T] = {
        new Extraction[A, V, T] {
            override def extract( view: V ): T = f( view )
        }
    }

    val extractionErrorTextView: Error[TextView] = {
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
        Extraction[Attribute.Value, TextView, String]
            .contramap( _.getEditText )
    }

    implicit val extractionValueTextInputLayoutOptionString: Value[TextInputLayout, Option[String]] = {
        Extraction[Attribute.Value, TextView, Option[String]]
            .contramap( _.getEditText )
    }
}