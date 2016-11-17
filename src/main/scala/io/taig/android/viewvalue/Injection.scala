package io.taig.android.viewvalue

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.design.widget.TextInputLayout
import android.view.View
import android.widget.{ CompoundButton, ImageView, RadioGroup, TextView }
import io.taig.android.viewvalue.Injection._
import io.taig.android.viewvalue.functional.{ ContramapL, ContramapR }
import io.taig.android.viewvalue.syntax.contramap._

import scala.language.reflectiveCalls

trait Injection[A <: Attribute, -V, -T] {
    def inject( view: V, value: T ): Unit
}

object Injection extends Injection0 {
    type Error[V, T] = Injection[Attribute.Error, V, T]

    type Value[V, T] = Injection[Attribute.Value, V, T]

    implicit def contramapLInjection[A <: Attribute] = {
        new ContramapL[( { type λ[α, β] = Injection[A, α, β] } )#λ] {
            override def contramapL[L, R, T]( fa: Injection[A, L, R] )( f: ( T, R ) ⇒ L ): Injection[A, T, R] = {
                instance { ( view, value ) ⇒ fa.inject( f( view, value ), value ) }
            }
        }
    }

    implicit def contramapRInjection[A <: Attribute] = {
        new ContramapR[( { type λ[α, β] = Injection[A, α, β] } )#λ] {
            override def contramapL[L, R, T]( fa: Injection[A, L, R] )( f: ( L, T ) ⇒ R ): Injection[A, L, T] = {
                instance { ( view, value ) ⇒ fa.inject( view, f( view, value ) ) }
            }
        }
    }

    @inline
    def apply[A <: Attribute, V, T](
        implicit
        i: Injection[A, V, T]
    ): Injection[A, V, T] = i

    def instance[A <: Attribute, V, T]( f: ( V, T ) ⇒ Unit ): Injection[A, V, T] = {
        new Injection[A, V, T] {
            override def inject( view: V, value: T ): Unit = f( view, value )
        }
    }

    val injectionErrorTextViewOptionCharSequence: Error[TextView, Option[CharSequence]] = {
        instance( ( view, error ) ⇒ view.setError( error.orNull ) )
    }

    implicit val injectionValueCompoundButtonBoolean: Value[CompoundButton, Boolean] = {
        instance( _.setChecked( _ ) )
    }

    implicit val injectionValueImageViewBitmap: Value[ImageView, Bitmap] = {
        instance( _.setImageBitmap( _ ) )
    }

    implicit val injectionValueImageViewDrawable: Value[ImageView, Drawable] = {
        instance( _.setImageDrawable( _ ) )
    }

    implicit val injectionValueImageViewResource: Value[ImageView, Int] = {
        instance( _.setImageResource( _ ) )
    }

    implicit val injectionValueImageViewUri: Value[ImageView, Uri] = {
        instance( _.setImageURI( _ ) )
    }

    implicit val injectionValueRadioGroupInt: Value[RadioGroup, Int] = {
        instance( _.check( _ ) )
    }

    implicit val injectionValueRadioGroupOptionInt: Value[RadioGroup, Option[Int]] = {
        instance {
            case ( view, Some( value ) ) ⇒ view.check( value )
            case ( view, None )          ⇒ view.clearCheck()
        }
    }

    implicit val injectionValueTextViewCharSequence: Value[TextView, CharSequence] = {
        instance( _.setText( _ ) )
    }

    implicit val injectionValueTextViewOptionCharSequence: Value[TextView, Option[CharSequence]] = {
        Injection[Attribute.Value, TextView, CharSequence]
            .contramapR( ( _, error ) ⇒ error.orNull )
    }

    implicit val injectionValueTextViewResource: Value[TextView, Int] = {
        instance( _.setText( _ ) )
    }

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

trait Injection0 {
    implicit def injectionErrorViewValue[V, T](
        implicit
        i: Error[V, Option[T]]
    ): Error[V, T] = i.contramapR( ( _, error ) ⇒ Option( error ) )

    implicit def injectionErrorViewResource[V <: View](
        implicit
        i: Error[V, String]
    ): Error[V, Int] = i.contramapR( _.getContext.getString( _ ) )
}