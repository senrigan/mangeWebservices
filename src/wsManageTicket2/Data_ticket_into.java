/**
 * Data_ticket_into.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package wsManageTicket2;

public class Data_ticket_into  implements java.io.Serializable {
    private int service;

    private java.lang.String category;

    private java.lang.String childService;

    private int robot;

    private java.lang.String datetime;

    private java.lang.String typeAlert;

    private java.lang.String message;

    public Data_ticket_into() {
    }

    public Data_ticket_into(
           int service,
           java.lang.String category,
           java.lang.String childService,
           int robot,
           java.lang.String datetime,
           java.lang.String typeAlert,
           java.lang.String message) {
           this.service = service;
           this.category = category;
           this.childService = childService;
           this.robot = robot;
           this.datetime = datetime;
           this.typeAlert = typeAlert;
           this.message = message;
    }


    /**
     * Gets the service value for this Data_ticket_into.
     * 
     * @return service
     */
    public int getService() {
        return service;
    }


    /**
     * Sets the service value for this Data_ticket_into.
     * 
     * @param service
     */
    public void setService(int service) {
        this.service = service;
    }


    /**
     * Gets the category value for this Data_ticket_into.
     * 
     * @return category
     */
    public java.lang.String getCategory() {
        return category;
    }


    /**
     * Sets the category value for this Data_ticket_into.
     * 
     * @param category
     */
    public void setCategory(java.lang.String category) {
        this.category = category;
    }


    /**
     * Gets the childService value for this Data_ticket_into.
     * 
     * @return childService
     */
    public java.lang.String getChildService() {
        return childService;
    }


    /**
     * Sets the childService value for this Data_ticket_into.
     * 
     * @param childService
     */
    public void setChildService(java.lang.String childService) {
        this.childService = childService;
    }


    /**
     * Gets the robot value for this Data_ticket_into.
     * 
     * @return robot
     */
    public int getRobot() {
        return robot;
    }


    /**
     * Sets the robot value for this Data_ticket_into.
     * 
     * @param robot
     */
    public void setRobot(int robot) {
        this.robot = robot;
    }


    /**
     * Gets the datetime value for this Data_ticket_into.
     * 
     * @return datetime
     */
    public java.lang.String getDatetime() {
        return datetime;
    }


    /**
     * Sets the datetime value for this Data_ticket_into.
     * 
     * @param datetime
     */
    public void setDatetime(java.lang.String datetime) {
        this.datetime = datetime;
    }


    /**
     * Gets the typeAlert value for this Data_ticket_into.
     * 
     * @return typeAlert
     */
    public java.lang.String getTypeAlert() {
        return typeAlert;
    }


    /**
     * Sets the typeAlert value for this Data_ticket_into.
     * 
     * @param typeAlert
     */
    public void setTypeAlert(java.lang.String typeAlert) {
        this.typeAlert = typeAlert;
    }


    /**
     * Gets the message value for this Data_ticket_into.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this Data_ticket_into.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Data_ticket_into)) return false;
        Data_ticket_into other = (Data_ticket_into) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.service == other.getService() &&
            ((this.category==null && other.getCategory()==null) || 
             (this.category!=null &&
              this.category.equals(other.getCategory()))) &&
            ((this.childService==null && other.getChildService()==null) || 
             (this.childService!=null &&
              this.childService.equals(other.getChildService()))) &&
            this.robot == other.getRobot() &&
            ((this.datetime==null && other.getDatetime()==null) || 
             (this.datetime!=null &&
              this.datetime.equals(other.getDatetime()))) &&
            ((this.typeAlert==null && other.getTypeAlert()==null) || 
             (this.typeAlert!=null &&
              this.typeAlert.equals(other.getTypeAlert()))) &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getService();
        if (getCategory() != null) {
            _hashCode += getCategory().hashCode();
        }
        if (getChildService() != null) {
            _hashCode += getChildService().hashCode();
        }
        _hashCode += getRobot();
        if (getDatetime() != null) {
            _hashCode += getDatetime().hashCode();
        }
        if (getTypeAlert() != null) {
            _hashCode += getTypeAlert().hashCode();
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Data_ticket_into.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:wsManageTicket", "data_ticket_into"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("service");
        elemField.setXmlName(new javax.xml.namespace.QName("", "service"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("category");
        elemField.setXmlName(new javax.xml.namespace.QName("", "category"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("childService");
        elemField.setXmlName(new javax.xml.namespace.QName("", "childService"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("robot");
        elemField.setXmlName(new javax.xml.namespace.QName("", "robot"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datetime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datetime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("typeAlert");
        elemField.setXmlName(new javax.xml.namespace.QName("", "typeAlert"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("", "message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

	@Override
	public String toString() {
		return "Data_ticket_into [service=" + service + ", category=" + category + ", childService=" + childService
				+ ", robot=" + robot + ", datetime=" + datetime + ", typeAlert=" + typeAlert + ", message=" + message
				+ "]";
	}
    
    
    

}
