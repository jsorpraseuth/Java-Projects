package Difficulty;

import Weapon.IntermediateWeapon;
import Weapon.WeaponIF;
import Character.CharacterIF;
import Character.IntermediateCharacter;


public class IntermediateFactory implements DifficultyFactoryIF {
    @Override
    public CharacterIF createCharacter() {
        return new IntermediateCharacter();
    }

    @Override
    public WeaponIF createWeapon() {
        return new IntermediateWeapon();
    }
}
