package io.taig.android.viewvalue.core.syntax

import io.taig.android.viewvalue.core._

import scala.language.{ higherKinds, implicitConversions, reflectiveCalls }

trait contramap {
    implicit def extractionContramapSyntax[A <: Attribute, V, T](
        extraction: Extraction[A, V, T]
    ): operation.contramap[( { type λ[α] = Extraction[A, α, T] } )#λ, V] = {
        new operation.contramap[( { type λ[α] = Extraction[A, α, T] } )#λ, V]( extraction )
    }

    implicit def injectionContramapLSyntax[A <: Attribute, V, T](
        injection: Injection[A, V, T]
    ): operation.contramapL[( { type λ[α, β] = Injection[A, α, β] } )#λ, V, T] = {
        new operation.contramapL[( { type λ[α, β] = Injection[A, α, β] } )#λ, V, T]( injection )
    }

    implicit def injectionContramapRSyntax[A <: Attribute, V, T](
        injection: Injection[A, V, T]
    ): operation.contramapR[( { type λ[α, β] = Injection[A, α, β] } )#λ, V, T] = {
        new operation.contramapR[( { type λ[α, β] = Injection[A, α, β] } )#λ, V, T]( injection )
    }
}

object contramap extends contramap