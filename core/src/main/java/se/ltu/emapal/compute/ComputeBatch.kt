package se.ltu.emapal.compute

/**
 * Some arbitrary byte array to be processed by some identified lambda function.
 *
 * @param lambdaId Identifies lambda responsible for processing batch.
 * @param batchId Batch identifier unique within the application.
 * @param data Arbitrary byte array to process.
 */
class ComputeBatch(
        lambdaId: Int,
        batchId: Int,
        data: ByteArray
)