
package com.backend.slim4.model;

public class ArticleFilter {
    
  int controlId;
  String warehousecode;
  String code;

    public int getControlId() {
        return controlId;
    }

    public void setControlId(int controlID) {
        this.controlId = controlID;
    }

    public String getWarehousecode() {
        return warehousecode;
    }

    public void setWarehousecode(String warehouse_code) {
        this.warehousecode = warehouse_code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
  
  
    
}
