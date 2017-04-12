/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package assign5;

//import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
//import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import java.io.FileNotFoundException;
import javafx.scene.text.TextAlignment;


/**
 *
 * @author Julianne Nuetzel
 */
public class Assign5 extends Application {
    
      ArrayList<dish> dishList = new ArrayList<>();
      
        String name = "";
        String descrip =""; //will be readin from file
        double price = 0.0; //price is not working :( 
        String image =""; 
       
        
        Label desLbl = new Label();
        Label priceLbl = new Label();
        ImageView imgLbl = new ImageView(); //not label
        Label nameLbl = new Label();
        static int currState = 0; //will keep track of which dish customer is viewing
       
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {        
         try{//READS in file from the path 
          Scanner scan = new Scanner(getClass().getResourceAsStream("/resources/config.txt"));
           while(scan.hasNextLine()){ 
               int caseNum = 0; 
                   for(int i = 0; i < 4; i++){ //four for the number of fields being read in 
                       String nextLine = scan.nextLine();
                         if (nextLine.contains("Name: "))
                            caseNum =1;                        
        
                         else if (nextLine.contains("Description: "))
                           caseNum =2;                           
                       
                         else if (nextLine.contains("Price: "))
                           caseNum =3;                          
                   
                         else if(nextLine.contains("Image: "))
                           caseNum =4;
                       
                        switch (caseNum){
                            case 1: 
                             name = nextLine; //should get the entirty of next line 
                             System.out.println("Name:" + name);
                             break;
                            
                            case 2:
                             descrip = nextLine;
                             System.out.println("Description: " + descrip);
                             break;
                            
                            case 3:
                              //String sprice = nextLine; //this may need a cast  
                              price = Double.parseDouble(nextLine.substring(7));
                              System.out.println(/*"Price: " + */ price); //may need a cast 
                              break;
                              
                            case 4: 
                              // String imageName = nextLine.substring(7);
                              //System.out.println( "Image file name: "+ imageName);
                              //URL url = getClass().getResource;
                              //image = ("/images/"+ imageName);
                              image = "/images/" + nextLine.substring(7);
                              break;
                            
                            default: 
                                System.out.println("Error or end of file");
                                break;                              
                            
                        }//end switch                                      
                   }//end for
              
                dish newDish = new dish (name, descrip, price, image); 
                dishList.add(newDish);
               // System.out.println("DISH price" + dish.price); // remove later
            }//end while
         }//end try
         
         catch(Exception e){
             System.out.println("Error" + e);
         }
         
            System.out.println( "HERE");
            
             //labels
            nameLbl= new Label(dishList.get(currState).name);
            System.out.println( "name label: " + nameLbl);
            desLbl= new Label(dishList.get(currState).descrip);
            System.out.println( "desLbl label: " + desLbl);
            priceLbl= new Label("Price " + dishList.get(currState).price);
            System.out.println( "price label: " + priceLbl);
            imgLbl= new ImageView(dishList.get(currState).image);
            
            System.out.println( "image label: " + imgLbl);
                       
            
            
            //System.out.println( "HERE 2");
            
            
            //fORMATING
           desLbl.setWrapText(true);
           desLbl.setMaxWidth(400);
           imgLbl.setFitHeight(300);
           imgLbl.setFitWidth(300);
            
            //System.out.println( "HERE 3");
           
           //grid panes
           GridPane gp = new GridPane();
           gp.setHgap(15);
           gp.setVgap(15);
           gp.add(nameLbl, 2,1,1,1); 
           gp.add(imgLbl,2,2,1,1); 
           gp.add(desLbl,2,3,2,1);
           gp.add(priceLbl,2,4,2,1);
          
            System.out.println( "HERE 4");
            //buttons 
            //previous
            Button prevBtn = new Button();
            gp.add (prevBtn, 1,5,1,1);
            prevBtn.setText(" < Prev");
             System.out.println( "HERE 5");
            //next
            Button nxtBtn = new Button();
            gp.add(nxtBtn, 3,5,1,1);
            nxtBtn.setText("Next >");
            
             System.out.println( "HERE 6");
            
            //prevBtn.setOnAction( new EventHandler<ActionEvent>()){
          prevBtn.setOnAction(new EventHandler<ActionEvent>() {  
              
               
              @Override
                // System.out.println( "HERE 7");
                  public void handle( ActionEvent event){
                         System.out.println( "HERE 8");
                      if (currState != 0){
                        currState = currState -1;
                         System.out.println( "HERE 9");
                         
                     //CLEAR grid pane    
                   gp.getChildren().remove(nameLbl);
                   gp.getChildren().remove(desLbl);
                   gp.getChildren().remove(imgLbl);
                   gp.getChildren().remove(priceLbl);
                    //gp.getChildren().clear(); //removes the buttons
                   
                    //not sure if this is going to work
                   ///  gp.add(nxtBtn, 3,5,1,1);
                   // nxtBtn.setText("Next >");
                      //labels
                      nameLbl= new Label(dishList.get(currState).name);
                      imgLbl= new ImageView(dishList.get(currState).image);
                      desLbl= new Label(dishList.get(currState).descrip);
                      priceLbl= new Label("Price " + dishList.get(currState).price);
                     System.out.println( "HERE 10");
                     //grid pane 
                     //GridPane gp = GridPane();
                     // gp.setHgap(15);
                      //gp.setVgap(15);
                      
                      desLbl.setWrapText(true);
                      desLbl.setMaxWidth(400);
                      imgLbl.setFitHeight(300);
                      imgLbl.setFitWidth(300);
                      //gridpanes
                      // System.out.println( "HERE 11");
                           gp.add(nameLbl, 2,1,1,1); 
                           gp.add(imgLbl,2,2,1,1); 
                           gp.add(desLbl,2,3,2,1);
                           gp.add(priceLbl,2,4,2,1);
                                
                    }//end if 
                       //System.out.println( "HERE 12");
            }//end handle
                   //System.out.println( "HERE 13");
    });//end setAction prev
            
           System.out.println( "HERE 14 ");
          
         //nxtBtn.setOnAction(new EventHandler<ActionEvent>()){
    nxtBtn.setOnAction(new EventHandler<ActionEvent>() {   
        @Override
            public void handle(ActionEvent event){
             if (currState != dishList.size() -1){
                  currState = currState+ 1;
                        
                     //CLEAR grid pane    
                  gp.getChildren().remove(nameLbl);
                   gp.getChildren().remove(desLbl);
                   gp.getChildren().remove(imgLbl);
                   gp.getChildren().remove(priceLbl);
                  // gp.getChildren().clear();
                   
                    //labels
                    nameLbl= new Label(dishList.get(currState).name);
                    imgLbl= new ImageView(dishList.get(currState).image);
                    desLbl= new Label(dishList.get(currState).descrip);
                    priceLbl= new Label("Price: " + dishList.get(currState).price);
            
                    //grid pane 
                 //GridPane gp = GridPane();
                // gp.setHgap(15);
                // gp.setVgap(15);
                //formatting
                desLbl.setWrapText(true);
                desLbl.setMaxWidth(400);
                imgLbl.setFitHeight(300);
                imgLbl.setFitWidth(300);
                
                //gp.add(lblName, column index, row index, coulms span, row span) 
                
                  gp.add(nameLbl, 2,1,1,1); 
                  gp.add(imgLbl,2,2,1,1); 
                  gp.add(desLbl,2,3,2,1);
                  gp.add(priceLbl,2,4,2,1);
                                
             }//end if                 
                    
                    
        }//end handle
    });//end setAction next
           
        //StackPane root = new StackPane();
        //root.getChildren().add(btn);
        
        Scene screen = new Scene (gp, 550, 500);
        
        primaryStage.setTitle("Menu Navigation System");
        primaryStage.setScene(screen);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
