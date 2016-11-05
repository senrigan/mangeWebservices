package wsManageTicketOld;

public class CqSamygPreReportTickectWebServicePortTypeProxy implements wsManageTicketOld.CqSamygPreReportTickectWebServicePortType {
  private String _endpoint = null;
  private wsManageTicketOld.CqSamygPreReportTickectWebServicePortType cqSamygPreReportTickectWebServicePortType = null;
  
  public CqSamygPreReportTickectWebServicePortTypeProxy() {
    _initCqSamygPreReportTickectWebServicePortTypeProxy();
  }
  
  public CqSamygPreReportTickectWebServicePortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initCqSamygPreReportTickectWebServicePortTypeProxy();
  }
  
  private void _initCqSamygPreReportTickectWebServicePortTypeProxy() {
    try {
      cqSamygPreReportTickectWebServicePortType = (new wsManageTicketOld.CqSamygPreReportTickectWebServiceLocator()).getCqSamygPreReportTickectWebServicePort();
      if (cqSamygPreReportTickectWebServicePortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)cqSamygPreReportTickectWebServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)cqSamygPreReportTickectWebServicePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (cqSamygPreReportTickectWebServicePortType != null)
      ((javax.xml.rpc.Stub)cqSamygPreReportTickectWebServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public wsManageTicketOld.CqSamygPreReportTickectWebServicePortType getCqSamygPreReportTickectWebServicePortType() {
    if (cqSamygPreReportTickectWebServicePortType == null)
      _initCqSamygPreReportTickectWebServicePortTypeProxy();
    return cqSamygPreReportTickectWebServicePortType;
  }
  
  public wsManageTicketOld.TicketCqResult wsManageTicketIndex(wsManageTicketOld.Data_ticket_into data_ticket_into) throws java.rmi.RemoteException{
    if (cqSamygPreReportTickectWebServicePortType == null)
      _initCqSamygPreReportTickectWebServicePortTypeProxy();
    return cqSamygPreReportTickectWebServicePortType.wsManageTicketIndex(data_ticket_into);
  }
  
  
}