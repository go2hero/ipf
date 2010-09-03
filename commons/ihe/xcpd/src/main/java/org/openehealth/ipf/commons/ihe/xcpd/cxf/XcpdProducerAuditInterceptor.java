/*
 * Copyright 2009 the original author or authors.
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
package org.openehealth.ipf.commons.ihe.xcpd.cxf;

import java.util.List;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.ws.addressing.AddressingProperties;
import org.apache.cxf.ws.addressing.JAXWSAConstants;
import org.apache.cxf.ws.addressing.Names;
import org.openehealth.ipf.commons.ihe.ws.correlation.AsynchronyCorrelator;
import org.openehealth.ipf.commons.ihe.ws.cxf.audit.AuditInterceptor;
import org.openehealth.ipf.commons.ihe.ws.cxf.audit.WsAuditStrategy;
import org.openehealth.ipf.commons.ihe.xcpd.XcpdAuditDataset;
import org.openehealth.ipf.commons.ihe.xcpd.XcpdAuditStrategy;


/**
 * CXF interceptor which stores the URL of the target endpoint into 
 * audit dataset.  
 * <p>
 * Usable only for synchronous clients, because asynchronous clients use 
 * correlation, and services get request URLs from HTTP Servlet contexts.
 *
 * @author Dmytro Rud
 */
public class XcpdProducerAuditInterceptor extends AuditInterceptor {
    private final AsynchronyCorrelator correlator;
    
    /**
     * Constructor.
     * 
     * @param auditStrategy
     *      an audit strategy instance
     */
    public XcpdProducerAuditInterceptor(WsAuditStrategy auditStrategy, AsynchronyCorrelator correlator) {
        super(Phase.WRITE_ENDING, auditStrategy);
        this.correlator = correlator;
    }

    
    @Override
    protected void process(SoapMessage message) throws Exception {
        XcpdAuditDataset auditDataset = (XcpdAuditDataset) getAuditDataset(message);
        auditDataset.setServiceEndpointUrl((String) message.get(Message.ENDPOINT_ADDRESS));
        
        if (((XcpdAuditStrategy) getAuditStrategy()).needStoreRequestPayload()) {
            String payload = (String) message.getContent(List.class).get(0);
            AddressingProperties apropo = (AddressingProperties) message.get(JAXWSAConstants.CLIENT_ADDRESSING_PROPERTIES_OUTBOUND);
            if (Names.WSA_ANONYMOUS_ADDRESS.equals(apropo.getReplyTo().getAddress().getValue())) {
                auditDataset.setRequestPayload(payload);
            } else {
                correlator.setRequestPayload(apropo.getMessageID().getValue(), payload);
            }
        }
    }

}