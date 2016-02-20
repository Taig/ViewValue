package io.taig.android.viewvalue.design

import android.support.design.widget.TextInputLayout
import android.widget.TextView

import implicits._

object Lab {
    val tv: TextView = ???
    val tip: TextInputLayout = ???

    tv.error = ""
    tv.error = 3
    tv.error = None
    tv.error = Some( "" )

    tip.error = ""
    tip.error = 3
    tip.error = None
    tip.error = Some( "" )
}