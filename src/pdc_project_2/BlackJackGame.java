package pdc_project_2;

//Setting up the variables for the game
public class BlackJackGame{
   private Deck deck; 
   private Dealer dealer; 
   private HumanPlayer player;
   private GameState state; 
   
   //Constructor
   public BlackJackGame(HumanPlayer player){
       this.player = player; 
       this.dealer = new Dealer(); 
       this.deck = new Deck();
       this.state = GameState.WAITING;       
   }
   
   public void startNewRound(){
       deck.shuffle();
       player.resetHand();
       dealer.resetHand();
       player.getHand().addCard(deck.dealCard());
       dealer.getHand().addCard(deck.dealCard());
       player.getHand().addCard(deck.dealCard());
       dealer.getHand().addCard(deck.dealCard());
       state = GameState.PLAYER_TURN;
   }
   
    public Dealer getDealer() { return dealer; }
    public HumanPlayer getPlayer() { return player; }
    public Deck getDeck() { return deck; }
    public GameState getState() { return state; }
    public void setState(GameState state) { this.state = state; }
}