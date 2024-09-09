/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

/**
 *
 * @author Elicho
 */
public class Cliente extends Persona {
private String nit;
private int id;
Conexion cn;

    public Cliente(){}
    public Cliente(int id, String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.id = id;
        this.nit = nit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

@Override
    public void crear(){
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            
            cn.abrir_conexion( );
            String query ="INSERT INTO clientes (nit,nombre,apellido,direccion,telefono,fecha_nacimiento) VALUES (?,?,?,?,?,?)";
            parametro = (PreparedStatement) cn.ConexionBD.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_nacimiento());
            int executar = parametro.executeUpdate();
            System.out.println("Ingreso Exitoso... " + Integer.toString(executar));
            
            
            cn.cerrar_conexion( );
            
        
        }catch(SQLException ex){
                System.out.println("Error al crear" + ex.getMessage());
        }
    }
    
@Override
    public DefaultTableModel leer(){
    DefaultTableModel tabla = new DefaultTableModel();
    try{
        cn = new Conexion();
        cn.abrir_conexion();
        
        String query ="SELECT * FROM db_empresa.clientes;";
        ResultSet consulta= cn.ConexionBD.createStatement().executeQuery(query);
        
        String encabezado[] = {"id","Nit","Nommbres","Apellidos","Direccion","Telefono","Nacimiento"};
        tabla.setColumnIdentifiers(encabezado);
        String datos[]= new String[7];
        while (consulta.next()){
        datos[0]=consulta.getString("id_cliente");
        datos[1]=consulta.getString("nit");
        datos[2]=consulta.getString("nombre");
        datos[3]=consulta.getString("apellido");
        datos[4]=consulta.getString("direccion");
        datos[5]=consulta.getString("telefono");
        datos[6]=consulta.getString("fecha_nacimiento");
        
        tabla.addRow(datos);
        
        }
        
        cn.cerrar_conexion();
    
    }catch (SQLException ex){
        cn.cerrar_conexion();
                   System.out.println("Error al leer" + ex.getMessage());
    }
    
    return tabla;
    
    
    }

@Override
public void actualizar(){
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            
            cn.abrir_conexion( );
            String query ="UPDATE clientes SET nit = ?, nombre = ?, apellido = ?, direccion = ?,telefono = ?, fecha_nacimiento = ? WHERE id_cliente = ?;";
            parametro = (PreparedStatement) cn.ConexionBD.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_nacimiento());
            parametro.setInt(7, getId());            
            int executar = parametro.executeUpdate();
            System.out.println("Actualizacion Exitoso... " + Integer.toString(executar));
            
            cn.cerrar_conexion( );
            
        
        }catch(SQLException ex){
                System.out.println("Error al Actualizar" + ex.getMessage());
        }
    
}

@Override
public void borrar(){
try {
            PreparedStatement parametro;
            cn = new Conexion();
            
            cn.abrir_conexion( );
            String query ="DELETE FROM clientes WHERE id_cliente = ?;";
            parametro = (PreparedStatement) cn.ConexionBD.prepareStatement(query);
     /*       parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_nacimiento());
    */      parametro.setInt(1, getId());            
            int executar = parametro.executeUpdate();
            System.out.println("Borrado Exitoso... " + Integer.toString(executar));
            
            cn.cerrar_conexion( );
            
        
        }catch(SQLException ex){
                System.out.println("Error al Borrar" + ex.getMessage());
        }
}

    
//crud
/*
@Override
protected String[] crear(){
try{
//codigo fuente
String datos[]= new String[6];
datos[0]=getNit();
datos[1]=getNombres();
datos[2]=getApellidos();
datos[3]=getDireccion();
datos[4]=getTelefono();
datos[5]= this.getFecha_nacimiento();
return datos;
} catch (Exception ex){
    System.out.println("Algo salido mal" + ex.getMessage());
    return null;
    }
}
*/
    
}
