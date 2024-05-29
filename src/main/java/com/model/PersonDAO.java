package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PersonDAO {
    private PreparedStatement pst;
    private ResultSet rs;

    public boolean updatePerson (Person person){


        boolean state = false;
        Connection connect = null;
        try {
            
            // Hacer coneccion con la base de datos
            connect = ConnectionPool.getInstance().getConnection();
    
            if(connect != null){
    
               String sql = "UPDATE persona SET id_type=?, id_number=?, name=?, last_Name=?, phone=?,email=?, date_of_birth=?, gender=? WHERE id=?";
               
               pst = connect.prepareStatement(sql);
               pst.setString(1, person.getIdType());
               pst.setInt(2, person.getIdNumber());
               pst.setString(3, person.getName());
               pst.setString(4, person.getLastName());
               pst.setString(5, person.getPhone());
               pst.setString(6, person.getEmail());
               pst.setString(7, person.getDateBirth());
               pst.setString(8, person.getGender());
               pst.setInt(9, person.getId());
    
               int res = pst.executeUpdate();
    
               state = res > 0;
             
            } else{
                System.out.println("Conexion fallida");
            }
    
        } catch (Exception ex){
    
            System.out.println(ex.getMessage());
    
        } finally{
            try {
                ConnectionPool.getInstance().closeConnection(connect);
            } catch (Exception e) {
                System.out.println(e.getMessage());
    
           }
        }
    
        return state;
    
    }
    public boolean addPerson(Person person){
        // estado de la conecion
        boolean state = false;
        Connection connect = null;
        try {
            
            // Hacer coneccion con la base de datos
            connect = ConnectionPool.getInstance().getConnection();

            if(connect != null){
                // Preparar inserssercion
                
                String sql = "INSERT INTO persona(id_number,id_type,name,last_name,date_of_birth,gender,email,phone, state) VALUES(?,?,?,?,?,?,?,?)";

                pst = connect.prepareStatement(sql);
                pst.setInt(1, person.getIdNumber());
                pst.setString(2, person.getIdType());
                pst.setString(3, person.getName());
                pst.setString(4, person.getLastName());
                pst.setString(5,  person.getDateBirth());
                pst.setString(6, person.getGender());
                pst.setString(7, person.getEmail());
                pst.setString(8, person.getPhone());
                pst.setInt(9, 1);

                int res = pst.executeUpdate();
                state = res > 0;
            } else{
                System.out.println("Conexion fallida");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally{
            try {
                ConnectionPool.getInstance().closeConnection(connect);
            } catch (Exception e) {
                System.out.println(e.getMessage());// TODO: handle exception
            }
        }


        return state; 
    }

  
    
public ArrayList<Person> selectPerson(String filter, ArrayList<String>data){


ArrayList<Person> list =new ArrayList<>();
Person person;

Connection connect = null;
try {
    
    // Hacer coneccion con la base de datos
    connect = ConnectionPool.getInstance().getConnection();

    if(connect != null){

        
    String sql="";

    switch (filter){
        case "Name":
        sql = "SELECT *FROM persona WHERE name REGEXP ? AND last name REGEXP ?AND state=1"; 
        pst = connect.prepareStatement(sql);
        pst.setString(1, data.get (0));
        pst.setString(2,data.get(0));
     break;

        case "IDNumber":
        sql = "SELECT *FROM persona WHERE name REGEXP ? AND last name REGEXP ?AND state=1"; 
        pst = connect.prepareStatement(sql);
        pst.setString(1, data.get (0));
        pst.setInt(2,Integer.parseInt(data.get(1)));
     break;

        default:
        sql ="SELECT * FROM persona WHERE 1 AND state=1";
        pst = connect.prepareStatement(sql);
        break;
    }

    rs = pst.executeQuery();

    while(rs.next()){

        person = new Person();

        person.setId(rs.getInt("id"));
        person.setIdType(rs.getString("Id_type"));
        person.setIdNumber(rs.getInt("id_number"));
        person.setName(rs.getString("Name"));
        person.setLastName(rs.getString("Last_name"));
        person.setEmail(rs.getString("Email"));
        person.setPhone(rs.getString("Phone"));
        person.setDateBirth(rs.getString("date_of_birth"));
        person.setGender(rs.getString("gender"));

        list.add(person);



        
    }
        // Preparar inserssercion
        
           System.out.println("Conexion fallida");
    }
} catch (Exception e) {
    System.out.println(e.getMessage());
} finally{
    try {
        ConnectionPool.getInstance().closeConnection(connect);
    } catch (Exception e) {
        System.out.println(e.getMessage());// TODO: handle exception
    }
}
return list;

}
    public boolean deletePerson (int id){

    boolean state = false;
    Connection connect = null;

    try{

        connect = ConnectionPool. getInstance().getConnection();

        if(connect != null){

            String sql = "UPDATE persona SET state=? WHERE id=?";

            pst = connect.prepareStatement(sql);
            pst.setInt(1, 0);
            pst.setInt(2, id);

            int res = pst.executeUpdate();

            state = res > 0;


        }else{

            

    }

      
} catch( Exception e) {
    System.out.println(e.getMessage());
}finally {
    try {
        ConnectionPool.getInstance().closeConnection(connect);
    } catch (Exception e) {
        System.out.println(e.getMessage());// TODO: handle exception
    }
}

return true;
}
}




















