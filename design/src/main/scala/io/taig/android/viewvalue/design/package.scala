package io.taig.android.viewvalue

import android.support.design.widget.TextInputLayout
import android.view.View

import scala.annotation.tailrec

package object design {
    @tailrec
    private[viewvalue] def findParentTextInputLayout( view: View ): Option[TextInputLayout] = {
        view.getParent match {
            case view: TextInputLayout ⇒ Some( view )
            case view: View            ⇒ findParentTextInputLayout( view )
            case _                     ⇒ None
        }
    }
}