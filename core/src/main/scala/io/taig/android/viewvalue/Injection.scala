package io.taig.android.viewvalue

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.{ ImageView, CompoundButton, RadioGroup, TextView }

trait Injection[-V, -T] {
    def inject( view: V, value: T ): Unit

    def contramapL[W <: View]( f: W ⇒ V ): Injection[W, T] = Injection.instance { ( view, value ) ⇒
        inject( f( view ), value )
    }

    def contramapR[U]( f: U ⇒ T ): Injection[V, U] = Injection.instance { ( view, value ) ⇒
        inject( view, f( value ) )
    }
}

object Injection {
    def apply[V, T]( implicit i: Injection[V, T] ): Injection[V, T] = i

    def instance[V, T]( f: ( V, T ) ⇒ Unit ): Injection[V, T] = new Injection[V, T] {
        override def inject( view: V, value: T ): Unit = f( view, value )
    }

    implicit val `Injection[CompoundButton, Boolean]`: Injection[CompoundButton, Boolean] = {
        instance( _.setChecked( _ ) )
    }

    implicit val `Injection[ImageView, Bitmap]`: Injection[ImageView, Bitmap] = instance( _.setImageBitmap( _ ) )

    implicit val `Injection[ImageView, Drawable]`: Injection[ImageView, Drawable] = instance( _.setImageDrawable( _ ) )

    implicit val `Injection[ImageView, Resource]`: Injection[ImageView, Int] = instance( _.setImageResource( _ ) )

    implicit val `Injection[ImageView, Uri]`: Injection[ImageView, Uri] = instance( _.setImageURI( _ ) )

    implicit val `Injection[RadioGroup, Int]`: Injection[RadioGroup, Int] = instance( _.check( _ ) )

    implicit val `Injection[RadioGroup, Option[Int]]`: Injection[RadioGroup, Option[Int]] = instance {
        case ( view, Some( value ) ) ⇒ view.check( value )
        case ( view, None )          ⇒ view.clearCheck()
    }

    implicit val `Injection[TextView, CharSequence]`: Injection[TextView, CharSequence] = instance( _.setText( _ ) )

    implicit val `Injection[TextView, Resource]`: Injection[TextView, Int] = instance( _.setText( _ ) )

    implicit val `Injection[TextView, Option[CharSequence]]`: Injection[TextView, Option[CharSequence]] = {
        Injection[TextView, CharSequence].contramapR( _.orNull )
    }
}