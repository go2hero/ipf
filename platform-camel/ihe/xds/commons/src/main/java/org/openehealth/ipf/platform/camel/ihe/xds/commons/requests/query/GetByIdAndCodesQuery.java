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
package org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.metadata.Code;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.metadata.DocumentEntry;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.metadata.XDSMetaClass;

/**
 * Base class for queries that are defined by:
 * <li> a UUID or unique ID 
 * <li> a list of format codes
 * <li> a list of confidentiality codes
 * @author Jens Riemschneider
 */
public abstract class GetByIdAndCodesQuery extends StoredQuery {
    private QueryList<Code> confidentialityCodes;
    private List<Code> formatCodes;
    
    private String uuid;
    private String uniqueId;
    private String homeCommunityId;

    /**
     * Constructs the query.
     * @param type
     *          the type of query.
     */
    protected GetByIdAndCodesQuery(QueryType type) {
        super(type);
    }
    
    /**
     * @return the codes for filtering {@link DocumentEntry#getConfidentialityCodes()}.
     */
    public QueryList<Code> getConfidentialityCodes() {
        return confidentialityCodes;
    }

    /**
     * @param confidentialityCodes
     *          the codes for filtering {@link DocumentEntry#getConfidentialityCodes()}.
     */
    public void setConfidentialityCodes(QueryList<Code> confidentialityCodes) {
        this.confidentialityCodes = confidentialityCodes;
    }

    /**
     * @return the codes for filtering {@link DocumentEntry#getFormatCode()}.
     */
    public List<Code> getFormatCodes() {
        return formatCodes;
    }

    /**
     * @param formatCodes
     *          the codes for filtering {@link DocumentEntry#getFormatCode()}.
     */
    public void setFormatCodes(List<Code> formatCodes) {
        this.formatCodes = formatCodes;
    }

    /**
     * @return the uuid to filter {@link XDSMetaClass#getEntryUuid()}.
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     *          the uuid to filter {@link XDSMetaClass#getEntryUuid()}.
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the unique ID to filter {@link XDSMetaClass#getUniqueId()}.
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * @param uniqueId
     *          the unique ID to filter {@link XDSMetaClass#getUniqueId()}.
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * @return the home community ID.
     */
    public String getHomeCommunityId() {
        return homeCommunityId;
    }

    /**
     * @param homeCommunityId   
     *          the home community ID.
     */
    public void setHomeCommunityId(String homeCommunityId) {
        this.homeCommunityId = homeCommunityId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((confidentialityCodes == null) ? 0 : confidentialityCodes.hashCode());
        result = prime * result + ((formatCodes == null) ? 0 : formatCodes.hashCode());
        result = prime * result + ((homeCommunityId == null) ? 0 : homeCommunityId.hashCode());
        result = prime * result + ((uniqueId == null) ? 0 : uniqueId.hashCode());
        result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GetByIdAndCodesQuery other = (GetByIdAndCodesQuery) obj;
        if (confidentialityCodes == null) {
            if (other.confidentialityCodes != null)
                return false;
        } else if (!confidentialityCodes.equals(other.confidentialityCodes))
            return false;
        if (formatCodes == null) {
            if (other.formatCodes != null)
                return false;
        } else if (!formatCodes.equals(other.formatCodes))
            return false;
        if (homeCommunityId == null) {
            if (other.homeCommunityId != null)
                return false;
        } else if (!homeCommunityId.equals(other.homeCommunityId))
            return false;
        if (uniqueId == null) {
            if (other.uniqueId != null)
                return false;
        } else if (!uniqueId.equals(other.uniqueId))
            return false;
        if (uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!uuid.equals(other.uuid))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
