package com.streamworx.ws.command;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.stx.commands.GetCommandRequest;
import com.stx.commands.GetCommandResponse;



@Endpoint
public class CommandEndpoint {

  @PayloadRoot(namespace = "http://stx.com/commands", localPart = "GetCommandRequest")
  @ResponsePayload
  public GetCommandResponse processCourseDetailsRequest(@RequestPayload GetCommandRequest request) {
    GetCommandResponse response = new GetCommandResponse();
    request.getIp()   
    response.setStatusCode(0);
    response.setStatusDescription("SUCCESS");
    
    
    return response;
  }

}
