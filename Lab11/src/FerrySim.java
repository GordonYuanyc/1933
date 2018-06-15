/**
 * This is the driver class for the ferry simulation. It keeps track of the agenda,
 * and potentially other shared information. It also has the main, which initializes 
 * the simulation and runs the Events in the agenda.
 */
public class FerrySim {
      
    // Note that this is static. This way, it can be accessed anywhere.
    public static PQ agenda = new PQ();
    // Array of queues to store Passengers for each island
    public static Q<Passenger>[] lines = new Q[3];

    public static void main(String[] args) {
        lines[0]=new Q<>();
        lines[1]=new Q<>();
        lines[2]=new Q<>();

        // add PassengerArrivalEvents to agenda
        agenda.add(new PassengerArrivalEvent(0), 0);
        agenda.add(new PassengerArrivalEvent(1), 0);
        agenda.add(new PassengerArrivalEvent(2), 0);


        // add FerryEvent to agenda

        // loops through agenda and runs the next iEvent
        // will throw an exception until events are added to the agenda
        while(agenda.getCurrentTime() <= 10000) {
            agenda.remove().run();
        }
        System.out.println("Island 0, number of passengers in line "+lines[0].length());
        System.out.println("Island 1, number of passengers in line "+lines[1].length());
        System.out.println("Island 2, number of passengers in line "+lines[2].length());
    }

}
