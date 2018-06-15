package planetwars.strategies;

import planetwars.publicapi.*;

import java.lang.reflect.Array;
import java.util.*;

import static sun.swing.MenuItemLayoutHelper.max;

public class myStrategy implements IStrategy {
    private static final int POPULATION_DIVISION = 5;
    private Random random;

    public myStrategy() {
        random=new Random();
    }

    @Override
    public void takeTurn(List<IPlanet> planets, IPlanetOperations planetOperations, Queue<IEvent> eventsToExecute) {
        List<IVisiblePlanet> conquered=new ArrayList<>();
        Queue<IVisiblePlanet> conqueredVisiblePlanets = new LinkedList<>();
        Queue<IVisiblePlanet> unconqueredVisiblePlanets = new LinkedList<>();
        Map<Integer,Integer > conqueredPlanets=new HashMap<>();//first integer ID, second integer population
        Map<Integer,IVisiblePlanet> conqueredPlanetsII=new HashMap<>();
        Map<Integer,IVisiblePlanet> unconqueredPlanets=new HashMap<>();

        for (IPlanet planet : planets) {//classify all visible planets
            if (planet instanceof IVisiblePlanet && ((IVisiblePlanet) planet).getOwner() == Owner.SELF) {
                conquered.add((IVisiblePlanet) planet);
                conqueredVisiblePlanets.add((IVisiblePlanet) planet);
                conqueredPlanets.put(planet.getId(),(int)((IVisiblePlanet) planet).getPopulation());
                conqueredPlanetsII.put(planet.getId(),(IVisiblePlanet)planet);
            } else if (planet instanceof IVisiblePlanet){
                unconqueredVisiblePlanets.add((IVisiblePlanet) planet);
                unconqueredPlanets.put(planet.getId(),(IVisiblePlanet) planet);
            }
        }

        Queue<IVisiblePlanet> traversalResult=depthTraverse(conquered);
        Queue<IVisiblePlanet> frontierPlanets=getFrontier(traversalResult,conqueredPlanets);

        while(!conqueredVisiblePlanets.isEmpty()){//send back up population to frontier, as close as enough
            IVisiblePlanet now=conqueredVisiblePlanets.poll();
            if(!frontierPlanets.contains(now)){
                IVisiblePlanet startSending=now;
                List<IEdge> neighbor=new ArrayList<>(startSending.getEdges());
                for(int i=0;i<neighbor.size();i++){
                    int tempID=neighbor.get(i).getDestinationPlanetId();
                    if(conqueredPlanetsII.containsKey(tempID)){
                        if(conqueredPlanets.get(startSending.getId())>4){
                            IVisiblePlanet goTo=conqueredPlanetsII.get(tempID);
                            long people=(long)(conqueredPlanets.get(startSending.getId())*0.75/startSending.getEdges().size());
                            if((conqueredPlanets.get(goTo.getId())+people)<(goTo.getHabitability()*0.8)){
                                eventsToExecute.offer(planetOperations.transferPeople(startSending,goTo,people));
                                conqueredPlanets.put(startSending.getId(),conqueredPlanets.get(startSending.getId())-(int)people);
                            }else{
                                people=Math.max((int)(conqueredPlanets.get(startSending.getId())*0.75),(int)(goTo.getHabitability()*0.1));
                                eventsToExecute.offer(planetOperations.transferPeople(startSending,goTo,people));
                                conqueredPlanets.put(startSending.getId(),conqueredPlanets.get(startSending.getId())-(int)people);
                            }
                        }
                    }
                }
            }
        }
        while(!frontierPlanets.isEmpty()){
            if(frontierPlanets.peek().getEdges().size()>1&&conqueredPlanets.get(frontierPlanets.peek().getId())>frontierPlanets.peek().getEdges().size()+1){
                while(conqueredPlanets.get(frontierPlanets.peek().getId())>frontierPlanets.peek().getEdges().size()+1){
                    IVisiblePlanet startPlanet=frontierPlanets.poll();
                    List<IEdge> neighbor=new ArrayList<>(startPlanet.getEdges());
                    for(int i=0;i<neighbor.size();i++){
                        int tempID=neighbor.get(i).getDestinationPlanetId();
                        if(unconqueredPlanets.containsKey(tempID)){
                            IVisiblePlanet toAttack=unconqueredPlanets.get(tempID);
                            if(toAttack.getOwner()==Owner.NEUTRAL){//conquer neutral planets
                                eventsToExecute.offer(planetOperations.transferPeople(startPlanet,toAttack,1));
                                conqueredPlanets.put(startPlanet.getId(),conqueredPlanets.get(startPlanet.getId())-1);
                            }else if(toAttack.getOwner()==Owner.OPPONENT&&conqueredPlanets.get(startPlanet.getId())>(int)toAttack.getPopulation()*1.5){//conquer opponents
                                eventsToExecute.offer(planetOperations.transferPeople(startPlanet,toAttack,(int)(toAttack.getPopulation()*1.5)));
                                conqueredPlanets.put(startPlanet.getId(),conqueredPlanets.get(startPlanet.getId())-(int)(toAttack.getPopulation()*1.5));
                            }
                        }
                    }
                }
            }else if(frontierPlanets.peek().getEdges().size()==1){//handle the special case of only one edge
                IVisiblePlanet startPlanet=frontierPlanets.poll();
                List<IEdge> neighbor=new ArrayList<>(startPlanet.getEdges());
                int neighborID=neighbor.get(0).getDestinationPlanetId();
                if(unconqueredPlanets.containsKey(neighborID)){
                    IVisiblePlanet toAttack=unconqueredPlanets.get(neighborID);
                    if(toAttack.getOwner()==Owner.NEUTRAL&&conqueredPlanets.get(startPlanet.getId())>=2){
                        eventsToExecute.offer(planetOperations.transferPeople(startPlanet,toAttack,1));
                        conqueredPlanets.put(startPlanet.getId(),conqueredPlanets.get(startPlanet.getId())-1);
                    }else if(toAttack.getOwner()==Owner.OPPONENT&&conqueredPlanets.get(startPlanet.getId())>(int)toAttack.getPopulation()*1.5){
                        eventsToExecute.offer(planetOperations.transferPeople(startPlanet,toAttack,(int)(toAttack.getPopulation()*1.5)));
                        conqueredPlanets.put(startPlanet.getId(),conqueredPlanets.get(startPlanet.getId())-(int)(toAttack.getPopulation()*1.5));
                    }
                }
            }else{
                frontierPlanets.poll();
            }
        }
    }

