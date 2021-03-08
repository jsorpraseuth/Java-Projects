package Weapon;

public class AdvancedWeapon implements WeaponIF {

    private Enum<WeaponTypes> weaponType;
    private String weaponName;
    private int weaponDamage;
    private final double damageHandicap = 0.5;

    @Override
    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    @Override
    public void setWeaponDamage(int weaponDamage) {
        this.weaponDamage = weaponDamage;
    }

    @Override
    public void setWeaponType(Enum<WeaponTypes> weaponType) {
        this.weaponType = weaponType;
    }

    @Override
    public Enum<WeaponTypes> getWeaponType() {
        return this.weaponType;
    }

    @Override
    public String getWeaponName() {
        return (weaponName == null) ? "Unknown" : this.weaponName;
    }

    @Override
    public int calculateDamage() {
        return (int) Math.round(weaponDamage - weaponDamage * damageHandicap);
    }
}

