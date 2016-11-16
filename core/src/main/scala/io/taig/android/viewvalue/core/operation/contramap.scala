package io.taig.android.viewvalue.core.operation

import io.taig.android.viewvalue.core.functional.{ Contramap, ContramapL, ContramapR }

import scala.language.higherKinds

class contramap[F[_], L]( fa: F[L] )( implicit c: Contramap[F] ) {
    def contramap[T]( f: T ⇒ L ): F[T] = c.contramap( fa )( f )
}

class contramapL[F[_, _], L, R]( fa: F[L, R] )( implicit cl: ContramapL[F] ) {
    def contramapL[T]( f: ( T, R ) ⇒ L ): F[T, R] = cl.contramapL( fa )( f )
}

class contramapR[F[_, _], L, R]( fa: F[L, R] )( implicit cr: ContramapR[F] ) {
    def contramapR[T]( f: ( L, T ) ⇒ R ): F[L, T] = cr.contramapL( fa )( f )
}