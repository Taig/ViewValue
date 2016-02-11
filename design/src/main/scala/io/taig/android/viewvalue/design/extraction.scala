package io.taig.android.viewvalue.design

import android.support.design.widget.TextInputLayout
import android.widget.TextView
import io.taig.android.viewvalue.Extraction

trait extraction {
    implicit val `Extraction[TextInputLayout, String]`: Extraction[TextInputLayout, String] = {
        Extraction[TextView, String].contramap( _.getEditText )
    }

    implicit val `Extraction[TextInputLayout, Option[String]]`: Extraction[TextInputLayout, Option[String]] = {
        Extraction[TextView, Option[String]].contramap( _.getEditText )
    }
}

object extraction extends extraction