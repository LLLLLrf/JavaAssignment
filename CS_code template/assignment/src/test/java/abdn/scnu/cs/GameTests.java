package abdn.scnu.cs;

import static org.junit.Assert.*;

import org.junit.Test;

import abdn.scnu.cs.AbstractGameGrid;
/*
import abdn.scnu.cs.BattleShip;
import abdn.scnu.cs.Game;
import abdn.scnu.cs.GameControls;
import abdn.scnu.cs.GameGrid;
import abdn.scnu.cs.OpponentGameGrid;
import abdn.scnu.cs.PlayerGameGrid;
*/
import org.junit.Before;
import org.junit.After;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.lang.reflect.Field;


public class GameTests {
	
	 private final ByteArrayOutputStream outContent = new ByteArrayOutputStream ();
	    private final PrintStream originalOut = System.out;
	    
	    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	    public static final String ANSI_BLACK = "\u001B[30m";
	    public static final String ANSI_RESET = "\u001B[0m";
	    
	    @Before
	    public void setUp() throws Exception {
	       
	        System.setOut (new PrintStream (outContent));
	    }
	    
	    @After
	    public void tearDown() throws Exception {
	        System.setOut (originalOut);
	        
	    }

		    
	    
	@Test
	public void t1_1_a() {
		BattleShip ship = new BattleShip ("Ship"); 
		try {
	        assertEquals ("Ship", ship.name);
	    } catch (Exception | Error e) {
	        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+"T1.1_a: Make sure the constructor of BattleShip class is modified and the ship name is stored in appropriate variable"+ ANSI_RESET+ e);
	    }	
		
	}
	
	@Test
	public void t1_1_b() {
		int horizontal = 0;
		int vertical = 0; 
		
		for (int i=0;i<100;i++) {
		
			BattleShip ship = new BattleShip ("Ships" +i); 
		
		if (ship.shipOrientation.equals("vertical")) {
			vertical++;
		}
        if (ship.shipOrientation.equals("horizontal")) {
        	horizontal++;
		}
		}
		
        if (horizontal<20||vertical<20) {
        	fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+"T1.1_b The ship orientation should be decided at random. Out of 100 ships you have "+vertical+" positioned vertically and "+horizontal+" positioned horizontally. Thisi does not look random."+ ANSI_RESET);
        	
        }
	}
	
