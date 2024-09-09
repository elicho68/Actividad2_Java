/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Elicho
 */
public class Empleado extends Persona {
private String codigo;
private int id_empleados,id_puesto;    
Conexion cn;

    public Empleado(){
    }
    public Empleado(String codigo, int id_empleados, int id_puesto, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.codigo = codigo;
        this.id_empleados = id_empleados;
        this.id_puesto = id_puesto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId_empleados() {
        return id_empleados;
    }

    public void setId_empleados(int id_empleados) {
        this.id_empleados = id_empleados;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

@Override
 public DefaultTableModel leer() {
   DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();

            String query = "SELECT * FROM db_empresa.empleados;";
            ResultSet consulta = cn.ConexionBD.createStatement().executeQuery(query);

            String encabezado[] = {"ID", "Codigo", "Nombres", "Apellidos", "Direccion", "Teléfono","nacimiento", "Puesto"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[8];
            while (consulta.next()) {
                datos[0] = consulta.getString("id_empleados");
                datos[1] = consulta.getString("codigo");
                datos[2] = consulta.getString("nombres");
                datos[3] = consulta.getString("apellidos");
                datos[4] = consulta.getString("direccion");
                datos[5] = consulta.getString("telefono");
                datos[6] = consulta.getString("fecha_nacimiento");                
                datos[7] = consulta.getString("id_puesto");
                tabla.addRow(datos);
            }

            cn.cerrar_conexion();

        } catch (SQLException ex) {
//            cn.cerrar_conexion();
            System.out.println("Error al leer empleados: " + ex.getMessage());
        }

        return tabla;  
 }

public DefaultComboBoxModel leer_puesto(){
    DefaultComboBoxModel  combo = new DefaultComboBoxModel ();
    try{
       cn = new Conexion ();
       cn.abrir_conexion();
       String query;
       query = "SELECT id_puesto as id,puesto from puestos";
       ResultSet consulta =  cn.ConexionBD.createStatement().executeQuery(query); //cn.conexionBD.createStatement().executeQuery(query);
       combo.addElement("0) Elija Puesto");
                  while (consulta.next())
                    {            
                      combo.addElement(consulta.getString("id")+") "+consulta.getString("puesto"));
                    }
              cn.cerrar_conexion();
              
       
    }catch(SQLException ex){
        System.out.println("Error: " + ex.getMessage() );
    }
    return combo;
    } 
 
@Override
public void crear() {
    try {
            PreparedStatement parametro;
            cn = new Conexion();

            cn.abrir_conexion();
            String query = "INSERT INTO empleados (codigo, nombres, apellidos, direccion, telefono, fecha_nacimiento, id_puesto) VALUES (?,?,?,?,?,?,?);";
            parametro = (PreparedStatement) cn.ConexionBD.prepareStatement(query);
            parametro.setString(1, getCodigo());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_nacimiento());
            parametro.setInt(7, getId_puesto());
            int executar = parametro.executeUpdate();
            System.out.println("Ingreso Exitoso... " + Integer.toString(executar));

            cn.cerrar_conexion();

            
        } catch (SQLException ex) {
            System.out.println("Error al crear empleado: " + ex.getMessage());
        }
}    
 
 
@Override
 public void actualizar() {
   try {
            PreparedStatement parametro;
            cn = new Conexion();

            cn.abrir_conexion();
            String query = "UPDATE empleados SET codigo = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?,  id_puesto = ? WHERE id_empleados = ?;";
            parametro = (PreparedStatement) cn.ConexionBD.prepareStatement(query);
            parametro.setString(1, getCodigo());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_nacimiento());
            parametro.setInt(7, getId_puesto());
            parametro.setInt(8, getId_empleados());            
            int executar = parametro.executeUpdate();
            System.out.println("Actualización Exitosa... " + Integer.toString(executar));

            cn.cerrar_conexion();

        } catch (SQLException ex) {
            System.out.println("Error al actualizar empleado: " + ex.getMessage());
        }  
     
 }

@Override
public void borrar() {
try {
            PreparedStatement parametro;
            cn = new Conexion();

            cn.abrir_conexion();
            String query = "DELETE FROM empleados WHERE id_empleados = ?;";
            parametro = cn.ConexionBD.prepareStatement(query);
            parametro.setInt(1, getId_empleados());
            int executar = parametro.executeUpdate();
            System.out.println("Borrado Exitoso... " + Integer.toString(executar));

            cn.cerrar_conexion();

        } catch (SQLException ex) {
            System.out.println("Error al borrar empleado: " + ex.getMessage());
        }
    }


}







