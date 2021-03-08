package Difficulty;

import Character.CharacterIF;
import Weapon.WeaponIF;

public interface DifficultyFactoryIF {
    public CharacterIF createCharacter();
    public WeaponIF createWeapon();
}

