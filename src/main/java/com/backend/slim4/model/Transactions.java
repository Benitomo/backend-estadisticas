
package com.backend.slim4.model;

import java.math.BigDecimal;
import java.sql.Date;


public class Transactions {
    int controlId;
    String transactionNumber;
    String transactionType;
    String transactionName;
    String transactionStatus;
    String warehouse;
    String code;
    Date issueDate;
    Date confirmedDate;
    Date requestedDate;
    BigDecimal issueQuantity;
    String lineNumber;
    BigDecimal confirmedQuantity;
    BigDecimal requestedQuantity;
    String customerNumber;
    String customerType;
    String customerName;
    BigDecimal salesPrice;
    String deliveryLocation;
    String supplierNumber;
    String supplierType;
    String supplierName;
    BigDecimal buyingPrice;
    String supplyingLocation;
    BigDecimal conversionFactor;
    String uD1;
    String uD2;
    String uD3;
    String uD4;
    String TransactionIssueTime;

    public int getControlId() {
        return controlId;
    }

    public void setControlId(int controlID) {
        this.controlId = controlID;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
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

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getConfirmedDate() {
        return confirmedDate;
    }

    public void setConfirmedDate(Date confirmedDate) {
        this.confirmedDate = confirmedDate;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    public BigDecimal getIssueQuantity() {
        return issueQuantity;
    }

    public void setIssueQuantity(BigDecimal issueQuantity) {
        this.issueQuantity = issueQuantity;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public BigDecimal getConfirmedQuantity() {
        return confirmedQuantity;
    }

    public void setConfirmedQuantity(BigDecimal confirmedQuantity) {
        this.confirmedQuantity = confirmedQuantity;
    }

    public BigDecimal getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(BigDecimal requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public BigDecimal getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(BigDecimal buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public String getSupplyingLocation() {
        return supplyingLocation;
    }

    public void setSupplyingLocation(String supplyingLocation) {
        this.supplyingLocation = supplyingLocation;
    }

    public BigDecimal getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(BigDecimal conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public String getuD1() {
        return uD1;
    }

    public void setuD1(String uD1) {
        this.uD1 = uD1;
    }

    public String getuD2() {
        return uD2;
    }

    public void setuD2(String uD2) {
        this.uD2 = uD2;
    }

    public String getuD3() {
        return uD3;
    }

    public void setuD3(String uD3) {
        this.uD3 = uD3;
    }

    public String getuD4() {
        return uD4;
    }

    public void setuD4(String uD4) {
        this.uD4 = uD4;
    }

    public String getTransactionIssueTime() {
        return TransactionIssueTime;
    }

    public void setTransactionIssueTime(String TransactionIssueTime) {
        this.TransactionIssueTime = TransactionIssueTime;
    }
    
    
}
