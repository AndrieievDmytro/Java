///////////////////Andrieiev Dmytro s21353///////////////////

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException; 
import java.awt.Color;


public class Main{
    public static void main(String[] args) throws Exception {
        
        List <Person> persons = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("PersCars.txt"))) {  
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\s+");
                Person p;
                if ( data.length > 2 ){
                    p = new Person(data[0],  Integer.parseInt( data[1] ), new Car( data[2], Integer.parseInt( data[3] ), Integer.parseInt( data[4] ), Integer.parseInt( data[5] ) ) );
                }else{
                    p = new Person(data[0],  Integer.parseInt( data[1] ) );
                }
                persons.add(p);
                System.out.println( p ); 
                System.out.println( "" ); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( "-----------------------------------------------------------" ); 
        System.out.println("Test of the functions");
        System.out.println();
        System.out.println("Find all cars:");
        System.out.println( Person.findAllCars(persons) );
        System.out.println();
        System.out.println("Find owners:");
        System.out.println( Person.findOwners(persons, "Mercedes") );
        System.out.println();
        System.err.println("Color of the Car: ");
        System.out.println( Person.findColor(persons, "Alice", 1993) );

    }
}

class Person{

    private String name;
    private int yearOfBirth;
    private Car car;
        
    Person( String n, int year, Car c ){
        this.name = n;
        this.yearOfBirth = year;
        this.car = c;
    }

    Person( String n, int year ){
        this.name = n;
        this.yearOfBirth = year;
        this.car = null;
    }

    public Car getCar(){
        return this.car;
    }

    public String getName(){
        return this.name;
    }

    public int getYearOfBirthday(){
        return this.yearOfBirth;
    }

    public boolean hasCar(){
        return (this.car != null);
    }

    
    public static List <Car> findAllCars( List<Person>list ){
        List <Car> c = new ArrayList<>();
        Iterator <Person> i = list.iterator();
        while ( i.hasNext() ){
            Person p = i.next();
            if ( p.hasCar() ){
                c.add( p.getCar() );
            }
        }
        return c;
    }

    public static List <Person> findOwners(List<Person> list, String carName){
        List <Person> np = new ArrayList<>();
        Iterator <Person> i = list.iterator();
        while ( i.hasNext() ){
            Person p = i.next();
            if ( p.hasCar() && p.getCar().getName().equals( carName ) ){
                np.add( p );
            }
        }
        return np;
    } 
 

    // Method findColor returns the last person founded in the list with the mached name and year of birthday
    public static Color findColor(List<Person>list, String name, int year){
        Color c = null;
        Iterator <Person> i = list.iterator();
        while ( i.hasNext() ){
            Person p = i.next();
            if ( p.getName().equals( name ) && p.getYearOfBirthday() == year ){
                c = p.getCar().getColor();
                // return c;   //  In case the first Person is matched in the list.
            }
        }
        return c; 
    }

    public String toString() {
        return this.name + " year of birth " + this.yearOfBirth + ( this.hasCar() ? " has a car: " + this.car.toString() : " has no car" );
           
    }
}

class Car{   
    private String name;
    private Color color;
    
    Car( String name, int r, int g, int b ){
            this.name = name;
            this.color = new Color ( r, g, b );
    }

    public String getName(){
        return this.name;
    }

    public Color getColor(){
        return this.color;
    }

    public String toString() {
        return this.name + " of color " + this.color;
           
    }
}




    
   