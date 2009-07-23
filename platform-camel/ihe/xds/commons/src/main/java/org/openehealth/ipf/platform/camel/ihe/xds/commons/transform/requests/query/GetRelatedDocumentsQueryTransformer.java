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
package org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.query;

import static org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.QueryParameter.*;

import org.openehealth.ipf.platform.camel.ihe.xds.commons.ebxml.EbXMLAdhocQueryRequest;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.GetDocumentsQuery;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.GetRelatedDocumentsQuery;

/**
 * Transforms between a {@link GetDocumentsQuery} and {@link EbXMLAdhocQueryRequest}.
 * @author Jens Riemschneider
 */
public class GetRelatedDocumentsQueryTransformer extends GetFromDocumentQueryTransformer<GetRelatedDocumentsQuery> {
    @Override
    public void toEbXML(GetRelatedDocumentsQuery query, EbXMLAdhocQueryRequest ebXML) {
        if (query == null || ebXML == null) {
            return;
        }
        
        super.toEbXML(query, ebXML);
        
        QuerySlotHelper slots = new QuerySlotHelper(ebXML);
        slots.fromAssociationType(ASSOCIATION_TYPE, query.getAssociationTypes());
    }
    
    @Override
    public void fromEbXML(GetRelatedDocumentsQuery query, EbXMLAdhocQueryRequest ebXML) {
        if (query == null || ebXML == null) {
            return;
        }
        
        super.fromEbXML(query, ebXML);
        
        QuerySlotHelper slots = new QuerySlotHelper(ebXML);        
        query.setAssociationTypes(slots.toAssociationType(ASSOCIATION_TYPE));
    }
}
