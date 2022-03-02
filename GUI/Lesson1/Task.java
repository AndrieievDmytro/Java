

class Task {

    // private  String d;
    private Task next;

    public Task(String d , Task n){
    //    this.d = d;
        this.next = n;
    }

    public Task(String d){
        this(d ,null);

    }
    public void setNextTask(Task t){
    
    }

    public void printTasks(){
        System.out.print(this);
        if(this.next != null){
            this.next.printTasks();
        } 
    }

    public static void printTasks(Task head){
       
    }


    public void printTasksRev(){
        if(this.next != null){
            this.next.printTasksRev();
        } 
        System.out.print(this);
    }
    public static void printTasksRev(Task head){
        
    }

}