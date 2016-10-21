/**
 * CqSamygPreReportTickectWebServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package wsManageTicket;

public class CqSamygPreReportTickectWebServiceLocator extends org.apache.axis.client.Service implements wsManageTicket.CqSamygPreReportTickectWebService {

    public CqSamygPreReportTickectWebServiceLocator() {
    }


    public CqSamygPreReportTickectWebServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CqSamygPreReportTickectWebServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CqSamygPreReportTickectWebServicePort
    private java.lang.String CqSamygPreReportTickectWebServicePort_address = "http://192.168.207.220/samyg/conquest/public/ws/preCreateTicket/wsManageTicket.php";

    public java.lang.String getCqSamygPreReportTickectWebServicePortAddress() {
        return CqSamygPreReportTickectWebServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CqSamygPreReportTickectWebServicePortWSDDServiceName = "CqSamyg Pre ReportTickect Web ServicePort";

    public java.lang.String getCqSamygPreReportTickectWebServicePortWSDDServiceName() {
        return CqSamygPreReportTickectWebServicePortWSDDServiceName;
    }

    public void setCqSamygPreReportTickectWebServicePortWSDDServiceName(java.lang.String name) {
        CqSamygPreReportTickectWebServicePortWSDDServiceName = name;
    }

    public wsManageTicket.CqSamygPreReportTickectWebServicePortType getCqSamygPreReportTickectWebServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CqSamygPreReportTickectWebServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCqSamygPreReportTickectWebServicePort(endpoint);
    }

    public wsManageTicket.CqSamygPreReportTickectWebServicePortType getCqSamygPreReportTickectWebServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            wsManageTicket.CqSamygPreReportTickectWebServiceBindingStub _stub = new wsManageTicket.CqSamygPreReportTickectWebServiceBindingStub(portAddress, this);
            _stub.setPortName(getCqSamygPreReportTickectWebServicePortWSDDServiceName());
            _stub._setProperty(javax.xml.rpc.Call.USERNAME_PROPERTY, "usr_1nt3rn4l");
            _stub._setProperty(javax.xml.rpc.Call.PASSWORD_PROPERTY, "w5_1nt3rn4l");



            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCqSamygPreReportTickectWebServicePortEndpointAddress(java.lang.String address) {
        CqSamygPreReportTickectWebServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (wsManageTicket.CqSamygPreReportTickectWebServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                wsManageTicket.CqSamygPreReportTickectWebServiceBindingStub _stub = new wsManageTicket.CqSamygPreReportTickectWebServiceBindingStub(new java.net.URL(CqSamygPreReportTickectWebServicePort_address), this);
                _stub.setPortName(getCqSamygPreReportTickectWebServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CqSamyg Pre ReportTickect Web ServicePort".equals(inputPortName)) {
            return getCqSamygPreReportTickectWebServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:wsManageTicket", "CqSamyg Pre ReportTickect Web Service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:wsManageTicket", "CqSamyg Pre ReportTickect Web ServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CqSamygPreReportTickectWebServicePort".equals(portName)) {
            setCqSamygPreReportTickectWebServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
