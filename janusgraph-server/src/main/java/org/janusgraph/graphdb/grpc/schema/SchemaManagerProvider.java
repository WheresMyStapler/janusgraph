// Copyright 2021 JanusGraph Authors
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

package org.janusgraph.graphdb.grpc.schema;

import org.janusgraph.core.JanusGraph;
import org.janusgraph.core.schema.JanusGraphManagement;
import org.janusgraph.graphdb.grpc.types.VertexLabel;

public class SchemaManagerProvider {
    private final JanusGraphManagement management;

    public SchemaManagerProvider(JanusGraph graph) {
        this.management = graph.openManagement();
    }

    private VertexLabel createVertexLabelProto(org.janusgraph.core.VertexLabel vertexLabel) {
        return VertexLabel.newBuilder()
            .setId(vertexLabel.id().toString())
            .setName(vertexLabel.name())
            .setPartitioned(vertexLabel.isPartitioned())
            .setReadOnly(vertexLabel.isStatic())
            .build();
    }


    public VertexLabel getVertexLabelByName(String name) {
        org.janusgraph.core.VertexLabel vertexLabel = management.getVertexLabel(name);
        if (vertexLabel == null) {
            return null;
        }

        return createVertexLabelProto(vertexLabel);
    }
}
