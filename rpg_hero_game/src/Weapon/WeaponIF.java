package Weapon;

public interface WeaponIF {
    public void setWeaponName(String weaponName);
    public void setWeaponDamage(int weaponDamage);
    public void setWeaponType(Enum<WeaponTypes> weaponType);
    public Enum<WeaponTypes> getWeaponType();
    public String getWeaponName();
    public int calculateDamage();
}

