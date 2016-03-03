package breakout.levels;

import java.util.Scanner;

public class Level {

	private String level;
	
	public Level(String input){
		this.level = input;
	}
	
	public void setLevel(){
		Scanner user_input = new Scanner(System.in);
		System.out.print("Please enter the top row of your Level: ");
		String toprowinput = user_input.nextLine();
		System.out.print("Please enter the middle row of your Level: ");
		String middlerowinput = user_input.nextLine();
		System.out.print("Please enter the last row of your Level: ");
		String lastrowinput = user_input.nextLine();
		String userinput = toprowinput+middlerowinput+lastrowinput;
		this.level = userinput;
	}
	public String getLevel(){
		return this.level;
	}
	
	public void loadLevel(){
		
	}
	
	public void saveLevelin(){
		
	}
}
