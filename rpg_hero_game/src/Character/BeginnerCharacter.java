package Character;

import Weapon.WeaponIF;
import java.util.Collections;
import java.util.List;

public class BeginnerCharacter implements CharacterIF {
    private String characterName;
    private String characterType;
    private final int startingHealth = 100;
    private final int maxInventorySlots = 4;
    private int currentHealth = startingHealth;
    private List<WeaponIF> weaponList;
    private WeaponIF currentWeapon;

    @Override
    public void setCharacterName(String name) {
        this.characterName = name;
    }

    @Override
    public void setCharacterType(String type) {
        this.characterType = type;
    }

    @Override
    public String getCharacterName() {
        return (this.characterName == null) ? "Unknown" : this.characterName;
    }

    @Override
    public String getCharacterType() {
        return (this.characterType == null) ? "Unknown" : this.characterType;
    }

    @Override
    public void setCurrentWeapon(WeaponIF currentWeapon) throws InvalidWeaponException {
        if(!this.weaponList.contains(currentWeapon)) throw new InvalidWeaponException("The character you tried to set the current weapon '' does not have it in their inventory, you must add the weapon first using setWeapons");
        // Also we need to make sure that we are being passed the right type of class eg this one needs to be a beginner weapon
        this.currentWeapon = currentWeapon;
    }

    @Override
    public void setWeapons(List<WeaponIF> weaponList) throws TooManyWeaponsException {
        if (weaponList.size() > maxInventorySlots) throw new TooManyWeaponsException("The max amount of items for a Beginner Character is: " + maxInventorySlots + " You attempted to add " + weaponList.size() + " which exceeds the maximum.");
        this.weaponList = weaponList;
    }

    @Override
    public void takeDamage(WeaponIF weaponUsed) {
        int damageDone = weaponUsed.calculateDamage();
        this.currentHealth = ((this.currentHealth - damageDone) < 0) ? 0 : this.currentHealth - damageDone;
        System.out.println(this.getCharacterName() + " is taking " + damageDone + " damage, " + this.getCharacterName() + " now has " + this.getHealth() + " health.");
    }

    @Override
    public List<WeaponIF> getWeapons() {
        return (this.weaponList == null) ? Collections.emptyList() : this.weaponList;
    }

    @Override
    public int getHealth() {
        return this.currentHealth;
    }

    @Override
    public void doDamage(CharacterIF victim) {
        System.out.println("Beginner Character " + this.getCharacterName() + " is doing damage to " + victim.getCharacterName() + " with Beginner weapon: " +  this.currentWeapon.getWeaponName());
        victim.takeDamage(this.currentWeapon);
        if(victim.getHealth() <= 0) System.out.println(victim.getCharacterName() + " has been killed.");
    }

    @Override
    public int getMaxInventory() {
        return maxInventorySlots;
    }
}


