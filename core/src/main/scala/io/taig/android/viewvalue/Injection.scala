package io.taig.android.viewvalue

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.{ ImageView, CompoundButton, RadioGroup, TextView }

trait Injection[A <: Attribute, -V, -T] {
    def inject( view: V, value: T ): Unit

    def contramapL[W <: View]( f: W ⇒ V ): Injection[A, W, T] = Injection.instance { ( view, value ) ⇒
        inject( f( view ), value )
    }

    def contramapR[U]( f: U ⇒ T ): Injection[A, V, U] = Injection.instance { ( view, value ) ⇒
        inject( view, f( value ) )
    }
}

object Injection {
    def apply[A <: Attribute, V, T]( implicit i: Injection[A, V, T] ): Injection[A, V, T] = i

    def instance[A <: Attribute, V, T]( f: ( V, T ) ⇒ Unit ): Injection[A, V, T] = new Injection[A, V, T] {
        override def inject( view: V, value: T ): Unit = f( view, value )
    }

    implicit val injectionErrorTextView: Injection[Attribute.Error, TextView, Option[CharSequence]] = {
        instance( ( textView, error ) ⇒ textView.setError( error.orNull ) )
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

    implicit val injectionValueTextViewResource: Injection[Attribute.Value, TextView, Int] = {
        instance( _.setText( _ ) )
    }

    implicit val injectionValueTextViewOptionCharSequence: Injection[Attribute.Value, TextView, Option[CharSequence]] = {
        Injection[Attribute.Value, TextView, CharSequence].contramapR( _.orNull )
    }
}