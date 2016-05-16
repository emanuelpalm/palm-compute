package se.ltu.emapal.compute.util

/**
 * Data type used for error handling.
 *
 * In places where it is expected that functions fail frequently, it may be inappropriate to use
 * regular exceptions, as these are easy to ignore and are expensive in terms of performance. The
 * [Result] type is constructed either as a success, wrapping an arbitrary value, or a failure,
 * wrapping an arbitrary error, and can later be destructed using a when expression.
 */
sealed class Result<out T, E : Throwable> {
    /** A successful result. */
    class Success<T, E : Throwable>(val value: T) : Result<T, E>() {
        override fun <U> apply(f: (T) -> Result<U, E>) = f(value)
        override fun <U> map(f: (T) -> U): Result<U, E> = Result.Success(f(value))
        override fun unwrap() = value

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }
            if (other?.javaClass != javaClass) {
                return false
            }
            other as Success<*, *>
            return value == other.value
        }

        override fun hashCode() = value?.hashCode() ?: 0
        override fun toString() = "Result.Success(value=$value)"
    }

    /** A failed result. */
    class Failure<T, E : Throwable>(val error: E) : Result<T, E>() {
        override fun <U> apply(f: (T) -> Result<U, E>): Result<U, E> = Result.Failure(error)
        override fun <U> map(f: (T) -> U): Result<U, E> = Result.Failure(error)
        override fun unwrap() = throw error

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }
            if (other?.javaClass != javaClass) {
                return false
            }
            other as Failure<*, *>
            return error == other.error
        }

        override fun hashCode() = error.hashCode()
        override fun toString() = "Result.Failure(error=$error)"
    }

    /** Applies function [f] to value, if a value is available. */
    abstract fun <U> apply(f: (T) -> Result<U, E>): Result<U, E>

    /** Maps function [f] to value, if a value is available. */
    abstract fun <U> map(f: (T) -> U): Result<U, E>

    /** Unwraps result, causing either its value to be returned, or error to be thrown. */
    abstract fun unwrap(): T
}