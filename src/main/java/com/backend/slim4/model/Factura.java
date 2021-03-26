/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinfloo.ejemplo01;

import java.math.BigDecimal;

/**
 *
 * @author USUARIO
 */
public class Factura {
    int numero_factura;
    int numero_linea;
    String numero_llave;
    String codigo_articulo;
    String cantidad;
    String precio_articulo;
    String descripcion;
    

    public int getNumero_factura() {
        return numero_factura;
    }

    public void setNumero_factura(int numero_factura) {
        this.numero_factura = numero_factura;
    }

    public int getNumero_linea() {
        return numero_linea;
    }

    public void setNumero_linea(int numero_linea) {
        this.numero_linea = numero_linea;
    }

    public String getNumero_llave() {
        return numero_llave;
    }

    public void setNumero_llave(String numero_llave) {
        this.numero_llave = numero_llave;
    }

    public String getCodigo_articulo() {
        return codigo_articulo;
    }

    public void setCodigo_articulo(String codigo_articulo) {
        this.codigo_articulo = codigo_articulo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio_articulo() {
        return precio_articulo;
    }

    public void setPrecio_articulo(String precio_articulo) {
        this.precio_articulo = precio_articulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
