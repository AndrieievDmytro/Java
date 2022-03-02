
public class TestClass{
    public static void main (String[] args) {
        Person john = new Person("John");
        john.buys("Ford", 20000)
            .buys("Opel", 16000)
            .buys("Fiat", 12000)
            .buys("BMW", 50000)
            .showCars();
        System.out.println();

        john.showCarsRev();
        System.out.println();

        System.out.println("Total price of " +
                john.getName() + "'s cars: " +
                john.getTotalPrice());
        System.out.println("Does " + john.getName() +
                " have a ford? " + john.hasCarString("ford"));
        System.out.println("Does " + john.getName() +
                " have a bmw?  " + john.hasCarString("bmw"));
        System.out.println(john.getName() + "'s most " +
                "expensive car is " + john.mostExpensive());
        System.out.println( (int) 'A' + 32 );
        System.out.println( (int) 'a' );
        String test = "SoMe WoRd";
        for ( int i = 0; i < test.length(); i++ ){
            System.out.print( (int)'A' < test.charAt(i) && test.charAt(i) < 97 ? (char)(test.charAt(i) + 32) : (char)(test.charAt(i)) );
        }
        System.out.println();

        /////////////TASK3////////////////

        Task t2 = new Task("Wash the dishes!");
        Task t1 = new Task("Walk the dog!",t2);
        t2.setNextTask(new Task("Clean the room!"));
        Task head = new Task("Get rest!",t1);
    
        head.printTasks();
        System.out.println();
        head.printTasksRev();
        System.out.println();
    
        System.out.println();
    
        Task.printTasks(head);
        System.out.println();
        Task.printTasksRev(head);
        System.out.println();

    }


    
}

