import com.blackJack.Banque;
import com.blackJack.BlackJack;
import com.blackJack.Carte;
import com.blackJack.Player;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BlackJack jack = new BlackJack();
        jack.creer_cartes();
        jack.melanger_jeu_cartes();
        Player player = new Player();
        Banque dealer = new Banque();
        do {
            int mise = menuStart(player);
            if(mise == -1){
                break;
            }else{
                // start
                NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("sk", "SK"));
                ArrayList<Carte> carteBanque = new ArrayList<>();
                player.setPlayerBalance(player.getPlayerBalance()-mise);
                player.addCartes(jack.piocherNCartes(1));
                dealer.addCartes(jack.piocherNCartes(1));
                if(player.getListCartes().size() == 1) player.addCartes(jack.piocherNCartes(1));
                if(dealer.getListCartes().size() == 1) carteBanque = jack.piocherNCartes(1);
                System.out.println("Sum point dealer: " + dealer.sumPoint());
                dealer.getListCartes().stream().forEach(carte -> System.out.println(carte.toString()));
                System.out.println("Sum point player: " + player.sumPoint());
                player.getListCartes().stream().forEach(carte -> System.out.println(carte.toString()));
                // Tirer ou rester Player
                while(Main.menuPlayer(player) == 1) {
                    player.addCartes(jack.piocherNCartes(1));
                    System.out.println("Sum point player: " + player.sumPoint());
                    player.getListCartes().stream().forEach(carte -> System.out.println(carte.toString()));
                    if (player.sumPoint()>21)break;
                }
                dealer.getListCartes().stream().forEach(carte -> System.out.println(carte.toString()));
                if (player.sumPoint()>21){
                    System.out.println("Perdu");
                }else {
                    // Add carte cachee
                    dealer.addCartes(carteBanque);
                    System.out.println("Sum point Dealer: " + dealer.sumPoint());
                    dealer.getListCartes().stream().forEach(carte -> System.out.println(carte.toString()));
                    if(player.sumPoint() == 21 && dealer.sumPoint()<21){
                        player.setPlayerBalance(player.getPlayerBalance()+mise*1.5);
                        System.out.println("BlackJack: Gagne "+mise*1.5);
                    }else if(player.sumPoint() < dealer.sumPoint() && dealer.sumPoint()>=17){
                        System.out.println("Perdu");
                    }else if(player.sumPoint() == dealer.sumPoint() && dealer.sumPoint()>=17){
                        System.out.println("Egalite");
                        player.setPlayerBalance(player.getPlayerBalance()+mise);
                    }else{
                        // Tirer Dealer tant que sumPoint < 17
                        while(dealer.sumPoint() < 17) {
                            dealer.addCartes(jack.piocherNCartes(1));
                            System.out.println("Sum point Dealer: " + dealer.sumPoint());
                            dealer.getListCartes().stream().forEach(carte -> System.out.println(carte.toString()));
                        }
                        // Verfiy BlackJack
                        if(player.sumPoint() == 21 && dealer.sumPoint() < 21){
                            player.setPlayerBalance(player.getPlayerBalance()+ mise*2.5);
                            System.out.println("BlackJack: Gagne "+mise*2.5);
                        }else if(player.sumPoint() > dealer.sumPoint()){
                            System.out.println("Gagne");
                            player.setPlayerBalance(player.getPlayerBalance()+mise*2);
                        }else if(player.sumPoint() == dealer.sumPoint()){
                            System.out.println("Egalite");
                            player.setPlayerBalance(player.getPlayerBalance()+mise);
                        }else if(player.sumPoint() < dealer.sumPoint()) {
                            System.out.println("Perdu");
                        }else System.out.println("erro logique!!!!");

                    }
                }
                jack.addDefausserCartes(player.getListCartes());
                jack.addDefausserCartes(dealer.getListCartes());
                player.clearList();
                dealer.clearList();

                if(jack.getCartes().size() <= 13){
                    System.out.println("----> "+jack.getCartes().size());
                    jack.defausserCartes();
                    jack.melanger_jeu_cartes();
                    System.out.println("----> "+jack.getCartes().size()+" Millange done");
                }
            }
        }while (player.getPlayerBalance()>0);


    }

    public static int menuStart(Player player){
        try {
            Scanner sc = new Scanner(System.in);
            NumberFormat sk = NumberFormat.getCurrencyInstance(new Locale("sk", "SK"));
            System.out.println("===========================BLACKJACK===========================");
            System.out.println("===========================Your Balance: " + sk.format(player.getPlayerBalance()) + " ===========");
            System.out.println("=========================== 1-[Mise]");
            System.out.println("=========================== 2-[Quiter]");
            int mise = sc.nextInt();
            if(mise != 1 && mise != 2){
                throw new Exception("Error!! Please taper 1 ou 2: ");
            }
            if( mise == 1){
                System.out.println("Combient d'argent vous-voulez misee: Votre prix: " + sk.format(player.getPlayerBalance()));
                System.out.println("(Max mise "+ sk.format(player.getPlayerBalance())+ "):");
                mise =  sc.nextInt();
                while (mise>player.getPlayerBalance()){
                    System.out.println("Error, Mise no valide: (Max mise: "+ sk.format(player.getPlayerBalance())+ "):");
                    mise =  sc.nextInt();
                }
                return mise;
            }else{
                return -1;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return menuStart(player);
        }
    }
    public static int menuPlayer(Player player){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("===========================Menu Player===========================");
            System.out.println("===========================1-[Tirer]");
            System.out.println("===========================2-[Rester]");
            int choix = sc.nextInt();
            if(!Arrays.toString(new int[]{1,2}).contains(choix+"")){
                throw new Exception("Error!! Please taper 1, 2: ");
            }
            return choix;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return menuPlayer(player);
        }
    }

}