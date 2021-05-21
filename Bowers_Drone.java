//Author Name: Joachim Bowers
//Date: 05/16/2021
//Program Name: Bowers_Drone
//Purpose: Simulation using button, drone movement in x, y,z location

import java.util.Scanner;

public class Bowers_Drone {
	

	//Variables
	public int xPos = 0;
	public int yPos = 0;
	public int zPos = 0;
	public String direction = "North";
	
	//Getters and setters
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public int getzPos() {
		return zPos;
	}
	public void setzPos(int zPos) {
		this.zPos = zPos;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}

	//methods
	public void goUp() {
		zPos = zPos + 1;
	}
	public void goDown() {
		zPos = zPos - 1;
	}
	public void goFoward(){
		if (direction == "North") {
			yPos = yPos + 1;
		}
		if (direction == "South") {
			yPos = yPos - 1;
		}
		if (direction == "West") {
			xPos = xPos - 1;
		}
		if (direction == "East") {
			xPos = xPos + 1;
		}
	}
	public void goBackwards() {
		if (direction == "North") {
			yPos = yPos - 1;
		}
		if (direction == "South") {
			yPos = yPos + 1;
		}
		if (direction == "West") {
			xPos = xPos + 1;
		}
		if (direction == "East") {
			xPos = xPos - 1;
		}
	}
	
			
	public void turnLeft(){
		String tempDirection = direction;
		if (tempDirection == "North") {
			direction = "West";
		}
		if (tempDirection == "West") {
			direction = "South";
		}
		if (tempDirection == "South") {
			direction = "East";
		}
		if (tempDirection == "East") {
			direction = "North";
		}
		
	}
	public void turnRight() {
		String tempDirection = direction;
		if (tempDirection == "North") {
			direction = "East";
			
		}
		if (tempDirection == "East") {
			direction = "South";
		}
		if (tempDirection == "South") {
			direction = "West";
		}
		if (tempDirection == "West") {
			direction = "North";
		}
	}
	
	public static void main(String[] args) {
		
		Bowers_Drone drone = new Bowers_Drone();
		
		//variable for the switch statement and loop
		int selection = 0;
		boolean loop = true;
		
		//create scanner object
		Scanner input = new Scanner(System.in);
		
		while(loop == true) {
		
			System.out.println("Which direction would you like to move the drone");
		
			System.out.println("1 - Move Up \n" 
					+"2 - Move Down \n"
					+"3 - Move Forward \n"
					+"4 - Move Backward \n"
					+"5 - Turn Left \n"
					+"6 - Turn Right \n"
					+"7 - display position \n"
					+"8 - exit navigation \n"
					);
			
			selection = input.nextInt();
			
			switch(selection) {
			case 1: 
				drone.goUp();
				System.out.println("You have moved up");
				break;
			
			case 2: 
				drone.goDown();
				System.out.println("You have moved down");
				break;
			
			case 3: 
				drone.goFoward();
				System.out.println("You have moved forward");
				break;
			
			case 4: 
				drone.goBackwards();
				System.out.println("You have moved backward");
				break;
			
			case 5: 
				drone.turnLeft();
				System.out.println("You have turned left");
				break;
			
			case 6: 
				drone.turnRight();
				System.out.println("You have turned right");
				break;
				
			case 7: 
				System.out.println("Bowers_Drone [x_pos=" + drone.getxPos() + ", "
					+ "y_pos=" + drone.getyPos() + ", "
					+ "z_pos=" + drone.getzPos() + ", "
					+ "orientation=" + drone.getDirection() +"]");
				break;
				
			case 8: 
				loop = false;
				break;
			}
		}
		
	}

}
