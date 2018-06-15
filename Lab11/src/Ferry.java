import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A class representing a Ferry, which transports Passengers from island to island.
 * Can hold up to 60 Passengers at a time.
 */

public class Ferry {
    private ArrayList<Passenger> island1=new ArrayList<>();
    private ArrayList<Passenger> island2=new ArrayList<>();
    private ArrayList<Passenger> island3=new ArrayList<>();

    // TODO: implement this
    public boolean addPassenger(Passenger p) {
        if(island1.size()+island2.size()+island3.size()<=59){
            if(p.getDropoffIsland()==0){
                island1.add(p);
                return true;
            }else if(p.getDropoffIsland()==1){
                island2.add(p);
                return true;
            }else if(p.getDropoffIsland()==2){
                island3.add(p);
                return true;
            }
        }
        return false;
    }

    // TODO: implement this
    public Passenger[] removePassengersAtIsland(int island){
        if(island==0){
            Passenger[] p=new Passenger[island1.size()];
            return island1.toArray(p);
        }else if(island==1){
            Passenger[] p=new Passenger[island2.size()];
            return island2.toArray(p);
        }else if(island==2){
            Passenger[] p=new Passenger[island3.size()];
            return island3.toArray(p);
        }
        return null;
    }
    
    // TODO: implement this
    public boolean isFull() {
        if(island1.size()+island2.size()+island3.size()>=60){
            return true;
        }
        return false;
    }
    
}
