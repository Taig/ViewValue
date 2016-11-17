package io.taig.android

import android.support.design.widget.TextInputLayout
import android.view.View

import scala.annotation.tailrec

package object viewvalue {
    @tailrec
    private[viewvalue] def findParentTextInputLayout( view: View ): Option[TextInputLayout] = {
        view.getParent match {
            case view: TextInputLayout ⇒ Some( view )
            case view: View            ⇒ findParentTextInputLayout( view )
            case _                     ⇒ None
        }
    }
}