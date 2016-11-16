package io.taig.android.viewvalue.core.operation

import io.taig.android.viewvalue.core.functional.Map

import scala.language.higherKinds

class map[F[_], L]( fa: F[L] )( implicit m: Map[F] ) {
    def map[T]( f: L ⇒ T ): F[T] = m.map( fa )( f )
}