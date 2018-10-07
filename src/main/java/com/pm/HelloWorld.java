package com.pm;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class HelloWorld {

    @GET
    @Path("/json")
    @Produces(MediaType.TEXT_PLAIN)
    public String getHelloWorldJSON() {
        return "Hello world!";
    }

    @GET
    @Path("/xml")
    @Produces(MediaType.APPLICATION_XML)
    public String getHelloWorldXML() {
        return "<xml><result>Hello World</result></xml>";
    }

}