package io.taig.android.viewvalue.syntax

import io.taig.android.viewvalue._

import scala.language.{ implicitConversions, reflectiveCalls }

trait map {
    implicit def extractionMapSyntax[A <: Attribute, V, T](
        extraction: Extraction[A, V, T]
    ): operation.map[( { type λ[α] = Extraction[A, V, α] } )#λ, T] = {
        new operation.map[( { type λ[α] = Extraction[A, V, α] } )#λ, T]( extraction )
    }
}

object map extends map