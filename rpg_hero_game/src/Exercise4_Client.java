import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;

import Character.CharacterIF;
import Character.TooManyWeaponsException;
import Character.InvalidWeaponException;
import Difficulty.DifficultyFactoryIF;
import Difficulty.DifficultyType;
import Weapon.WeaponIF;
import Weapon.WeaponTypes;

public class Exercise4_Client {
    private Scanner inputScanner;
    private DifficultyFactoryIF factory;
    private CharacterIF player;
    private CharacterIF opponent;


    /**************************************************************/
    /*                    DIFFICULTY SELECTION                    */

    /**************************************************************/
    public Enum<DifficultyType> difficultySelection() {
        inputScanner = new Scanner(System.in);
        String difficultyInput = "";
        while (!(difficultyInput.equalsIgnoreCase("beginner")
                || difficultyInput.equalsIgnoreCase("intermediate")
                || difficultyInput.equalsIgnoreCase("advanced"))) {

            System.out.println("\n------------------------------------------------------");
            System.out.printf("%50s\n", "Please select a difficulty for your adventure");
            System.out.printf("%42s\n", "Beginner, Intermediate, Advanced");
            System.out.println("------------------------------------------------------");
            difficultyInput = inputScanner.nextLine(); // read user input
            // check
            if (!(difficultyInput.equalsIgnoreCase("beginner")
                    || difficultyInput.equalsIgnoreCase("intermediate")
                    || difficultyInput.equalsIgnoreCase("advanced"))) {
                System.out.print("\nSorry, an invalid difficulty '" + difficultyInput + "' was entered, Please try again..\n");
            }
        }

        DifficultyType diff = DifficultyType.valueOf(difficultyInput.toUpperCase());
        factory = GameUtility.createDifficultyFactory(diff);

        return diff;
    }

    /**************************************************************/
    /*                    CHARACTER GENERATION                    */

    /**************************************************************/
    public void generateCharacters() {
        try {
            inputScanner = new Scanner(new File("Exercises/Exercise4/Exercise4_Group1/src/RandomCharacters.txt"));
            List<CharacterIF> characters = new ArrayList<CharacterIF>();
            while(inputScanner.hasNextLine()) {
                CharacterIF temp = factory.createCharacter();
                temp.setCharacterName(inputScanner.next());
                temp.setCharacterType(inputScanner.next());
                characters.add(temp);
            }

            inputScanner = new Scanner(System.in);
            while(true) {
                System.out.println("\n------------------------------------------------------");
                System.out.printf("%30s\n", "Please select a Character");
                System.out.println("------------------------------------------------------");
                for(CharacterIF character : characters) {
                    System.out.println("-> " + character.getCharacterName());
                }
                System.out.println("------------------------------------------------------");

                String userInput = inputScanner.nextLine();

                CharacterIF foundCharacter = characters.stream()
                        .filter(possible -> possible.getCharacterName().equalsIgnoreCase(userInput))
                        .findFirst()
                        .orElse(null);
                if(foundCharacter == null) {
                    System.out.println("Invalid Character");
                    continue;
                }

                player = foundCharacter;
                characters.remove(foundCharacter);
                Random rand = new Random();
                opponent = characters.get(rand.nextInt(characters.size()));
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**************************************************************/
    /*                     WEAPON GENERATION                      */

    /**************************************************************/
    public void generateWeapons() {

        try {
            inputScanner = new Scanner(new File("Exercises/Exercise4/Exercise4_Group1/src/RandomWeapons.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<WeaponIF> weapons = new ArrayList<WeaponIF>();
        while(inputScanner.hasNextLine()) {
            WeaponIF temp = factory.createWeapon();
            temp.setWeaponName(inputScanner.next());
            temp.setWeaponType(WeaponTypes.valueOf(inputScanner.next()));
            temp.setWeaponDamage(inputScanner.nextInt());
            weapons.add(temp);
        }

        try { // Pick random weapons to assign to each character
            Collections.shuffle(weapons);
            List<WeaponIF> weaponCopy = new ArrayList<WeaponIF>(weapons);
            Collections.shuffle(weaponCopy);

            player.setWeapons(weapons.subList(0, player.getMaxInventory()));
            opponent.setWeapons(weaponCopy.subList(0, opponent.getMaxInventory()));
        } catch (TooManyWeaponsException e) {
            e.printStackTrace();
        }

    }

    /**************************************************************/
    /*                        PLAY GAME                           */
    /**************************************************************/
    public void playGame() {

        System.out.println("------------------------------------------------------");
        System.out.printf("%34s\n", "Time to fight!");
        System.out.println("------------------------------------------------------");

        List<WeaponIF> playerWeapons = player.getWeapons();
        List<WeaponIF> opponentWeapons = opponent.getWeapons();

        System.out.printf("%36s\n", "Player's Weapons");
        System.out.println("------------------------------------------------------");
        playerWeapons.forEach(weapon -> System.out.println("-> " + weapon.getWeaponName()));
        System.out.println("------------------------------------------------------");
        System.out.printf("%36s\n", "Opponent's Weapons");
        System.out.println("------------------------------------------------------");
        opponentWeapons.forEach(weapon -> System.out.println("-> " + weapon.getWeaponName()));
        System.out.println("------------------------------------------------------");
        System.out.println();

        int turn = 1;
        Random rand = new Random();
        while(player.getHealth() > 0 && opponent.getHealth() > 0) {
            try {
                Thread.sleep(750);
                System.out.println("Turn #" + turn);
                if (turn % 2 == 0) {
                    System.out.println("Opponents turn");
                    opponent.setCurrentWeapon(opponentWeapons.get(rand.nextInt(opponentWeapons.size())));
                    opponent.doDamage(player);
                } else {
                    System.out.println("Your turn");
                    player.setCurrentWeapon(playerWeapons.get(rand.nextInt(playerWeapons.size())));
                    player.doDamage(opponent);
                }
            } catch(InvalidWeaponException | InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println();
            turn++;
        }

        System.out.println((player.getHealth() > 0) ? "You Won!!" : "You lost!!");

    }

    public static void main(String[] args) {
        Exercise4_Client client = new Exercise4_Client();
        Enum<DifficultyType> diff = client.difficultySelection();
        client.generateCharacters();
        client.generateWeapons();
        client.playGame();
    }
}


