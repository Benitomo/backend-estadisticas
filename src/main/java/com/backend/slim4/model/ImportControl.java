
package com.backend.slim4.model;


public class ImportControl {
    int controlID;
    int importType;
    String controlTimestamp;
    int controlStatus;

    public int getControlID() {
        return controlID;
    }

    public void setControlID(int controlID) {
        this.controlID = controlID;
    }

    public int getImportType() {
        return importType;
    }

    public void setImportType(int importType) {
        this.importType = importType;
    }

    public String getControlTimestamp() {
        return controlTimestamp;
    }

    public void setControlTimestamp(String controlTimestamp) {
        this.controlTimestamp = controlTimestamp;
    }

    public int getControlStatus() {
        return controlStatus;
    }

    public void setControlStatus(int controlStatus) {
        this.controlStatus = controlStatus;
    }
    
    
}
