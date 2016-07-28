package com.gdc.mangedengine.util.webservice;
import javax.xml.ws.WebServiceRef;
import helloservice.endpoint.HelloService;
import helloservice.endpoint.Hello;

public class HelloClient {
    @WebServiceRef(wsdlLocation="http://192.168.208.8:8080/conquestSamyg/public/ws/preCreateTicket/wsManageTicket.php?wsdl")
    static HelloService service;

    public static void main(String[] args) {
        try {
            HelloClient client = new HelloClient();
            client.doTest(args);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void doTest(String[] args) {
        try {
            System.out.println("Retrieving the port from
                     the following service: " + service);
            Hello port = service.getHelloPort();
            System.out.println("Invoking the sayHello operation
                     on the port.");

            String name;
            if (args.length > 0) {
                name = args[0];
            } else {
                name = "No Name";
            }

            String response = port.sayHello(name);
            System.out.println(response);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
