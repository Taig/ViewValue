package io.taig.android.viewvalue.design

import android.support.design.widget.TextInputLayout
import android.widget.TextView
import io.taig.android.viewvalue.Injection

trait injection {
    implicit val `Injection[TextInputLayout, CharSequence]`: Injection[TextInputLayout, CharSequence] = {
        Injection[TextView, CharSequence].contramapL( _.getEditText )
    }

    implicit val `Injection[TextInputLayout, Option[CharSequence]]`: Injection[TextInputLayout, Option[CharSequence]] = {
        Injection[TextView, Option[CharSequence]].contramapL( _.getEditText )
    }
}

object injection extends injection