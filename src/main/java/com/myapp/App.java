package com.myapp;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.model.Person;
import com.model.PersonDAO;

public class App {
    public static void main(String[] args) throws Exception {
        
        PersonDAO model = new PersonDAO();
        int menu = Integer.parseInt(JOptionPane.showInputDialog("CRUD\n" +
                                                                 "1-insert DATA\n"+
                                                                 "2-Select DATA\n"+
                                                                 "3-Update DATA\n"+
                                                                 "4-DELETE DATA\n"+
                                                                 "5-exit"
                                                                 ));
        Person person = new Person();
        switch(menu){
            case 1:
            person.setIdNumber(12345);
        person.setIdType("cedula de ciudadania");
        person.setName("Diana Marcela");
        person.setLastName("Posada");
        person.setDateBirth("1981/03/29");
        person.setEmail("chelita.29@hotmail.com");
        person.setPhone("3147894811");
        person.setGender("Femenino");

        if(model.addPerson(person)){
            System.out.println("Guardado");
        }else{
            System.out.print("No Guardado");
        }

             break;

        

             case 2:
             ArrayList<String> data = new ArrayList<>();
             data.add("Cedula de Ciudadania");
             data.add("1345");
             ArrayList<Person> list = model.selectPerson("IDNumber", data);
 
             for(int i=0; i<list.size(); i++){
 
                 System.out.println(list.get(i).getIdNumber() +" "+ list.get(i).getName()+" "+ list.get(i).getLastName());
             }
 
 
          break;
          
          case 3:
          
          person = new Person();
 
          person.setId (3);
          person.setIdNumber(12345);
          person.setIdType("cedula de ciudadania");
          person.setName("Diana Maria");
          person.setLastName("Posada");
          person.setDateBirth("1981/03/29");
          person.setEmail("maria.29@gmail.com");
          person.setPhone("3103924014");
          person.setGender("Femenino");
  
          if(model.updatePerson(person)){
              System.out.println("Los datos han sido modificados de manera exitosa");
          }else{
              System.out.print("No se pudo modificar la información");
          }
        
 
 
    break;
 
    case 4:

         int confirm = JOptionPane.showConfirmDialog(null, "¿Esta seguro de realizar este proceso?\n"
                                                                          +"Todos los datos se perderan");

        if(confirm == JOptionPane.YES_OPTION){
                if(model.deletePerson(4)){
                    System.out.println("Los datos han sido eliminados de manera exitosa");
                }else{
                    System.out.println("No se pudo eliminar la información");
                }
            }

        break;   
         
        
    }
    
}
}




























































































