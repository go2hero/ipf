/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openehealth.ipf.commons.ihe.hl7v2;

import ca.uhn.hl7v2.ErrorCode;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.Version;
import org.openehealth.ipf.commons.ihe.core.atna.AuditStrategy;
import org.openehealth.ipf.commons.ihe.hl7v2.audit.QueryAuditDataset;

import java.util.List;

/**
 * A MLLP transaction configuration with PDQ-specific methods for continuation support.
 *
 * @author Dmytro Rud
 */
public class PdqTransactionConfiguration extends Hl7v2TransactionConfiguration<QueryAuditDataset> {
   
    public PdqTransactionConfiguration(
            String name,
            String description,
            boolean isQuery,
            AuditStrategy<QueryAuditDataset> clientAuditStrategy,
            AuditStrategy<QueryAuditDataset> serverAuditStrategy,
            Version[] hl7Version,
            String sendingApplication, 
            String sendingFacility,
            ErrorCode requestErrorDefaultErrorCode,
            ErrorCode responseErrorDefaultErrorCode,
            String[] allowedRequestMessageTypes,
            String[] allowedRequestTriggerEvents,
            String[] allowedResponseMessageTypes,
            String[] allowedResponseTriggerEvents, 
            boolean[] auditabilityFlags,
            boolean[] responseContinuabilityFlags,
            HapiContext hapiContext)
    {
        super(name, description, isQuery, clientAuditStrategy, serverAuditStrategy,
                hl7Version, sendingApplication, sendingFacility,
                requestErrorDefaultErrorCode,
                responseErrorDefaultErrorCode, allowedRequestMessageTypes,
                allowedRequestTriggerEvents, allowedResponseMessageTypes,
                allowedResponseTriggerEvents, auditabilityFlags,
                responseContinuabilityFlags, hapiContext);
    }
    
    @Override
    public boolean isDataStartSegment(List<String> segments, int index) {
        return segments.get(index).startsWith("PID");
    }

    @Override
    public boolean isFooterStartSegment(List<String> segments, int index) {
        return segments.get(index).startsWith("DSC");
    }
}
