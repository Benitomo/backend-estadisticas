
package com.backend.slim4.model;

import java.math.BigDecimal;
import java.sql.Date;

public class HistoricalPurchaseOrders {
    int controlId;
    String warehouse;
    String code;
    String number;
    int line;
    int orderTypeNumber;
    Date deliveredDate;
    int deliveredQuantity;
    String supplier;
    String comment;
    String freeText1;
    String freeText2; 
    BigDecimal freeNumber1;
    BigDecimal freeNumber2;
    Date orderedDate;
    Date requestedDate;
    int orderedQuantity;
    int requestedQuantity;
    int confirmedQuantity;
    Date confirmedDate;
    String supplierNumber;
    String supplierName;

    public int getControlId() {
        return controlId;
    }

    public void setControlId(int controlId) {
        this.controlId = controlId;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getOrderTypeNumber() {
        return orderTypeNumber;
    }

    public void setOrderTypeNumber(int orderTypeNumber) {
        this.orderTypeNumber = orderTypeNumber;
    }

    public Date getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(Date deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public int getDeliveredQuantity() {
        return deliveredQuantity;
    }

    public void setDeliveredQuantity(int deliveredQuantity) {
        this.deliveredQuantity = deliveredQuantity;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFreeText1() {
        return freeText1;
    }

    public void setFreeText1(String freeText1) {
        this.freeText1 = freeText1;
    }

    public String getFreeText2() {
        return freeText2;
    }

    public void setFreeText2(String freeText2) {
        this.freeText2 = freeText2;
    }

    public BigDecimal getFreeNumber1() {
        return freeNumber1;
    }

    public void setFreeNumber1(BigDecimal freeNumber1) {
        this.freeNumber1 = freeNumber1;
    }

    public BigDecimal getFreeNumber2() {
        return freeNumber2;
    }

    public void setFreeNumber2(BigDecimal freeNumber2) {
        this.freeNumber2 = freeNumber2;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(int orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(int requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }

    public int getConfirmedQuantity() {
        return confirmedQuantity;
    }

    public void setConfirmedQuantity(int confirmedQuantity) {
        this.confirmedQuantity = confirmedQuantity;
    }

    public Date getConfirmedDate() {
        return confirmedDate;
    }

    public void setConfirmedDate(Date confirmedDate) {
        this.confirmedDate = confirmedDate;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    
    
}
