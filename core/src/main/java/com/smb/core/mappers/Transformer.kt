package com.smb.core.mappers

/**
 * Created by dev on 08.02.18.
 */

abstract class Transformer<in T, out U> private constructor() : (T) -> U {

    fun asListMapper(): Transformer<List<T>?, List<U>> =
            object : Transformer<List<T>?, List<U>>() {
                override fun invoke(t: List<T>?) = t?.map(this@Transformer) ?: emptyList()
            }

    companion object {

        fun <T, U> build(action: T.() -> U): Transformer<T, U> =
                object : Transformer<T, U>() {

                    override fun invoke(t: T) = action(t)
                }
    }
}
