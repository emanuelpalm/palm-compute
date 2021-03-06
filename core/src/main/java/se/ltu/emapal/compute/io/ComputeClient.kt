package se.ltu.emapal.compute.io

import rx.Observable
import se.ltu.emapal.compute.ComputeBatch
import se.ltu.emapal.compute.ComputeError
import se.ltu.emapal.compute.ComputeLambda
import se.ltu.emapal.compute.ComputeLogEntry
import java.io.Closeable

/**
 * Manages service communication on behalf of some compute client.
 */
interface ComputeClient : Closeable {
    /** Submits processed batch for eventual transmission to connected compute service. */
    fun submit(batch: ComputeBatch)

    /** Submits compute error for eventual transmission to connected compute service. */
    fun submit(error: ComputeError)

    /** Submits log entry for eventual transmission to connected compute service. */
    fun submit(logEntry: ComputeLogEntry)

    /** Publishes received batches. */
    val whenBatch: Observable<ComputeBatch>

    /** Publishes generated errors. */
    val whenException: Observable<Throwable>

    /** Publishes received lambdas. */
    val whenLambda: Observable<ComputeLambda>

    /** Publishes client status changes. */
    val whenStatus: Observable<ComputeClientStatus>

}