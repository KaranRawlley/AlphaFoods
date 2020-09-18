package com.mind.exception_handling;

public class UserErrorResponce 
{
	private Integer status;
	private String message;
	private long timestrap;
	
	public UserErrorResponce(Integer status, String message, long timestrap) {
		this.status = status;
		this.message = message;
		this.timestrap = timestrap;
	}

	public UserErrorResponce() {
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimestrap() {
		return timestrap;
	}

	public void setTimestrap(long timestrap) {
		this.timestrap = timestrap;
	}
	

}



