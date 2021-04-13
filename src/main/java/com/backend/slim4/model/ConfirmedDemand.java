
package com.backend.slim4.model;

import java.math.BigDecimal;
import java.sql.Date;

public class ConfirmedDemand {
  int controlId;
  String warehousecode;
  String code;
  String number;
  Date requiredDate;
  int requiredQuantity;
  int exceptionLevel;
  String orderType;
  String customerDetails;
  String productionDetails;
  String comment;
  int originalQuantity;
  int suppliedQuantity;
  String freeText1;
  String freeText2;
  BigDecimal freeNumber1;
  BigDecimal freeNumber2;
  int type;
  int line;

    public int getControlId() {
        return controlId;
    }

    public void setControlId(int controlId) {
        this.controlId = controlId;
    }

    public String getWarehousecode() {
        return warehousecode;
    }

    public void setWarehousecode(String warehousecode) {
        this.warehousecode = warehousecode;
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

    public Date getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Date requiredDate) {
        this.requiredDate = requiredDate;
    }

    public int getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setRequiredQuantity(int requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }

    public int getExceptionLevel() {
        return exceptionLevel;
    }

    public void setExceptionLevel(int exceptionLevel) {
        this.exceptionLevel = exceptionLevel;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(String customerDetails) {
        this.customerDetails = customerDetails;
    }

    public String getProductionDetails() {
        return productionDetails;
    }

    public void setProductionDetails(String productionDetails) {
        this.productionDetails = productionDetails;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getOriginalQuantity() {
        return originalQuantity;
    }

    public void setOriginalQuantity(int originalQuantity) {
        this.originalQuantity = originalQuantity;
    }

    public int getSuppliedQuantity() {
        return suppliedQuantity;
    }

    public void setSuppliedQuantity(int suppliedQuantity) {
        this.suppliedQuantity = suppliedQuantity;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
  
  
}
