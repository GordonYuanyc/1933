package planetwars.strategies;

import planetwars.publicapi.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class peaceStrategy implements IStrategy{
    private static final int POPULATION_DIVISION = 5;
    private List<IVisiblePlanet> conqueredVisiblePlanets = new ArrayList<>();
    private List<IVisiblePlanet> unconqueredVisiblePlanets = new ArrayList<>();
    List<IEdge> neighboring=new ArrayList<>();

    public peaceStrategy() {

    }

    @Override
    public void takeTurn(List<IPlanet> planets, IPlanetOperations planetOperations, Queue<IEvent> eventsToExecute) {
        for (IPlanet planet : planets) {
            if (planet instanceof IVisiblePlanet && ((IVisiblePlanet) planet).getOwner() == Owner.SELF) {
                conqueredVisiblePlanets.add((IVisiblePlanet) planet);
            } else if (planet instanceof IVisiblePlanet)
                unconqueredVisiblePlanets.add((IVisiblePlanet) planet);
        }
        for(int i=0;i<unconqueredVisiblePlanets.size();i++){
            IVisiblePlanet destination=unconqueredVisiblePlanets.get(i);
            for(int j=0;j<getSource(destination).size();j++){
                eventsToExecute.offer(planetOperations.transferPeople
                        (getSource(destination).get(j),destination,getTransfer(getSource(destination).get(j))));
            }
        }
    }

    private int getTransfer(IVisiblePlanet source){
        int i=0;
        if(source.getPopulation()>source.getHabitability()/2){
            i=source.getHabitability()/2;
        }else{
            i=(int)source.getPopulation();
        }
        return i;
    }

    private List<IVisiblePlanet> getSource(IVisiblePlanet destination) {
        List<IEdge> neighboring=new ArrayList<>(destination.getEdges());
        List<IVisiblePlanet> toDo=new ArrayList<>();
        for(int i=0;i<neighboring.size();i++){
            int tempID=neighboring.get(i).getDestinationPlanetId();
            for(int j=0;j<conqueredVisiblePlanets.size();j++){
                if(conqueredVisiblePlanets.get(j).getId()==tempID){
                    toDo.add(conqueredVisiblePlanets.get(j));
                }
            }
        }
        /*if(destination.getOwner()==null){

        }*/
        return toDo;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean compete() {
        return false;
    }
}
