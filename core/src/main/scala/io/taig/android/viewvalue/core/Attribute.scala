package io.taig.android.viewvalue.core

sealed trait Attribute

object Attribute {
    sealed trait Value extends Attribute
    sealed trait Error extends Attribute
}