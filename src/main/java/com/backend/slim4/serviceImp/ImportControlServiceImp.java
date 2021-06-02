
package com.backend.slim4.serviceImp;

import com.backend.slim4.model.ImportControl;
import com.backend.slim4.service.ImportControlService;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
       int result = 0;
       // Obtener fecha y hora actual
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Date myDate = new Date();
        System.out.print("\n Timestamp: " + timestamp + " \n");
        // Validar si existe un registro en ImportControl respecto a la interface, dependiendo de que exista el query cambia
        boolean validacion = existeRegistro(stmt, interfaceID, control);
        String sql = validacion == true?
                "UPDATE [slim4interface_test].[dbo].[ImportControl] "
                + "SET controlStatus = " + 1 
                + ", controlTimestamp = '" + new SimpleDateFormat("dd/MM/yyyy").format(myDate)
                + "' WHERE importType = " + interfaceID
        :
                "SET NOCOUNT ON INSERT INTO [slim4interface_test].[dbo].[ImportControl]"
                + "(controlId, importType, controlTimestamp, controlStatus) "
                + "VALUES (" + control.getControlID() + ", " + control.getImportType()+ ", '" + timestamp.toString() + "', " + 1 + ")";
        
        
        try {
            if(validacion == true)
                 stmt.execute(sql);
            else
                stmt.executeUpdate(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(ImportControlServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public String getFormatoFecha(String fecha) throws ParseException {
        String convertion = null;
        if(fecha != null){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdf2 = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
            convertion = sdf.format(sdf2.parse(fecha));
        }

        return convertion;
    }
   
   public boolean existeRegistro(Statement stmt, int interfaceID, ImportControl control){
        String sql = "SET NOCOUNT ON SELECT COUNT (*) FROM [slim4interface_test].[dbo].[ImportControl] WHERE importType = " + control.getImportType();
        boolean result = false;
        try {
            result = stmt.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ImportControlServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
   
}
