package io.taig.android.viewvalue.design

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.widget.TextView
import io.taig.android.viewvalue.{ Attribute, Injection }

trait injection {
    implicit val injectionValueTextInputLayoutCharSequence: Injection[Attribute.Value, TextInputLayout, CharSequence] = {
        Injection[Attribute.Value, TextView, CharSequence].contramapL( _.getEditText )
    }

    implicit def injectionValueTextInputLayoutResource( implicit context: Context ): Injection[Attribute.Value, TextInputLayout, Int] = {
        Injection[Attribute.Value, TextView, Int].contramapL( _.getEditText )
    }

    implicit val injectionValueTextInputLayoutOptionCharSequence: Injection[Attribute.Value, TextInputLayout, Option[CharSequence]] = {
        Injection[Attribute.Value, TextView, Option[CharSequence]].contramapL( _.getEditText )
    }
}

object injection extends injection