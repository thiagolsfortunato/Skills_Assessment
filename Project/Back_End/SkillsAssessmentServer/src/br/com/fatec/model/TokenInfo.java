package br.com.fatec.model;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;

public class TokenInfo {
	private String userId;
    private DateTime issued;
    private DateTime expires;
    
    /*public ObjectId getUserId() {
        return userId;
    }
    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }*/
    public DateTime getIssued() {
        return issued;
    }
    public void setIssued(DateTime issued) {
        this.issued = issued;
    }
    public DateTime getExpires() {
        return expires;
    }
    public void setExpires(DateTime expires) {
        this.expires = expires;
    }
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
