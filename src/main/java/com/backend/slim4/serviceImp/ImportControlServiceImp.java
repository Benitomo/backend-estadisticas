
package com.backend.slim4.serviceImp;

import com.backend.slim4.model.ImportControl;
import com.backend.slim4.service.ImportControlService;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class ImportControlServiceImp implements ImportControlService{
   
   @Override
   public int importInterface() {
       return 0;
   }
   
   @Override
   public int insert(Statement stmt, int interfaceID, ImportControl control){
        System.out.print("\n Entré a insertar registro en ImportControl en Sql Server \n");
        String sql = "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[ImportControl](controlId, importType, controlTimestamp, controlStatus) "
                + "VALUES (" + control.getControlID() + ", " + control.getImportType()+ ", '" + control.getControlTimestamp()+ "', " + control.getControlStatus()+ ")";
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ImportControlServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