    private Queue<IVisiblePlanet> depthTraverse(List<IVisiblePlanet> conquered){//depth first traversal
        Queue<IVisiblePlanet> traversalOrder=new LinkedList<>();//new queue for the resulting traverse order
        Stack<IVisiblePlanet> vertexStack=new Stack<>();//a new stack to hold vertices as they are visited

        Map<IVisiblePlanet,String> isVisited=new HashMap<>();
        for(int i=0;i<conquered.size();i++){
            isVisited.put(conquered.get(i),"false");
        }
        isVisited.put(conquered.get(0),"true");
        traversalOrder.add(conquered.get(0));
        vertexStack.push(conquered.get(0));

        while(!vertexStack.isEmpty()){
            IVisiblePlanet topVertex=vertexStack.peek();
            if(firstUnvisitedNeighbor(topVertex,isVisited,conquered)!=null){
                IVisiblePlanet nextNeighbor=firstUnvisitedNeighbor(topVertex,isVisited,conquered);
                isVisited.put(nextNeighbor,"true");
                traversalOrder.add(nextNeighbor);
                vertexStack.push(nextNeighbor);
            }else{
                vertexStack.pop();
            }
        }
        return traversalOrder;
    }

    private Queue<IVisiblePlanet> getFrontier(Queue<IVisiblePlanet> traverse,Map conquered){
        Queue<IVisiblePlanet> finalQueue=new LinkedList<>();
        while(!traverse.isEmpty()){
            IVisiblePlanet toCheck=traverse.poll();
            List<IEdge> neighbor=new ArrayList<>(toCheck.getEdges());
            while(!neighbor.isEmpty()){
                if(!conquered.containsKey(neighbor.get(0).getDestinationPlanetId())){
                    finalQueue.add(toCheck);
                    break;
                }else{
                    neighbor.remove(0);
                }
            }

        }
        return finalQueue;
    }

    private IVisiblePlanet firstUnvisitedNeighbor(IVisiblePlanet topVertex,Map isVisited,List<IVisiblePlanet> conquered){
        List<IVisiblePlanet> temporary=getConqueredNeighbor(topVertex,conquered);
        IVisiblePlanet returnUse=null;
        while(!temporary.isEmpty()){
            if(isVisited.get(temporary.get(0))=="false"){
                returnUse=temporary.get(0);
                break;
            }else{
                temporary.remove(0);
            }
        }
        return returnUse;
    }

    private List<IVisiblePlanet> getConqueredNeighbor(IVisiblePlanet destination,List<IVisiblePlanet> conquered) {
        List<IEdge> neighboring=new ArrayList<>(destination.getEdges());
        List<IVisiblePlanet> toDo=new ArrayList<>();
        for(int i=0;i<neighboring.size();i++){
            int tempID=neighboring.get(i).getDestinationPlanetId();
            for(int j=0;j<conquered.size();j++){
                if(conquered.get(j).getId()==tempID){
                    toDo.add(conquered.get(j));
                }
            }
        }
        return toDo;
    }

    private Map<Integer,ArrayList<IVisiblePlanet>> createMap(List<IVisiblePlanet> source,List<IVisiblePlanet> destination){
        Map<Integer,ArrayList<IVisiblePlanet>> toDo=new HashMap<>();
        for(int i=0;i<source.size();i++){
            ArrayList<IVisiblePlanet> temp=new ArrayList<>();
            toDo.put(source.get(i).getId(),temp);
        }
        for(int i=0;i<destination.size();i++){
            IVisiblePlanet tempPlanet=destination.get(i);
            List<IEdge> neighboring=new ArrayList<>(tempPlanet.getEdges());
            for(int j=0;j<neighboring.size();j++){
                int destinationID=neighboring.get(j).getDestinationPlanetId();
                if(toDo.containsKey(destinationID)){
                    ArrayList<IVisiblePlanet> updated=toDo.get(destinationID);
                    updated.add(tempPlanet);
                    toDo.put(destinationID,updated);
                }
            }
        }
        return toDo;
    }

    @Override
    public String getName() {
        return "Yancheng Yuan is a LoveLiver";
    }

    @Override
    public boolean compete() {
        return true;
    }
}
