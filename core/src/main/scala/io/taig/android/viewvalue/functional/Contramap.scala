package io.taig.android.viewvalue.functional

import scala.language.higherKinds

trait Contramap[F[_]] {
    def contramap[L, T]( fa: F[L] )( f: T ⇒ L ): F[T]
}

trait ContramapL[F[_, _]] {
    def contramapL[L, R, T]( fa: F[L, R] )( f: ( T, R ) ⇒ L ): F[T, R]
}

trait ContramapR[F[_, _]] {
    def contramapL[L, R, T]( fa: F[L, R] )( f: ( L, T ) ⇒ R ): F[L, T]
}