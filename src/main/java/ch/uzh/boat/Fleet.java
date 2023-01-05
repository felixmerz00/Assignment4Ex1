package main.java.ch.uzh.boat;

import java.util.ArrayList;
import java.util.Iterator;

public class Fleet implements Iterable<Boat>{
    // Distribute correct boats
    // Check if there are still surviving boats

    private ArrayList<Boat> boats = new ArrayList<>();

    public Fleet(){
        this.boats.add(new Carrier());
        this.boats.add(new Battleship());
        this.boats.add(new Battleship());
        this.boats.add(new Submarine());
        this.boats.add(new Submarine());
        this.boats.add(new Submarine());
        this.boats.add(new PatrolBoat());
        this.boats.add(new PatrolBoat());
        this.boats.add(new PatrolBoat());
        this.boats.add(new PatrolBoat());
    }

    public boolean stillStanding() {
        for (Boat boat : boats) {
            if (boat.stillAlive()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Boat> iterator() {
        return this.boats.iterator();
    }
}


