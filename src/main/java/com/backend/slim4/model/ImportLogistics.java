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
public class ImportLogistics {
  int controlId;
  String warehousecode;
  String code;
  String supplierNumber;
  String supplierName;
  BigDecimal leadTime;
  BigDecimal reviewTime;
  BigDecimal supplierReliability;
  int supplierReliabilityType;
  String stockedItem;
  int MOQ;
  int IOQ;
  int EOQ;
  int logisticUnit1;
  int logisticUnit2;
  int logisticUnit3;
  int logisticUnit4;
  int logisticUnit5;
  int logisticUnit6;
  int insuranceInventory;
  int insuranceInventoryType;
  BigDecimal targetServiceLevel;
  String plcArticleCode;
  Date plcDate;
  BigDecimal plcPerc;
  String abcClass;
  BigDecimal buyingPrice;
  int MSQ;
  int ISQ;

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

    public BigDecimal getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(BigDecimal leadTime) {
        this.leadTime = leadTime;
    }

    public BigDecimal getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(BigDecimal reviewTime) {
        this.reviewTime = reviewTime;
    }

    public BigDecimal getSupplierReliability() {
        return supplierReliability;
    }

    public void setSupplierReliability(BigDecimal supplierReliability) {
        this.supplierReliability = supplierReliability;
    }

    public int getSupplierReliabilityType() {
        return supplierReliabilityType;
    }

    public void setSupplierReliabilityType(int supplierReliabilityType) {
        this.supplierReliabilityType = supplierReliabilityType;
    }

    public String getStockedItem() {
        return stockedItem;
    }

    public void setStockedItem(String stockedItem) {
        this.stockedItem = stockedItem;
    }

    public int getMOQ() {
        return MOQ;
    }

    public void setMOQ(int MOQ) {
        this.MOQ = MOQ;
    }

    public int getIOQ() {
        return IOQ;
    }

    public void setIOQ(int IOQ) {
        this.IOQ = IOQ;
    }

    public int getEOQ() {
        return EOQ;
    }

    public void setEOQ(int EOQ) {
        this.EOQ = EOQ;
    }

    public int getLogisticUnit1() {
        return logisticUnit1;
    }

    public void setLogisticUnit1(int logisticUnit1) {
        this.logisticUnit1 = logisticUnit1;
    }

    public int getLogisticUnit2() {
        return logisticUnit2;
    }

    public void setLogisticUnit2(int logisticUnit2) {
        this.logisticUnit2 = logisticUnit2;
    }

    public int getLogisticUnit3() {
        return logisticUnit3;
    }

    public void setLogisticUnit3(int logisticUnit3) {
        this.logisticUnit3 = logisticUnit3;
    }

    public int getLogisticUnit4() {
        return logisticUnit4;
    }

    public void setLogisticUnit4(int logisticUnit4) {
        this.logisticUnit4 = logisticUnit4;
    }

    public int getLogisticUnit5() {
        return logisticUnit5;
    }

    public void setLogisticUnit5(int logisticUnit5) {
        this.logisticUnit5 = logisticUnit5;
    }

    public int getLogisticUnit6() {
        return logisticUnit6;
    }

    public void setLogisticUnit6(int logisticUnit6) {
        this.logisticUnit6 = logisticUnit6;
    }

    public int getInsuranceInventoryType() {
        return insuranceInventoryType;
    }

    public void setInsuranceInventoryType(int insuranceInventoryType) {
        this.insuranceInventoryType = insuranceInventoryType;
    }
    
    

    public int getInsuranceInventory() {
        return insuranceInventory;
    }

    public void setInsuranceInventory(int insuranceInventory) {
        this.insuranceInventory = insuranceInventory;
    }

    public BigDecimal getTargetServiceLevel() {
        return targetServiceLevel;
    }

    public void setTargetServiceLevel(BigDecimal targetServiceLevel) {
        this.targetServiceLevel = targetServiceLevel;
    }

    public String getPlcArticleCode() {
        return plcArticleCode;
    }

    public void setPlcArticleCode(String plcArticleCode) {
        this.plcArticleCode = plcArticleCode;
    }

    public Date getPlcDate() {
        return plcDate;
    }

    public void setPlcDate(Date plcDate) {
        this.plcDate = plcDate;
    }

    public BigDecimal getPlcPerc() {
        return plcPerc;
    }

    public void setPlcPerc(BigDecimal plcPerc) {
        this.plcPerc = plcPerc;
    }

    public String getAbcClass() {
        return abcClass;
    }

    public void setAbcClass(String abcClass) {
        this.abcClass = abcClass;
    }

    public BigDecimal getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(BigDecimal buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public int getMSQ() {
        return MSQ;
    }

    public void setMSQ(int MSQ) {
        this.MSQ = MSQ;
    }

    public int getISQ() {
        return ISQ;
    }

    public void setISQ(int ISQ) {
        this.ISQ = ISQ;
    }
  
  
}
