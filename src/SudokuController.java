import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import java.util.Random;


public class SudokuController {

	  @FXML
	    private GridPane grid;

	
	final int SIZE = 9;

	private int val=0;
	
	private TextField [] fields;
	
	

	@FXML
	
		public void initialize() {
		int i = 0;
		
		grid.setHgap(2);
		
		grid.setVgap(2);
		
		grid.paddingProperty();
		
		handleTextField();

	}
	
	
	    private void handleTextField() {
		
	    	 int i;
	    	
	    	fields = new TextField[SIZE*SIZE];
	    	//adding text fields to the board
	    	for( i = 0; i < SIZE*SIZE ; i++) {
	    		
	    		fields[i] = new TextField("");
	    		
	    		fields[i].setPrefSize(grid.getPrefWidth()/SIZE, grid.getPrefHeight() /SIZE);
	    		
	    		grid.add(fields[i], i%SIZE, i/SIZE);
	    		
	    		//checking if the value is legal 
	    		fields[i].setOnAction(new EventHandler<ActionEvent>() {
	    		
	    		@Override
	    		public void handle(ActionEvent event) {
	    			
	    			
	    			handleTxtFieldAction(event);
	    		
	    			}
	    		
	    		});
	    		
	    	}
	    	
		
	}


		
	 	private void handleTxtFieldAction(ActionEvent event) {
	 		
	 		 
	 		
	 		TextField temp = (TextField)event.getSource();
	 		
	 		int number = Integer.parseInt(temp.getText());
	 		
	 		int numRow = (int)grid.getRowIndex(temp);
	 		
	 		int numCol = (int)grid.getColumnIndex(temp);
	 		
	 		int indx = 9*numRow +numCol;
	 			 		
	 		int i = indx + 9;
	 		
	 		int num = 0;
	 			 	
	 		//checking the text fields who follow the current one in the column
	 		while( i < SIZE*SIZE ) {
	 			
	 			
	 			 if(isNumeric(fields[i].getText()))
	 		 		
		 			num = Integer.parseInt(fields[i].getText());
	 			
	 			 if(number == num) {
	 				
	 				System.out.println( new Exception("\ntwo identical numbers in the same column, try again"));
	 				
	 				break;

	 			}
	 			
	 			else 
	 				
	 				i += 9;
	 		}
	 		
	 		i = indx - 9;
	 		num = 0;
	 	
	 		//checking the text fields prior to the current one in the column	 		
	 		while( i >=0 ) {
	 			
	 		
	 			 if(isNumeric(fields[i].getText()))
	 		 		
		 			num = Integer.parseInt(fields[i].getText());
	 			
	 			 if(number == num) {
	 				
	 				System.out.println( new Exception("\ntwo identical numbers in the same column, try again"));
	 				
	 				break;

	 			}
	 			
	 			else 
	 				
	 				i -= 9;
	 		}
	 		
	 		i = indx+1;
	 		
	 		num = 0;
	 		
	 		//checking the text fields who follow the current one in the row
	 		while( i < SIZE*SIZE  && i%SIZE >=0  ) {
	 			
	 			
	 			if((i-1)%9 == 0)
	 				break;
	 			 if(isNumeric(fields[i].getText()))
	 		 		
		 			num = Integer.parseInt(fields[i].getText());
	 			 
	 			 if(number == num) {
	 				
	 				System.out.println( new Exception("\ntwo identical numbers in the same row, try again"));
	 				
	 				break;
	 			}
	 			
	 			else 
	 				
	 				i++;
	 		}
	 		
	 		i = indx-1;
	 		num = 0;
	 		
	 		//checking the text fields prior to the current one in the row
	 		while( i < SIZE*SIZE && i%SIZE >=0 ) {
	 			
 				
	 			if((i+1)%SIZE == 0)
	 				break;
	 			
	 			 if(isNumeric(fields[i].getText()))
	 		 		
		 			num = Integer.parseInt(fields[i].getText());
	 			 
	 			 if(number == num) {
	 				
	 				System.out.println( new Exception("\ntwo identical numbers in the same row, try again"));
	 				
	 				break;

	 			}
	 			
	 			else 
	 				
	 				i--;
	 		}
	 		
	 	//
	 		
	 		if(numRow % SIZE < 3 && numRow % SIZE >= 0) {
	 			
	 			if(numCol % SIZE < 3 &&  numCol % SIZE >= 0)
	 				
	 				searchSqr(0, 0,  indx,  number,  numRow,  numCol);
	 			
	 			if(numCol % SIZE < 6 &&  numCol % SIZE >= 3)
	 				
	 				searchSqr(0, 3,  indx,  number,  numRow,  numCol);
	 				
	 			if(numCol % SIZE < SIZE &&  numCol % SIZE >= 6)
	 				
	 				searchSqr(0, 6,  indx,  number,  numRow,  numCol);
	 		
	 		}
	 		
	 	//look for the value through a square of 9 text fields
	 		
	 		//check which square to look at
	 			
	 		if(numRow % SIZE < 6 &&  numRow % SIZE >= 3) {

	 			if(numCol % SIZE < 3 &&  numCol % SIZE >= 0)
	 				
	 				searchSqr(0, 27,  indx,  number,  numRow,  numCol);
	 			
	 			if(numCol % SIZE < 6 &&  numCol % SIZE >= 3)
	 				
	 				searchSqr(0, 30,  indx,  number,  numRow,  numCol);
	 				
	 			if(numCol % SIZE < SIZE &&  numCol % SIZE >= 6)
	 				
	 				searchSqr(0, 33,  indx,  number,  numRow,  numCol);
	 		}
	 				
	 		
	 			
	 	    if(numRow % SIZE < SIZE &&  numRow % SIZE >= 6) {
	 	    	
	 	    	if(numCol % SIZE < 3 &&  numCol % SIZE >= 0)
	 				
	 				searchSqr(0, 54,  indx,  number,  numRow,  numCol);
	 			
	 			if(numCol % SIZE < 6 &&  numCol % SIZE >= 3)
	 				
	 				searchSqr(0, 57,  indx,  number,  numRow,  numCol);
	 				
	 			if(numCol % SIZE < SIZE &&  numCol % SIZE >= 6)
	 				
	 				searchSqr(0, 60,  indx,  number,  numRow,  numCol);  
	 	  
	 	    }
	 		
	 	}
	 	//look for the value through a square of 9 text fields
	 	private void searchSqr(int num, int i, int indx, int number, int numRow, int numCol) {
	 		
	 		int count3 = 1;
	 		
	 		int count33 = 1;
	 
	 		
	 			while(i < SIZE*SIZE && count33 <= 3) {
 				
	 				if (indx == i) {
	 				
	 					i++;
	 					
	 					continue;
	 				}
		 			 if(isNumeric(fields[i].getText()))
		 		 		
			 			num = Integer.parseInt(fields[i].getText());
		 			 
		 			 if(number == num) {
		 				
		 				System.out.println( new Exception("\ntow identical numbers in the same square, try again"));
		 				
		 				break;
		 			}
		 			 
		 			 else i++;
		 			 
		 			count3++;
	 				
	 				if(count3 == 3) {
	 				
	 					i += 7;
	 					
	 					count3 = 1;
	 					
	 					count33++;
	 				
	 				}
	 				
	 		}
	 		
	 	}
	 	

