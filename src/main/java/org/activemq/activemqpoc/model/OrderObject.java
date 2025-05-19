package org.activemq.activemqpoc.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

public class OrderObject implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String orderID;
    private String orderStatus;
    private String messageStatus;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String status) {
        this.orderStatus = status;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    @Override
    public String toString() {
        return "SystemMessage {orderID=" + orderID + '\'' +
                ", orderStatus=" + orderStatus + '\'' +
                ", messageStatus=" + messageStatus + '\'' +
                "}";
    }
}
