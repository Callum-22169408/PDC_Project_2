package pdc_project_2;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

//testing cases: tests my classes for logic etc
//These tests are mostly to do with Aces and the dealer hitting or standing 
//because on my UI test these things were difficult to get tuned. 

public class GameLogicTest {
    private Hand hand;

    @Before
    public void setUp() {
        hand = new Hand();
    }

    // Tests what a hand value is with no aces. 
    @Test
    public void testGetHandValue_NoAces() {
        hand.addCard(new Card("10", "H"));
        hand.addCard(new Card("7", "D"));
        assertEquals(17, hand.getHandValue());
    }

    // Tests whether a hand with ace will bust 
    @Test
    public void testGetHandValue_AceAsEleven() {
        hand.addCard(new Card("A", "S"));
        hand.addCard(new Card("7", "C"));
        assertEquals(18, hand.getHandValue());
    }

    // Tests if a hand with Ace as a 1 will bust or not
    @Test
    public void testGetHandValue_AceAsOne() {
        hand.addCard(new Card("A", "S"));
        hand.addCard(new Card("9", "D"));
        hand.addCard(new Card("8", "C"));
        assertEquals(18, hand.getHandValue());
    }

    // Tests whether a dealer will bust on a 17 or not (hit or stand)
    @Test
    public void testDealerStandsOnSeventeen() {
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        // No need to shuffle or deal, just set up the hand
        dealer.getHand().addCard(new Card("10", "H"));
        dealer.getHand().addCard(new Card("7", "S"));
        dealer.playTurn(deck);
        assertEquals(17, dealer.getHand().getHandValue());
    }

    // Tests if a hand will bust over 21
    @Test
    public void testHandBusts() {
        hand.addCard(new Card("10", "H"));
        hand.addCard(new Card("9", "S"));
        hand.addCard(new Card("5", "D"));
        assertTrue(hand.getHandValue() > 21);
    }
}
