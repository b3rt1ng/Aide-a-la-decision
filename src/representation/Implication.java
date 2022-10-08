package representation;

import java.util.Map;
import java.util.Set;
import java.util.HashSet;

/**
 * Class representing an Implication.
 * 
 * @author Antoine Collenot
 */

public class Implication implements Constraint {

    Variable v1,v2;
    Set<Object> s1,s2;

    public Implication(Variable v1, Set<Object> s1,Variable v2, Set<Object> s2) {
        this.v1 = v1;
        this.v2 = v2;
        this.s1 = s1;
        this.s2 = s2;    
    }

    
    /** 
     * Returns a set of variables to which the constraint applies.
     * 
     * @return Set<Variable>
     */
    public Set<Variable> getScope() {
        Set<Variable> scope = new HashSet<Variable>();
        scope.add(v1);
        scope.add(v2);
        return scope;
    }

    
    /** 
     * Returns wheter or not an instance satisfy the current constraint.
     * This constraint will only be satisifed if given a v1 affected to a S1 set implies a v2 affected to a S2 set, v1 and v2 being two varaibles.
     * 
     * @param instance
     * @return boolean
     */
    public boolean isSatisfiedBy(Map<Variable, Object> instance) {
        if (!(instance.keySet().contains(this.v1) && instance.keySet().contains(this.v2)))
            throw new IllegalArgumentException("V1 or V2 aren't present in the given instance !");
        
        if ((this.s1.contains(instance.get(this.v1)) && this.s2.contains(instance.get(this.v2)))) {
            return true;
        } else if (!this.s1.contains(instance.get(this.v1))) {
            return true;
        }
        return false;
    }
}
