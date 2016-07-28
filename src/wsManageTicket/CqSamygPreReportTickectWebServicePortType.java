/**
 * CqSamygPreReportTickectWebServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package wsManageTicket;

public interface CqSamygPreReportTickectWebServicePortType extends java.rmi.Remote {

    /**
     * Reportar las alarmas generadas por el Conquest.
     */
    public wsManageTicket.TicketCqResult wsManageTicketIndex(wsManageTicket.Data_ticket_into data_ticket_into) throws java.rmi.RemoteException;
}
