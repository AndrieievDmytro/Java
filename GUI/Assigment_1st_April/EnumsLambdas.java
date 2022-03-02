/////////////Andrieiev Dmytro s21353///////////

import java.util.Arrays;
import java.util.Comparator;

enum Sex {F, M};
enum Size {XS, S, M, L, XL};
enum Country{
    PL{ 
        @Override
            public String toString() {return "Polan";}
    }, 
    NL{
        @Override
            public String toString() {return "Nederland";}
    },
    DE{
        @Override
            public String toString() {return "Deutschland";}
    }
};

public class EnumsLambdas{

    static <T> void printArray(String message, T[] arr){
        System.out.println( message );
        for ( int i = 0; i < arr.length; ++i ){
            System.out.println( arr[i].toString() );
        }
    }
    public static void main(String[] args) {
        Person[] persons = {
            new Person("Max", Sex.M, Size.XL, Country.NL),
            new Person("Jan", Sex.M, Size.S, Country.PL),
            new Person("Eva", Sex.F, Size.XS, Country.NL),
            new Person("Lina", Sex.F, Size.L, Country.DE),
            new Person("Mila", Sex.F, Size.S, Country.DE),
            new Person("Ola", Sex.F, Size.M, Country.PL),
        };


        Comparator <Person> sexThenSize = (p1 ,p2) -> 
        {
            int d = p1.sex.ordinal()-p2.sex.ordinal();
            if( d != 0 ){
                return d;
            }
            return p1.size.ordinal() - p2.size.ordinal();
        } ;
        Arrays.sort(persons, sexThenSize);
        printArray("Persons by sex and then size", persons);
        System.out.println();

        Arrays.sort(persons, (p1 ,p2) -> 
        {
            int d = p1.size.ordinal()-p2.size.ordinal();
            if( d != 0 ){
                return d;
            }
            return p1.name.compareTo( p2.name );
        });
        printArray("Persons by size and then name", persons);
        System.out.println();


        Country[] countries = Country.values();
        Arrays.sort(countries, (p1 ,p2) -> 
        {
            return p2.compareTo( p1 );
        });
        printArray("Countries by name", countries);
    }
            
}

class Person{
    public String name;
    public Sex sex;
    public Size size;
    public Country country;

    Person(String n, Sex s, Size si, Country c ){
        this.name = n;
        this.sex = s;
        this.size = si;
        this.country = c;
    }

    @Override
    public String toString(){
        return name + "(" + sex + ", " + size + ", " + country + ")";        
    }
    
}
