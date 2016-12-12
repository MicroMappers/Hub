package org.qcri.micromappers.models;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Kushal
 * A JAVA POJO class used to define a collection base class for Twitter/Facebook collection.
 */
public class CollectionTask {

	protected String collectionCode;
	protected String collectionName;
	protected String toTrack;
    protected String lastDocument;
    protected String statusCode;
    protected String statusMessage;
    protected String accessToken;
    protected String accessTokenSecret;
    protected Long collectionCount;
    protected String provider;
    protected String toFollow;
    
    public CollectionTask() {}		
    
    /**
     * @return the toTrack
     */
    public String getToTrack() {
        return toTrack;
    }

    /**
     * @param toTrack the toTrack to set
     */
    public void setToTrack(String toTrack) {
        this.toTrack = toTrack;
    }

    /**
     * @return the accessToken
     */
    //@XmlTransient
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken the accessToken to set
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * @return the accessTokenSecret
     */
    //@XmlTransient
    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }

    /**
     * @param accessTokenSecret the accessTokenSecret to set
     */
    public void setAccessTokenSecret(String accessTokenSecret) {
        this.accessTokenSecret = accessTokenSecret;
    }

    public boolean isToTrackAvailable() {
        if (StringUtils.isNotEmpty(toTrack)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkSocialConfigInfo() {
    	
    	boolean isConfigured = StringUtils.isNotEmpty(getAccessToken());
    	
     	if(provider.equals("Twitter")) {
    		isConfigured = isConfigured && StringUtils.isNotEmpty(getAccessTokenSecret()); 
    	}
    		
        return isConfigured;
                
    }

    /**
     * @return the collectionName
     */
    public String getCollectionName() {
        return collectionName;
    }

    /**
     * @param collectionName the collectionName to set
     */
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    /**
     * @return the collectionCode
     */
    public String getCollectionCode() {
        return collectionCode;
    }

    /**
     * @param collectionCode the collectionCode to set
     */
    public void setCollectionCode(String collectionCode) {
        this.collectionCode = collectionCode;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CollectionTask other = (CollectionTask) obj;
        if ((this.accessToken == null) ? (other.accessToken != null) : !this.accessToken.equals(other.accessToken)) {
            return false;
        }
        if ((this.accessTokenSecret == null) ? (other.accessTokenSecret != null) : !this.accessTokenSecret.equals(other.accessTokenSecret)) {
            return false;
        }
        return true;
    }

    /**
     * @return the collectionCount
     */
    public Long getCollectionCount() {
        return collectionCount;
    }

    /**
     * @param collectionCount the collectionCount to set
     */
    public void setCollectionCount(Long collectionCount) {
        this.collectionCount = collectionCount;
    }

    /**
     * @return the lastDocument
     */
    public String getLastDocument() {
        return lastDocument;
    }

    /**
     * @param lastDocument the lastDocument to set
     */
    public void setLastDocument(String lastDocument) {
        this.lastDocument = lastDocument;
    }

	/**
     * @return the status
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * @param status the status to set
     */
    public void setStatusCode(String status) {
        this.statusCode = status;
    }

    /**
     * @return the statusMessage
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @param statusMessage the statusMessage to set
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public boolean isToFollowAvailable() {
        if (StringUtils.isNotBlank(toFollow)) {
            return true;
        } else {
            return false;
        }

    }

    @Override
	public CollectionTask clone() {

        CollectionTask newTask = new CollectionTask();
        newTask.setAccessToken(accessToken);
        newTask.setAccessTokenSecret(accessTokenSecret);
        newTask.setCollectionCode(collectionCode);
        newTask.setCollectionName(collectionName);
        newTask.setLastDocument(lastDocument);
        newTask.setStatusCode(statusCode);
        newTask.setStatusMessage(statusMessage);
        newTask.setToTrack(toTrack);
        newTask.setToFollow(toFollow);
        newTask.setCollectionCount(collectionCount);
        return newTask;
    }

	/**
	 * @return the toFollow
	 */
	public String getToFollow() {
		return toFollow;
	}

	/**
	 * @param toFollow the toFollow to set
	 */
	public void setToFollow(String toFollow) {
		this.toFollow = toFollow;
	}

}
