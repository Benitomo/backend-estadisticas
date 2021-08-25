package com.backend.estadisticas.serviceImp;

import com.backend.estadisticas.model.Estadisticas;
import com.backend.estadisticas.service.EstadisticasService;
import com.backend.estadisticas.GetConnection;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EstadisticasServiceImp implements EstadisticasService{
    
    @Override
    public ResponseEntity estadisticasSelect() {
        
        Estadisticas estadistica = new Estadisticas();
        // Mensaje de respuesta
        String tituloResp  = "Aqui Esta";
        String mensajeResp = "El Problema";

        try{
            // Informix
            Connection cnt = GetConnection.informix("impocali");
            Statement stmt = cnt.createStatement();

            System.out.println("Conexión realizada con éxito");

            String sql = "SELECT seg.segm_code_segmento, seg.segm_descripcion, sseg.tpro_tipo_articulo, sseg.tpro_descr FROM invsegm seg INNER JOIN invtpro sseg ON (seg.segm_code_segmento = sseg.tpro_segmento)";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("codSegmento: " + rs.getString(1) + " desSegmento: " + rs.getString(2) + " tipoArticulo: " + rs.getString(3) + " desTipoArticulo: " + rs.getString(4));

                estadistica.codSegmento = rs.getString( "segm_code_segmento" );
                estadistica.desSegmento = rs.getString( "segm_descripcion" );
                estadistica.tipoArticulo = rs.getString( "tpro_tipo_articulo" );
                estadistica.desTipoArticulo = rs.getString( "tpro_descr" );
            }
            cnt.close();
        }

        catch (SQLException ex) {
            Logger.getLogger(EstadisticasServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         HashMap<String, String> map = new HashMap<>();
         map.put(tituloResp, mensajeResp);
         return new ResponseEntity(estadistica, HttpStatus.CONFLICT);
         //return objEstadisticas;
        
    }
}