package io.taig.android.viewvalue

import android.view.View
import android.widget.{ CompoundButton, RadioGroup, TextView }

trait Extraction[-V, +T] {
    def extract( view: V ): T

    def map[U]( f: T ⇒ U ): Extraction[V, U] = Extraction.instance( view ⇒ f( extract( view ) ) )

    def contramap[W <: View]( f: W ⇒ V ): Extraction[W, T] = Extraction.instance( view ⇒ extract( f( view ) ) )
}

object Extraction {
    def apply[V, T]( implicit e: Extraction[V, T] ): Extraction[V, T] = e

    def instance[V, T]( f: V ⇒ T ): Extraction[V, T] = new Extraction[V, T] {
        override def extract( view: V ): T = f( view )
    }

    implicit val `Extraction[CompoundButton, Boolean]`: Extraction[CompoundButton, Boolean] = instance( _.isChecked )

    implicit val `Extraction[RadioGroup, Int]`: Extraction[RadioGroup, Int] = instance( _.getCheckedRadioButtonId )

    implicit val `Extraction[RadioGroup, Option[Int]]`: Extraction[RadioGroup, Option[Int]] = {
        Extraction[RadioGroup, Int].map {
            case -1 ⇒ None
            case id ⇒ Some( id )
        }
    }

    implicit val `Extraction[TextView, String]`: Extraction[TextView, String] = instance( _.getText.toString )

    implicit val `Extraction[TextView, Option[String]]`: Extraction[TextView, Option[String]] = {
        Extraction[TextView, String].map {
            case text if text.length == 0 ⇒ None
            case text                     ⇒ Some( text.toString )
        }
    }
}