package Entity_properties;

/**
 * Created by Chris on 19/04/2016.
 */
public interface  CombatCapable
{
    void ifEnemyIsInTargetingRange();
    void ifEnemyIsInFiringRange();
    void ifEnemyIsInFiringCone();
    void fireWeapon();


}
