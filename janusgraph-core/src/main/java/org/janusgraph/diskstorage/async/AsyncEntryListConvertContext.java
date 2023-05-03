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

import org.janusgraph.diskstorage.EntryList;
import org.janusgraph.diskstorage.EntryMetaData;

import java.util.concurrent.CompletableFuture;

public class AsyncEntryListConvertContext {

    private final CompletableFuture<EntryList> result = new CompletableFuture<>();

    public volatile long[] limitAndValuePos = new long[10];
    public volatile byte[] data = new byte[limitAndValuePos.length*15];
    public volatile EntryMetaData[] metadataSchema = null;
    public volatile int pos=0;
    public volatile int offset=0;

    public void complete(EntryList resultData){
        this.result.complete(resultData);
    }

    public CompletableFuture<EntryList> getResult() {
        return result;
    }
}
