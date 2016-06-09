package br.gov.sp.fatec.entity;

import org.joda.time.DateTime;

public class TokenInfo {
	private Long userId;
    private DateTime issued;
    private DateTime expires;
    
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
