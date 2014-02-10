//import Card.java;
import java.io.*;
import java.util.*;


public class main{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Hello welcome to 5 Card Poker.");
		System.out.println("How many computer opponents do you want to face (0-3 only)? ");
		int opponents = Integer.parseInt(scanner.nextLine());

		while(opponents < 0 || opponents >= 4){
			System.out.println("Value out of bounds please enter a new value: ");
			opponents = Integer.parseInt(scanner.nextLine());
		}

		List<List<Card>> all_players = new LinkedList<List<Card>>();
		List<Card> player_hand = new ArrayList<>();
		List<Card> ai_hand1 = new ArrayList<>();
		List<Card> ai_hand2 = new ArrayList<>();
		List<Card> ai_hand3 = new ArrayList<>();
		all_players.add(player_hand);

		if(opponents == 1){
			all_players.add(ai_hand1);
		}
		else if (opponents == 2){
			all_players.add(ai_hand1);
			all_players.add(ai_hand2);
		}
		else if (opponents == 3){
			all_players.add(ai_hand1);
			all_players.add(ai_hand2);
			all_players.add(ai_hand3);		
		}

		System.out.println("\nThe deck is being shuffled...\n");
		CardPile discardpile = new CardPile();
		CardPile deck = new CardPile(true);
		
		/*List<Card> temp = new ArrayList<>();

		temp.add(new Card('T', 'C'));
		temp.add(new Card('7', 'D'));
		temp.add(new Card('5', 'C'));
		temp.add(new Card('4', 'D'));
		temp.add(new Card('2', 'D'));

		handEvaluator.hand_pairing(temp);
		AIPlayer.discard_draw(temp, deck, discardpile, 1);
		*/

		System.out.println("Cards will now be dealt to " + (opponents + 1) + " players.\n");
		deck.deal_cards(all_players);
		

		//Sorting the hands by value/pairings:
		for(int i = 0; i < all_players.size(); i++){
			handEvaluator.hand_pairing(all_players.get(i));
		}

		//printing all the hands 
		UserPlayer.print_phand(player_hand);
		for(int i = 1; i < all_players.size(); i++){
			System.out.print("Computer hand: ");
			AIPlayer.print_aihand(all_players.get(i));
		}
		

		UserPlayer.discard_draw(player_hand, deck, discardpile);
       	for(int i = 1; i < all_players.size(); i++){
			AIPlayer.discard_draw(all_players.get(i), deck, discardpile, i);
		}
       	//int val1  = handEvaluator.hand_calculator(player_hand);


		System.exit(0);
	}
}