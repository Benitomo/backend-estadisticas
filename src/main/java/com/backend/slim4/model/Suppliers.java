
package com.backend.slim4.model;

import java.math.BigDecimal;
import java.sql.Date;


public class Suppliers {
    int controlId;
    String warehouse;
    String code;
    String supplierNumber;
    String supplierName;
    int primarySupplier;
    int preference;
    BigDecimal leadTime;
    BigDecimal reviewTime;
    BigDecimal buyingPrice; 
    String currencyCode;
    int MOQ;
    int IOQ;
    int EOQ;
    BigDecimal supplierReliability;
    int supplierRealibilityType;
    String supplierArticleCode;
    int availableInventory;
    BigDecimal desiredSplit;
    int suppliedQuantity;
    Date orderFromDate;
    Date orderToDate;
    int logisticUnit1;
    int logisticUnit2;
    int logisticUnit3;
    int logisticUnit4;
    int logisticUnit5;
    int logisticUnit6;
    String uD1;
    String uD2;
    String uD3;
    String uD4;
    String uD5;

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

    public int getPrimarySupplier() {
        return primarySupplier;
    }

    public void setPrimarySupplier(int primarySupplier) {
        this.primarySupplier = primarySupplier;
    }

    public int getPreference() {
        return preference;
    }

    public void setPreference(int preference) {
        this.preference = preference;
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

    public BigDecimal getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(BigDecimal buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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

    public BigDecimal getSupplierReliability() {
        return supplierReliability;
    }

    public void setSupplierReliability(BigDecimal supplierReliability) {
        this.supplierReliability = supplierReliability;
    }

    public int getSupplierRealibilityType() {
        return supplierRealibilityType;
    }

    public void setSupplierRealibilityType(int supplierRealibilityType) {
        this.supplierRealibilityType = supplierRealibilityType;
    }

    public String getSupplierArticleCode() {
        return supplierArticleCode;
    }

    public void setSupplierArticleCode(String supplierArticleCode) {
        this.supplierArticleCode = supplierArticleCode;
    }

    public int getAvailableInventory() {
        return availableInventory;
    }

    public void setAvailableInventory(int availableInventory) {
        this.availableInventory = availableInventory;
    }

    public BigDecimal getDesiredSplit() {
        return desiredSplit;
    }

    public void setDesiredSplit(BigDecimal desiredSplit) {
        this.desiredSplit = desiredSplit;
    }

    public int getSuppliedQuantity() {
        return suppliedQuantity;
    }

    public void setSuppliedQuantity(int suppliedQuantity) {
        this.suppliedQuantity = suppliedQuantity;
    }

    public Date getOrderFromDate() {
        return orderFromDate;
    }

    public void setOrderFromDate(Date orderFromDate) {
        this.orderFromDate = orderFromDate;
    }

    public Date getOrderToDate() {
        return orderToDate;
    }

    public void setOrderToDate(Date orderToDate) {
        this.orderToDate = orderToDate;
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

    public String getuD5() {
        return uD5;
    }

    public void setuD5(String uD5) {
        this.uD5 = uD5;
    }
    
    
}
