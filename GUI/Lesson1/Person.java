 
 class Person{
    private  String name;
    private  Car theFirstCar;

    public Person(String name) {
        this.name = name;
        this.theFirstCar = null;
    }

    public Person buys(String make, int price) {
        if ( this.theFirstCar == null ){
            this.theFirstCar = new Car( make, price );
        } else {
            this.theFirstCar = new Car( make, price, this.theFirstCar );
        }
        return this;
    }

    public String getName() {
        return this.name;
    }

    public void showCars() {
        if ( this.theFirstCar != null ){
            this.theFirstCar.showCars();
        }
    }

    public void showCarsRev() {
        if ( this.theFirstCar != null ){
            this.theFirstCar.showCarRev();
        }
    }

    public int getTotalPrice() {
        Car b = this.theFirstCar;
        int tp = 0;
        while ( b != null ){
            tp += b.getPrice();
            b = b.getNext();
        }
        return tp;
    }

    public boolean hasCar(String make) {
        Car b = this.theFirstCar;
        while ( b != null && !b.getMake().equalsIgnoreCase(make) ){
            b = b.getNext();
        }
        return b != null;
    }

    public String hasCarString(String make) {
        return this.hasCar(make) ? "Yes" : "No";
    }

    public Car mostExpensive() {
        Car b = this.theFirstCar;
        Car mp = this.theFirstCar;
        while ( b != null ){
            if ( mp.getPrice() < b.getPrice() ) mp = b;
            b = b.getNext();
        }
        return mp;
    }

}
