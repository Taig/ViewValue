package io.taig.android.viewvalue

sealed trait Attribute

object Attribute {
    sealed trait Value extends Attribute
    sealed trait Feedback extends Attribute
}