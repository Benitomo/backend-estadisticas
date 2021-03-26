/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinfloo.ejemplo01;

import java.io.Serializable;

/**
 *
 * @author USUARIO
 */
public class Pagina implements Serializable{
    
    String nombre_archivo;
    int pagina;
    String parametro;
    
    public Pagina(String n, int p, String parametro)
    {
        this.nombre_archivo = n;
        this.pagina = p;
        this.parametro=parametro;
    }

    public String getNombre_archivo() {
        return nombre_archivo;
    }

    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }
    
    
}
