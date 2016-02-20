package io.taig.android.viewvalue.functional

import scala.language.higherKinds

trait Map[F[_]] {
    def map[L, T]( fa: F[L] )( f: L ⇒ T ): F[T]
}