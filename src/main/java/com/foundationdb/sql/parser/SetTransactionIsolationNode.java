/**
 * Copyright 2011-2013 FoundationDB, LLC
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/* The original from which this derives bore the following: */

/*

   Derby - Class org.apache.derby.impl.sql.compile.SetTransactionIsolationNode

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to you under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

package com.foundationdb.sql.parser;

import com.foundationdb.sql.StandardException;

/**
 * A SetTransactionIsolationNode is the root of a QueryTree that represents a SET
 * TRANSACTION ISOLATION command
 *
 */

public class SetTransactionIsolationNode extends TransactionStatementNode
{
    private boolean current;
    private IsolationLevel isolationLevel;

    /**
     * Initializer for SetTransactionIsolationNode
     *
     * @param current Whether applies to current transaction or session default
     * @param isolationLevel The new isolation level
     */
    public void init(Object current,
                     Object isolationLevel) {
        this.current = (Boolean)current;
        this.isolationLevel = (IsolationLevel)isolationLevel;
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        SetTransactionIsolationNode other = (SetTransactionIsolationNode)node;
        this.current = other.current;
        this.isolationLevel = other.isolationLevel;
    }

    /**
     * Convert this object to a String.  See comments in QueryTreeNode.java
     * for how this should be done for tree printing.
     *
     * @return This object as a String
     */

    public String toString() {
        return "current: " + current + "\n" +
               "isolationLevel: " + isolationLevel + "\n" +
            super.toString();
    }

    public boolean isCurrent() {
        return current;
    }

    public IsolationLevel getIsolationLevel() {
        return isolationLevel;
    }

    public String statementToString() {
        if (current)
            return "SET TRANSACTION ISOLATION";
        else
            return "SET SESSION CHARACTERISTICS AS TRANSACTION ISOLATION";
    }

}
