package io.taig.android.viewvalue.syntax

import io.taig.android.viewvalue.{ Attribute, Extraction, Injection, ops }

import scala.language.{ higherKinds, implicitConversions, reflectiveCalls }

trait contramap {
    implicit def extractionContramapSyntax[A <: Attribute, V, T](
        extraction: Extraction[A, V, T]
    ): ops.contramap[( { type λ[α] = Extraction[A, α, T] } )#λ, V] = {
        new ops.contramap[( { type λ[α] = Extraction[A, α, T] } )#λ, V]( extraction )
    }

    implicit def injectionContramapLSyntax[A <: Attribute, V, T](
        injection: Injection[A, V, T]
    ): ops.contramapL[( { type λ[α, β] = Injection[A, α, β] } )#λ, V, T] = {
        new ops.contramapL[( { type λ[α, β] = Injection[A, α, β] } )#λ, V, T]( injection )
    }

    implicit def injectionContramapRSyntax[A <: Attribute, V, T](
        injection: Injection[A, V, T]
    ): ops.contramapR[( { type λ[α, β] = Injection[A, α, β] } )#λ, V, T] = {
        new ops.contramapR[( { type λ[α, β] = Injection[A, α, β] } )#λ, V, T]( injection )
    }
}

object contramap extends contramap