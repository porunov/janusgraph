// Copyright 2023 JanusGraph Authors
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.janusgraph.diskstorage.async;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @param <T> single chunk of data
 */
public class DataChunks<T, C> {

    /**
     * Dynamic holder for data chunks to process.
     */
    private final Queue<T> dataChunks = new ConcurrentLinkedQueue<>();

    /**
     * If `false` no more data chunks are expected to be added. Thus, the processing thread is open to finish processing and return.
     */
    private volatile boolean lastChunkRetrieved;

    /**
     * Lock to be acquired by the processing thread to indicate to other potential threads which want to process the same
     * chunks of data that there is already a thread working on processing.
     * Notice that some threads will process only some chunks of data (older chunks) and may leave some unprocessed chunks.
     * Thus, each thread which brings a new chunk of data must check at the end that there is already some thread working on
     * processing the data. In case no threads working on processing the data - the thread which added a chunk of data to `dataChunks`
     * must acquire the lock and process all chunks which are left for processing.
     */
    private final ReentrantLock processingLock = new ReentrantLock();

    /**
     * Some partially or fully processed data with potential context information which can be used to resume processing
     * for the next chunk of data.
     */
    private final C processedDataContext;

    public DataChunks(C processedDataContext) {
        this.processedDataContext = processedDataContext;
    }

    public Queue<T> getDataChunks() {
        return dataChunks;
    }

    public boolean isLastChunkRetrieved() {
        return lastChunkRetrieved;
    }

    public void setLastChunkRetrieved() {
        this.lastChunkRetrieved = true;
    }

    public ReentrantLock getProcessingLock() {
        return processingLock;
    }

    public C getProcessedDataContext() {
        return processedDataContext;
    }
}