/*
	@Test
	public void t1_2() {
		try {
		BattleShip ship = new BattleShip ("Ship1"); 
		 int [][] shipCoordinates = new int [1][2];
		 shipCoordinates [0][0] = 1; 
		 shipCoordinates [0][1] = 2;
	       
	
			ship.setHits(2); 
			
			ship.setShipCoordinates(shipCoordinates);
			
			int [][] checkCoordinates = ship.getShipCoordinates();
			
			assertEquals (1, checkCoordinates[0][0]);
			assertEquals (2, checkCoordinates[0][1]);
			
			assertEquals ("Ship1", ship.getName());
			
			assertEquals (2, ship.getHits());
			
			boolean recognisedValues = false; 
			
			if (ship.getShipOrientation().equals("horizontal")||ship.getShipOrientation().equals("vertical")) {
				recognisedValues= true;
			}
			
			assertEquals (true, recognisedValues);
			
	       
	        
	    } catch (Exception | Error e) {
	        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+"T1.2 It seems the getter and setter methods are not working as expected. "+ ANSI_RESET+ e);
	    }	
		
	}
	
	
	@Test
	public void t1_3() {
		try {
		BattleShip ship = new BattleShip ("Ship1"); 
		 ship.shipCoordinates = new int [1][2];
		 ship.shipCoordinates [0][0] = 1; 
		 ship.shipCoordinates [0][1] = 2;
	       
	
			ship.hits = 2; 
			
			//ship is not at these coordinates
			assertEquals (false, ship.checkAttack(2, 1));
			
			assertEquals (true, ship.checkAttack(1, 2));
			
			//hitting same coordinate again shoudl return false
			assertEquals (false, ship.checkAttack(1, 2));
	       
	        
	    } catch (Exception | Error e) {
	        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+"T1.3 It seems that the checkAttack method is not working as expected. "+ ANSI_RESET+ e);
	    }	
		
	}
	
	@Test
	public void t2_1() {
	       
			try {
		    AbstractGameGrid gameGrid = new GameGrid (10,10,4);
		    
		    boolean foundDifferentCharacter  =false;
		   
		    for (int i = 0; i< gameGrid.gameGrid.length;i++) {
				
				String subArray [] = gameGrid.gameGrid[i];
				for (int j = 0; j< subArray.length;j++) {
					//allow ships as well in case later tasks were completed too 
					if (!(subArray[j].equals(".")||subArray[j].equals("*"))) {
						foundDifferentCharacter =true; 
						System.out.println(subArray[j]);
					}
				}
			}

			//ship is not at these coordinates
			assertEquals (false,foundDifferentCharacter);
			
	        
	    } catch (Exception | Error e) {
	        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+"T2.1: Are you initialising the GameGrid with . ? Does it extend teh AbstractGameGrid?   "+ ANSI_RESET+ e);
	    }	
		
	}
	
	@Test
	public void t2_2() {
	       
			try {
		    AbstractGameGrid gameGrid = new GameGrid (10,10,4);
		    
			assertEquals (4,gameGrid.ships.length);
			
			assertEquals ("Ship 1",gameGrid.ships[0].name);
			
			assertEquals ("Ship 2",gameGrid.ships[1].name);
			
			assertEquals ("Ship 3",gameGrid.ships[2].name);
			
			assertEquals ("Ship 4",gameGrid.ships[3].name);
			
	        
	    } catch (Exception | Error e) {
	        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+"T2.2: Are you generating the ships based on the parameter passed to the constructor of GameGrid? Do you generate the names of ships as specifiied?   "+ ANSI_RESET+ e);
	    }	
		
	}
	
	@Test
	public void t2_3() {
	       
			try {
			//check if one ship created - create large grid to limit overlap
		    AbstractGameGrid gameGrid = new GameGrid (1000,1000,1);
		    
		    int shipLength ;
		    
		    GameGrid  gameGrid2 = new GameGrid (1000,1000,3);
		    
		    shipLength = 0;
             for (int i = 0; i< gameGrid2.gameGrid.length;i++) {
				
				String subArray [] = gameGrid2.gameGrid[i];
				for (int j = 0; j< subArray.length;j++) {
					if (subArray[j].equals("*")) {
						shipLength++;
						System.out.println(subArray[j]);
					}
				}
			}
          //allow for some overlap
         if (shipLength<6) {
        	 fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+"T2.3: Are you generating required number of ships?   "+ ANSI_RESET);
        	 
         }
         shipLength = 0;
             for (int i = 0; i< gameGrid.gameGrid.length;i++) {
				
				String subArray [] = gameGrid.gameGrid[i];
				for (int j = 0; j< subArray.length;j++) {
					if (subArray[j].equals("*")) {
						shipLength++;
						System.out.println(subArray[j]);
					}
				}
			}

			//check if there is a ship
			assertEquals (3,shipLength);
			
			
			
	         
	         GameGrid  gameGrid3 = new GameGrid (1000,1000,1);
			    
			 
	          //allow for some overlap
	         if (gameGrid3.ships[0].shipCoordinates[0][0]==gameGrid.ships[0].shipCoordinates[0][0]&&gameGrid3.ships[0].shipCoordinates[0][1]==gameGrid.ships[0].shipCoordinates[0][1]) {
	        	 fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+"T2.3: Are you possitioning your ships randomly?   "+ ANSI_RESET);
	        	 
	         }
			
			
	        
	    } catch (Exception | Error e) {
	        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+"T2.3: Something is wrong with your method of placing ships on the grid.   "+ ANSI_RESET+ e);
	    }	
		
	}
	
	@Test
	public void t2_4() {
	       
			try {
				//check if the class extends GameGrid hence type GameGrid
			GameGrid pGameGrid = new PlayerGameGrid (4,4,1);
			
			pGameGrid.gameGrid[0][0] = ".";
			pGameGrid.gameGrid[0][1] = ".";
			pGameGrid.gameGrid[0][2] = ".";
			pGameGrid.gameGrid[0][3] = ".";
			
			pGameGrid.gameGrid[1][0] = ".";
			pGameGrid.gameGrid[1][1] = "*";
			pGameGrid.gameGrid[1][2] = "*";
			pGameGrid.gameGrid[1][3] = "X";
			
			pGameGrid.gameGrid[2][0] = ".";
			pGameGrid.gameGrid[2][1] = "%";
			pGameGrid.gameGrid[2][2] = "%";
			pGameGrid.gameGrid[2][3] = ".";
			
			pGameGrid.gameGrid[3][0] = ".";
			pGameGrid.gameGrid[3][1] = ".";
			pGameGrid.gameGrid[3][2] = ".";
			pGameGrid.gameGrid[3][3] = ".";
			
			((PlayerGameGrid) pGameGrid).printGrid();
			
			String output = outContent.toString().trim(); 
	        
	        int count = 0;
	        int count2 = 0;
	        int count3 = 0;
	        int count4 = 0;
	        
	        for (int i = 0 ; i< output.length();i++ ) {
	            if (output.charAt(i) == '.') {
	               count++; 
	            }
	            if (output.charAt(i) == 'X') {
		               count2++; 
		            }
	            if (output.charAt(i) == '%') {
		               count3++; 
		            }
	            if (output.charAt(i) == '*') {
		               count4++; 
		            }
	        }
	        
	        assertTrue (count==11);
	        
	        assertTrue (count2==1);
	        
	        assertTrue (count3==2);
	        
	        assertTrue (count4==2);
	        
	        outContent.reset();   
			
			
			GameGrid oGameGrid = new OpponentGameGrid (4,4,1);
			
			oGameGrid.gameGrid[0][0] = ".";
			oGameGrid.gameGrid[0][1] = ".";
			oGameGrid.gameGrid[0][2] = ".";
			oGameGrid.gameGrid[0][3] = ".";
			
			oGameGrid.gameGrid[1][0] = ".";
			oGameGrid.gameGrid[1][1] = "*";
			oGameGrid.gameGrid[1][2] = "*";
			oGameGrid.gameGrid[1][3] = "X";
			
			oGameGrid.gameGrid[2][0] = ".";
			oGameGrid.gameGrid[2][1] = "%";
			oGameGrid.gameGrid[2][2] = "%";
			oGameGrid.gameGrid[2][3] = ".";
			
			oGameGrid.gameGrid[3][0] = ".";
			oGameGrid.gameGrid[3][1] = ".";
			oGameGrid.gameGrid[3][2] = ".";
			oGameGrid.gameGrid[3][3] = ".";
			
			
((OpponentGameGrid) oGameGrid).printGrid();
			
			output = outContent.toString().trim(); 
	        
	        count = 0;
	        count2 = 0;
	        count3 = 0;
	        count4 = 0;
	        
	        for (int i = 0 ; i< output.length();i++ ) {
	            if (output.charAt(i) == '.') {
	               count++; 
	            }
	            if (output.charAt(i) == 'X') {
		               count2++; 
		            }
	            if (output.charAt(i) == '%') {
		               count3++; 
		            }
	            if (output.charAt(i) == '*') {
		               count4++; 
		            }
	        }
	        
	        assertTrue (count==13);
	        
	        assertTrue (count2==1);
	        
	        assertTrue (count3==2);
	        
	        assertTrue (count4==0);
	        
	        outContent.reset();   
			
	        
	    } catch (Exception | Error e) {
	        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+"T2.4: Something is wrong with your methods. Are you hiding opponnet's ships? Do you extend the GameGrid class?    "+ ANSI_RESET+ e);
	    }	
		
	}
	
	@Test
	public void t3_1() {
	       
			try {
				//check if extends the interface
			GameControls game = new Game (10,10,3); 
			
			//check if conversion works
			OpponentGameGrid grid1 = (OpponentGameGrid) game.getOpponentssGrid(); 
			
			//check if conversion works
			PlayerGameGrid grid2 = (PlayerGameGrid) game.getPlayersGrid(); 
			
			 assertTrue (grid1.gameGrid.length==10);
			 
			 assertTrue (grid2.gameGrid.length==10);
			 
			 assertTrue (grid1.ships.length==3);
			 
			 assertTrue (grid2.ships.length==3);
			
	        
	    } catch (Exception | Error e) {
	        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+"T3.1: Something is wrong. Are you intantiating the grids? Do you extend the GameControls interface?    "+ ANSI_RESET+ e);
	    }	
		
	}
	
	@Test
	public void t3_3() {
	       
			try {
				//check if extends the interface
			GameControls game = new Game (4,4,1); 
			
			//check if conversion works
			OpponentGameGrid grid1 = (OpponentGameGrid) game.getOpponentssGrid(); 
			
			//check if conversion works
			PlayerGameGrid grid2 = (PlayerGameGrid) game.getPlayersGrid(); 
			
			grid1.ships[0].setHits(3);
			
			game.checkVictory();
			
			String output = outContent.toString().trim(); 
	       
	        assertTrue (output.contains("You have won!"));
	        
	        outContent.reset();  
	        
	        grid1.ships[0].setHits(0);
	        
	        grid2.ships[0].setHits(3);
	        
	    	game.checkVictory();
	        output = outContent.toString().trim(); 
		       
	        assertTrue (output.contains("You have lost!"));
	        
	        outContent.reset(); 
			
	        
	    } catch (Exception | Error e) {
	        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+"T3.3: Something is wrong. Is your check victory method working? Check the code of this test for hints on the expected behaviour.    "+ ANSI_RESET+ e);
	    }	
			
	}
			
			@Test
			public void t3_4() {
			       
					try {
						//check if extends the interface
					Game game = new Game (4,4,1); 
					
					//find out where opponent's ship is
					int coordinates [][] = ((OpponentGameGrid) game.getOpponentssGrid()).ships[0].shipCoordinates;
					
					game.playRound(coordinates[0][0] +","+coordinates[0][1]);
					
					String output = outContent.toString().trim(); 
			       
					//there should be at least one X 
					
					 int count = 0;
				     int count2 = 0;
				     int count3 = 0;
				     int count4 = 0;
				        
				     for (int i = 0 ; i< output.length();i++ ) {
				            if (output.charAt(i) == '.') {
				               count++; 
				            }
				            if (output.charAt(i) == 'X') {
					               count2++; 
					            }
				            if (output.charAt(i) == '%') {
					               count3++; 
					            }
				            if (output.charAt(i) == '*') {
					               count4++; 
					            }
				        }
					
			        assertTrue (count2>0);
			        
			        assertTrue (output.contains("HIT Ship 1!!!"));
			        
			       
			        
			        // we only have one ship and it can't have this shape so there has to be at least one miss
			        game.playRound("0,0");
			        game.playRound("0,1");
			        outContent.reset();
			        game.playRound("1,0");
			        
			        output = outContent.toString().trim(); 
				    
			        count = 0;
				    count2 = 0;
				    count3 = 0;
				    count4 = 0;
				        
				     for (int i = 0 ; i< output.length();i++ ) {
				            if (output.charAt(i) == '.') {
				               count++; 
				            }
				            if (output.charAt(i) == 'X') {
					               count2++; 
					            }
				            if (output.charAt(i) == '%') {
					               count3++; 
					            }
				            if (output.charAt(i) == '*') {
					               count4++; 
					            }
				        }
					//there must be at least one miss recorded
			        assertTrue (count3>0);
			       
			        outContent.reset(); 
					
			        
			    } catch (Exception | Error e) {
			        fail(ANSI_WHITE_BACKGROUND +ANSI_BLACK+"T3.4: Something is wrong with you rplayRound method. Check your code and also code of this test to see what is expected when your method is called.    "+ ANSI_RESET+ e);
			    }	
		
	}
*/
}
