package Difficulty;

import Weapon.AdvancedWeapon;
import Weapon.WeaponIF;
import Character.CharacterIF;
import Character.AdvancedCharacter;


public class AdvancedFactory implements DifficultyFactoryIF {
    @Override
    public CharacterIF createCharacter() {
        return new AdvancedCharacter();
    }

    @Override
    public WeaponIF createWeapon() {
        return new AdvancedWeapon();
    }
}

