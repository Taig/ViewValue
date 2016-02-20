package io.taig.android.viewvalue

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.{ CompoundButton, ImageView, RadioGroup, TextView }
import io.taig.android.viewvalue.functional.{ ContramapL, ContramapR }
import io.taig.android.viewvalue.syntax.contramap._

import scala.language.reflectiveCalls

trait Injection[A <: Attribute, -V, -T] {
    def inject( view: V, value: T ): Unit
}

object Injection {
    implicit def contramapLInjection[A <: Attribute] = new ContramapL[( { type λ[α, β] = Injection[A, α, β] } )#λ] {
        override def contramapL[L, R, T]( fa: Injection[A, L, R] )( f: ( T, R ) ⇒ L ): Injection[A, T, R] = {
            instance { ( view, value ) ⇒ fa.inject( f( view, value ), value ) }
        }
    }

    implicit def contramapRInjection[A <: Attribute] = new ContramapR[( { type λ[α, β] = Injection[A, α, β] } )#λ] {
        override def contramapL[L, R, T]( fa: Injection[A, L, R] )( f: ( L, T ) ⇒ R ): Injection[A, L, T] = {
            instance { ( view, value ) ⇒ fa.inject( view, f( view, value ) ) }
        }
    }

    def apply[A <: Attribute, V, T]( implicit i: Injection[A, V, T] ): Injection[A, V, T] = i

    def instance[A <: Attribute, V, T]( f: ( V, T ) ⇒ Unit ): Injection[A, V, T] = new Injection[A, V, T] {
        override def inject( view: V, value: T ): Unit = f( view, value )
    }

    implicit val injectionErrorTextViewCharSequence: Injection[Attribute.Error, TextView, CharSequence] = {
        instance( _.setError( _ ) )
    }

    implicit val injectionErrorTextViewOptionCharSequence: Injection[Attribute.Error, TextView, Option[CharSequence]] = {
        Injection[Attribute.Error, TextView, CharSequence].contramapR( ( _, error ) ⇒ error.orNull )
    }

    implicit val injectionErrorTextViewResource: Injection[Attribute.Error, TextView, Int] = {
        Injection[Attribute.Error, TextView, CharSequence].contramapR( _.getContext.getString( _ ) )
    }

    implicit val injectionValueCompoundButtonBoolean: Injection[Attribute.Value, CompoundButton, Boolean] = {
        instance( _.setChecked( _ ) )
    }

    implicit val injectionValueImageViewBitmap: Injection[Attribute.Value, ImageView, Bitmap] = {
        instance( _.setImageBitmap( _ ) )
    }

    implicit val injectionValueImageViewDrawable: Injection[Attribute.Value, ImageView, Drawable] = {
        instance( _.setImageDrawable( _ ) )
    }

    implicit val injectionValueImageViewResource: Injection[Attribute.Value, ImageView, Int] = {
        instance( _.setImageResource( _ ) )
    }

    implicit val injectionValueImageViewUri: Injection[Attribute.Value, ImageView, Uri] = {
        instance( _.setImageURI( _ ) )
    }

    implicit val injectionValueRadioGroupInt: Injection[Attribute.Value, RadioGroup, Int] = {
        instance( _.check( _ ) )
    }

    implicit val injectionValueRadioGroupOptionInt: Injection[Attribute.Value, RadioGroup, Option[Int]] = instance {
        case ( view, Some( value ) ) ⇒ view.check( value )
        case ( view, None )          ⇒ view.clearCheck()
    }

    implicit val injectionValueTextViewCharSequence: Injection[Attribute.Value, TextView, CharSequence] = {
        instance( _.setText( _ ) )
    }

    implicit val injectionValueTextViewOptionCharSequence: Injection[Attribute.Value, TextView, Option[CharSequence]] = {
        Injection[Attribute.Value, TextView, CharSequence].contramapR( ( _, error ) ⇒ error.orNull )
    }

    implicit val injectionValueTextViewResource: Injection[Attribute.Value, TextView, Int] = {
        instance( _.setText( _ ) )
    }
}