	 	//check if a string is a number
	 	public static boolean isNumeric(String strNum) {
	 	    if (strNum == null) {
	 	        return false;
	 	    }
	 	    try {
	 	        double d = Double.parseDouble(strNum);
	 	    } catch (NumberFormatException nfe) {
	 	        return false;
	 	    }
	 	    return true;
	 	}
	 

		@FXML
	    void btnClear(ActionEvent event) {
			//clear the board
			grid.getChildren().clear();
			//initialize it
			initialize();
	    	
	    }

	    @FXML
	    void setbtn(ActionEvent event) {
	    	

	    	TextField textField = new TextField();
	    	textField.getStyleClass().add("custom");
	    	
	    	//loop through all text fields and setting the values entered and coloring their text fields 
	    	for(int i=0; i<SIZE*SIZE; i++) {
	    	
	    	String a = fields[val].getText();
	    	
	    	boolean b = isNumeric(a);
	    	
	    		
	    	 if(b == true && Integer.parseInt(fields[val].getText()) <= 9 && Integer.parseInt(fields[val].getText()) > 0  ) {
	    		
	    		 fields[val].setStyle("-fx-background-color: SALMON;");
	    	
	    		fields[val].setDisable(true);
	    		
	    	}
	    	
	    			    
	    	 val++;

	    	
	    	}
	    	
	    	val = 0;
	    
	  
	    }
}
