package main.java.ch.uzh.player.PlaceBoatsStrategy;

import main.java.ch.uzh.board.Grid;
import main.java.ch.uzh.board.GridType;
import main.java.ch.uzh.board.Position;
import main.java.ch.uzh.boat.Boat;
import main.java.ch.uzh.boat.Fleet;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayerPlacement extends AbstractPlacementStrategy{

    public void placeBoats(Fleet fleet, Grid grid){

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        for(Boat boat : fleet)
        {
            boolean success = false;

            while(!success){
                System.out.println(grid.showGridContent(GridType.OCEAN_GRID));
                try{
                    System.out.printf("Please enter the starting and ending position for your %s (size %d), separated by comma.%n",
                            boat.toString(), boat.getSize());
                    String userInput = scanner.nextLine();  // Read user input
                    Position[] boatPositions = parseInput(userInput);
                    placeOneBoat(boat, grid, boatPositions[0], boatPositions[1]);
                    success = true;

                } catch (Exception e) {
                    System.out.println("An error occurred, please try again: " + e.getMessage());
                }
            }
            System.out.println("Thank you!");

        }
        System.out.println("The boats are placed!");
    }

    private Position[] parseInput(String s){
        // Verify against basic heuristics
        if (s.length() < 5 || s.length() > 7)
            throw new InputMismatchException("Please enter valid input");

        String[] parts = s.split(",");
        if (parts.length != 2)
            throw new InputMismatchException("Please enter exactly 2 locations (start and end), separated by comma");

        Position start = Position.parse(parts[0]);
        Position end = Position.parse(parts[1]);

        //check if coordinate is valid
        if (start == null)
            throw new InputMismatchException("Please enter valid location for the start coordinate");

        if (end == null)
            throw new InputMismatchException("Please enter valid location for the end coordinate");

        return new Position[] {start, end};
    }

}
