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
    fun whenBatch(): Observable<ComputeBatch>

    /** Publishes generated errors. */
    fun whenError(): Observable<Throwable>

    /** Publishes received lambdas. */
    fun whenLambda(): Observable<ComputeLambda>

    /** Publishes client status changes. */
    fun whenStatus(): Observable<Status>

    /**
     * Client status.
     */
    enum class Status {
        /** Client is currently connected to service. */
        CONNECTED,

        /** Client is currently attempting to connect to service. */
        CONNECTING,

        /** Client is not currently connected to service. */
        DISCONNECTED,

        /** Client was connected to a service, which terminated the connection. */
        TERMINATED
    }
}