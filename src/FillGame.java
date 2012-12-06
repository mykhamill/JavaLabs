
public class FillGame {
	//Declare and set constants
	private static final int MAP_SIZE = 10;
	private static final int NUMBER_OF_COLORS = 4;
	
	//Declare primitive attributes
	private int mapSize;
	private int numberOfColors;
	
	//Declare object attributes
	private int[][] map;
	private int[][] newMap;

	//Base constructor
	public FillGame() {
		this.mapSize = MAP_SIZE;
		numberOfColors = NUMBER_OF_COLORS;
		resetGame();
	}
	
	//Single parameter constructor
	public FillGame(int mapSize) {
		this.mapSize = mapSize;
		numberOfColors = NUMBER_OF_COLORS;
		resetGame();
	}
	
	//Two parameter constructor
	public FillGame(int mapSize, int numOfColors) {
		this.mapSize = mapSize;
		numberOfColors = numOfColors;
		resetGame();
	}
	
	//initialising the boards
	public void resetGame() {
		map = new int[mapSize][mapSize]; // initialises the map
		newMap = new int[mapSize][mapSize]; // initialises the newMap
		for (int y = 0; y < mapSize; y++) { // loop over all the rows
			for (int x = 0; x < mapSize; x++) { // loop over all the columns on the current row
				map[y][x] = (int)Math.floor(Math.random() * numberOfColors); // set map location to random value within the number given
				newMap[y][x] = -1; // set newMap location to unset ie -1
			}
		}
	}
	
	//Returns the value at a location on the map
	public int getMapAt(int x, int y) {
		return map[y][x];
	}
	
	//Returns the size of the map
	public int getMapSize() {
		return mapSize;
	}
	
	//Returns the number of colors/values being used
	public int getNumberOfColors() {
		return numberOfColors;
	}
	
	//Prints the map values out to the std_out(console)
	public void displayMap() {
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	//Updates the map using the fill number to change all the map locations with the same
	//value as map location (0, 0)
	public boolean updateMap(int fillNumber) {
		if (fillNumber >= 0) { //Does not update if fillNumber is less than 0
			boolean result = true; //Used to check if the map is completed
			
			//Reinitialises the newMap
			newMap = new int[mapSize][mapSize]; 
			for (int y = 0; y < mapSize; y++) { 
				for (int x = 0; x < mapSize; x++) {
					newMap[y][x] = -1;
				}
			}
			
			newMap[0][0] = fillNumber; 
			exploreMapAt(0, 0); // the start of a recursive method
			
			// Fills in the rest of newMap with the same values as the map
			for (int y = 0; y < mapSize; y++) {
				for (int x = 0; x < mapSize; x++) {
					if (newMap[y][x] == -1) {
						newMap[y][x] = map[y][x]; 
					}
				}
			}
			
			//Loops over newMap and checks if any locations do not match newMap(0, 0) 
			for (int i = 0; i < mapSize * mapSize; i++) {
				int x = i / mapSize;
				int y = i % mapSize;
				if (newMap[y][x] != newMap[0][0]) {
					result = false;
					break;
				}
			}
			
			map = newMap; //Sets the map to newMap
			return result; //Returns true if all the newMap locations equal newMap(0, 0) 
		}
		return false;
	}
	
	public void exploreMapAt(int y, int x) {
		int[][] result = new int[4][]; //Initialises the result array
		if (!(x == 0 && y == 0)) { //Only alters locations that are not (0, 0)
			newMap[y][x] = newMap[0][0];
		}
		//If location is not on top edge of the board then element 0 of results is set
		if (y <= 0) {
			result[0] = new int[] {-1};  //Sets the value of the array
		} else {
			result[0] = new int[] {map[y - 1][x], y - 1, x}; //Sets the value of the array
		}
		
		//If location is not on right edge of the board then element 1 of the results is set
		if (x >= mapSize - 1) {
			result[1] = new int[] {-1}; //Sets the value of the array
		} else {
			result[1] = new int[] {map[y][x + 1], y, x + 1}; //Sets the value of the array
		}
		
		//If location is not on bottom edge of the board then element 2 of the results is set
		if (y >= mapSize - 1) {
			result[2] = new int[] {-1}; //Sets the value of the array
		} else {
			result[2] = new int[] {map[y + 1][x], y + 1, x}; //Sets the value of the array
		}
		
		//If location is not on left edge of the board then element 3 of the results is set
		if (x <= 0) {
			result[3] = new int[] {-1}; //Sets the value of the array
		} else {
			result[3] = new int[] {map[y][x - 1], y, x - 1}; //Sets the value of the array
		}
		
		//Loops over the results
		for (int i = 0; i < result.length; i++) {
			//Checks if the length of the result
			if (result[i].length > 1) {
				//If the location given by result is unset in newMap ie -1
				//and the value of the location on the map is equal to map(0, 0)
				//then that location is explored recursively
				if (newMap[result[i][1]][result[i][2]] == -1 && map[result[i][1]][result[i][2]] == map[0][0]) {
					exploreMapAt(result[i][1], result[i][2]); //Recursive call to explore the map location
				}
			}
		}
	}
}
