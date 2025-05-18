package org.activemq.activemqpoc.model;

import java.io.Serial;
import java.io.Serializable;

public class SystemMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String orderID;
    private String status;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SystemMessage {orderID=" + orderID + '\'' +
                ", status=" + status + '\'' +
                "}";
    }
}
