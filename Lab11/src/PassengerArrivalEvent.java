import java.util.Random;

/**
 * A class that handles the addition of Passengers to the line on an island
 */
public class PassengerArrivalEvent implements IEvent{
    private int island;
    public PassengerArrivalEvent(int island){
        this.island = island;
    }
    public int getIsland(){
        return island;
    }
    
    // TODO: implement this method
    @Override
    public void run() {
        Passenger pa=new Passenger(getIsland());
        FerrySim.lines[getIsland()].add(pa);
        Random rand = new Random();
        double time = rand.nextDouble();
        time=5+10*time;
        PassengerArrivalEvent pae=new PassengerArrivalEvent(getIsland());
        FerrySim.agenda.add(pae,time);
        System.out.println("Passenger Event Island: "+island+
                " Current Time is: "+FerrySim.agenda.getCurrentTime()+" Next passenger in "+time);
    }
}
