package io.taig.android.viewvalue.base

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.{ CompoundButton, ImageView, RadioGroup, TextView }
import io.taig.android.viewvalue.core.implicits._
import io.taig.android.viewvalue.core.Injection._
import io.taig.android.viewvalue.core.{ Attribute, Injection }

trait injection {
    implicit val injectionErrorTextViewOptionCharSequence: Error[TextView, Option[CharSequence]] = {
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
}

object injection extends injection