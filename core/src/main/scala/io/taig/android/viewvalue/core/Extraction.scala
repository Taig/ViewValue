package io.taig.android.viewvalue.core

import io.taig.android.viewvalue.core.functional._

import scala.language.reflectiveCalls

trait Extraction[A <: Attribute, -V, +T] {
    def extract( view: V ): T
}

object Extraction {
    type Error[V] = Extraction[Attribute.Error, V, Option[String]]

    type Value[V, T] = Extraction[Attribute.Value, V, T]

    implicit def contramapExtraction[A <: Attribute, T] = {
        new Contramap[( { type λ[α] = Extraction[A, α, T] } )#λ] {
            override def contramap[L, U]( fa: Extraction[A, L, T] )( f: U ⇒ L ): Extraction[A, U, T] = {
                instance( view ⇒ fa.extract( f( view ) ) )
            }
        }
    }

    implicit def mapExtraction[A <: Attribute, V] = {
        new Map[( { type λ[α] = Extraction[A, V, α] } )#λ] {
            override def map[L, U]( fa: Extraction[A, V, L] )( f: L ⇒ U ): Extraction[A, V, U] = {
                instance( view ⇒ f( fa.extract( view ) ) )
            }
        }
    }

    @inline
    def apply[A <: Attribute, V, T](
        implicit
        e: Extraction[A, V, T]
    ): Extraction[A, V, T] = e

    def instance[A <: Attribute, V, T]( f: V ⇒ T ): Extraction[A, V, T] = {
        new Extraction[A, V, T] {
            override def extract( view: V ): T = f( view )
        }
    }
}