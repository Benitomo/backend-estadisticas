
package com.backend.slim4.service;

import com.backend.slim4.model.ImportControl;
import java.sql.Statement;


public interface ImportControlService {
    int importInterface();
    int insert(Statement stmt, int interfaceID, ImportControl control);
}
