package com.streamworx.ws.command;

public class CommandResponse {
	  private Integer StatusCode;
	  private String StatusDescription;
	  private String Result;

	  public CommandResponse() {
	    super();
	  }

	  public CommandResponse(Integer StatusCode, String StatusDescription, String Result) {
	    super();
	    this.StatusCode = StatusCode;
	    this.StatusDescription = StatusDescription;
	    this.Result = Result;
	  }

	  public CommandResponse(Integer StatusCode, String StatusDescription) {
	    super();
	    this.StatusCode = StatusCode;
	    this.StatusDescription = StatusDescription;
	  }

	  // Getters and Setters omitted
	  
	  @Override
	  public String toString() {
	    return String.format("Response [StatusCode=%d, StatusDescription=%s, Result=%s]", StatusCode, StatusDescription, Result);
	  }

	}