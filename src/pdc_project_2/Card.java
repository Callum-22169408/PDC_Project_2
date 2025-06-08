/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_project_2;

/**
 *
 * @author User
 */
//Card class is building the deck of cards 
public class Card {
    
        private final String rank; 
        private final String suit; 
    
//Constructor for the deck
public Card(String rank, String suit){
     this.rank = rank; 
     this.suit = suit; 
    }

//Returns the value of the face and ace cards e.g face =10 ace =11
public int getBlackJackValue (){
    switch(rank){
        case "J":
        case "Q":
        case "K":
          return 10; 
        case "A": 
            return 11; 
        default: 
            return Integer.parseInt(rank);
      }
   }

   //Is it an ace or not? that's the real question. 
   public boolean isAce (){
       return "A".equals(rank);
   }
   
    // returns what the card actually is 
   @Override
   public String toString(){
       return rank + "-" + suit; 
   }
   
   //Gets the image files for the cards 
   public String getImageFilePath(){
       return String.format("./cards/%s.png", toString());
   }
}

    
