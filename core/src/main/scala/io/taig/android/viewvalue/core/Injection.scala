package io.taig.android.viewvalue.core

import android.view.View
import io.taig.android.viewvalue.core.functional.{ ContramapL, ContramapR }
import io.taig.android.viewvalue.core.syntax.contramap._

import scala.language.reflectiveCalls

trait Injection[A <: Attribute, -V, -T] {
    def inject( view: V, value: T ): Unit
}

object Injection extends Injection0 {
    implicit def contramapLInjection[A <: Attribute] = {
        new ContramapL[( { type λ[α, β] = Injection[A, α, β] } )#λ] {
            override def contramapL[L, R, T]( fa: Injection[A, L, R] )( f: ( T, R ) ⇒ L ): Injection[A, T, R] = {
                instance { ( view, value ) ⇒ fa.inject( f( view, value ), value ) }
            }
        }
    }

    implicit def contramapRInjection[A <: Attribute] = {
        new ContramapR[( { type λ[α, β] = Injection[A, α, β] } )#λ] {
            override def contramapL[L, R, T]( fa: Injection[A, L, R] )( f: ( L, T ) ⇒ R ): Injection[A, L, T] = {
                instance { ( view, value ) ⇒ fa.inject( view, f( view, value ) ) }
            }
        }
    }

    @inline
    def apply[A <: Attribute, V, T](
        implicit
        i: Injection[A, V, T]
    ): Injection[A, V, T] = i

    def instance[A <: Attribute, V, T]( f: ( V, T ) ⇒ Unit ): Injection[A, V, T] = {
        new Injection[A, V, T] {
            override def inject( view: V, value: T ): Unit = f( view, value )
        }
    }
}

trait Injection0 {
    type Error[V, T] = Injection[Attribute.Error, V, T]

    type Value[V, T] = Injection[Attribute.Value, V, T]

    implicit def injectionErrorViewValue[V, T](
        implicit
        i: Error[V, Option[T]]
    ): Error[V, T] = i.contramapR( ( _, error ) ⇒ Option( error ) )

    implicit def injectionErrorViewResource[V <: View](
        implicit
        i: Error[V, String]
    ): Error[V, Int] = i.contramapR( _.getContext.getString( _ ) )
}