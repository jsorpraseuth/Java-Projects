package Character;

import Weapon.WeaponIF;

import java.util.List;

public interface CharacterIF {
    public void setCharacterName(String name);
    public void setCharacterType(String type);
    public void setCurrentWeapon(WeaponIF currentWeapon) throws InvalidWeaponException;
    public void setWeapons(List<WeaponIF> weaponList) throws TooManyWeaponsException;
    public void doDamage(CharacterIF victim);
    public void takeDamage(WeaponIF weaponUsed);
    public List<WeaponIF> getWeapons();
    public int getHealth();
    public String getCharacterName();
    public String getCharacterType();
    public int getMaxInventory();
}


