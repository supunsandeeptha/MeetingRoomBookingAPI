package supun.com.main.exception;

import java.util.Date;

// class for error details
public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String details;

	// constructors
	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	//getters
	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}
