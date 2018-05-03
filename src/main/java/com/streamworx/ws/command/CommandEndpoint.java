package com.streamworx.ws.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.stx.commands.GetCommandRequest;
import com.stx.commands.GetCommandResponse;



@Endpoint
public class CommandEndpoint {

  @PayloadRoot(namespace = "http://stx.com/commands", localPart = "GetCommandRequest")
  @ResponsePayload
  public GetCommandResponse processCourseDetailsRequest(@RequestPayload GetCommandRequest request) throws IOException {
    GetCommandResponse response = new GetCommandResponse();   
    response.setStatusCode(0);
    response.setStatusDescription("SUCCESS");
    boolean success;
    
    JSch jsch = new JSch();
    try {

        Session session = jsch.getSession(request.getUsername(), request.getIp(), request.getPort());
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(request.getPassword());
        session.connect();
        System.out.println("session connected");

        Channel channel = session.openChannel("exec");
        ((ChannelExec)channel).setCommand(request.getCommand());
        channel.setInputStream(null);
        ((ChannelExec)channel).setErrStream(System.err);
         
        InputStream input = channel.getInputStream();
        channel.connect();
         
        System.out.println("Channel Connected to machine " + request.getIp() + " server with command: " + request.getCommand() ); 
         
        try{
            InputStreamReader inputReader = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            String line = null;
             
            while((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }
            bufferedReader.close();
            inputReader.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        success=true;

    } catch (JSchException e) {
        System.out.println("failed");
        success=false;
        e.printStackTrace();

    }

	response.setResult("http://"+request.getIp()+":"+request.getPort()+" >>> "+request.getCommand());
    if(success) {
    	response.setStatusCode(0);
    	response.setStatusDescription("Success");
    }
    else
    {
    	response.setStatusCode(1);    	
    	response.setStatusDescription("Error");
    	response.setErrorCode(999);
    	response.setErrorMessage("Super Failed");
    	

    }
    
    
    return response;
  }

}
