package Difficulty;

import Weapon.BeginnerWeapon;
import Weapon.WeaponIF;
import Character.CharacterIF;
import Character.BeginnerCharacter;


public class BeginnerFactory implements DifficultyFactoryIF {
    @Override
    public CharacterIF createCharacter() {
        return new BeginnerCharacter();
    }

    @Override
    public WeaponIF createWeapon() {
        return new BeginnerWeapon();
    }
}
