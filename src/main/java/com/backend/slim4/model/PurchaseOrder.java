/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.slim4.model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author USUARIO
 */
public class PurchaseOrder {
  int controlId;
  String warehousecode;
  String code;
  int number;
  Date deliveryDate;
  int openQuantity;
  String supplier;
  String comment;
  int originalQuantity;
  int suppliedQuantity;
  String freeText1;
  String freeText2;
  BigDecimal freeNumber1;
  BigDecimal freeNumber2;
  int orderTypeNumber;
  int line;
  int excludeSetting;
  Date excludeDate;
  int excludeFromAM;
  String supplierNumber;
  String supplierName;

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getOpenQuantity() {
        return openQuantity;
    }

    public void setOpenQuantity(int openQuantity) {
        this.openQuantity = openQuantity;
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

    public int getOrderTypeNumber() {
        return orderTypeNumber;
    }

    public void setOrderTypeNumber(int orderTypeNumber) {
        this.orderTypeNumber = orderTypeNumber;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getExcludeSetting() {
        return excludeSetting;
    }

    public void setExcludeSetting(int excludeSetting) {
        this.excludeSetting = excludeSetting;
    }

    public Date getExcludeDate() {
        return excludeDate;
    }

    public void setExcludeDate(Date excludeDate) {
        this.excludeDate = excludeDate;
    }

    public int getExcludeFromAM() {
        return excludeFromAM;
    }

    public void setExcludeFromAM(int excludeFromAM) {
        this.excludeFromAM = excludeFromAM;
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
