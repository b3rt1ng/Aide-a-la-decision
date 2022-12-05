package blocksworld.representation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import representation.BooleanVariable;
import representation.Variable;

public class StateBuilder 
{
    protected ArrayList<Integer>[] stacks;
    protected ArrayList<Variable> variables;

    public StateBuilder(int nbBlocks, int nbStacks, ArrayList<Integer>[] stacks)
    {
        this.stacks = stacks;

        World world = new World(nbBlocks, nbStacks);
        this.variables = new ArrayList<>(world.getVariables());
    }

    public Map<Variable, Object> getState()
    {
        HashMap<Variable, Object> state = new HashMap<Variable, Object>();

        for(int i = 0; i < this.stacks.length; i++)
        {
            int stack = -1-i;

            state.put(this.variables.get(this.variables.indexOf(new BooleanVariable("free"+stack))), true);

            //On traite le premier élément de la pile et on traite la pile
            if(this.stacks[i].size() > 0)
            {
                int block = this.stacks[i].get(0);
                
                state.put(this.variables.get(this.variables.indexOf(new Variable("on"+block, null))), stack);
                state.put(this.variables.get(this.variables.indexOf(new Variable("free"+stack, null))), false);
            }
            else
            {
                state.put(this.variables.get(this.variables.indexOf(new Variable("free"+stack, null))), true);
            }

            //On traite tout les blocs sur la pile i de l'ind 1 à l'ind n-1
            for(int j = 1; j < this.stacks[i].size(); j++)
            {
                int block = this.stacks[i].get(j);
                int blockAvant = this.stacks[i].get(j-1);
                state.put(this.variables.get(this.variables.indexOf(new Variable("on"+block, null))), blockAvant);
                state.put(this.variables.get(this.variables.indexOf(new Variable("fixed"+blockAvant, null))), true);
                state.put(this.variables.get(this.variables.indexOf(new Variable("fixed"+block, null))), false);
            }
        }

        return state;
    } 
    
    public void stateShuffler(int nb)
    {
        for(int i=0 ; i<nb ; i++)
        {
            //on fait la liste des piles non vides
            ArrayList<Integer> listPileNotEmpty = new ArrayList<Integer>();
            for(int j=0 ; j<this.stacks.length ; j++)
            {
                if(this.stacks[j].size() > 0)
                {
                    listPileNotEmpty.add(j);
                }
            }

            //on choisit une pile non vide au hasard
            Random rand = new Random();
            int pile = listPileNotEmpty.get(rand.nextInt(listPileNotEmpty.size()));

            //on prend le dernier bloc de la pile
            int block = this.stacks[pile].get(this.stacks[pile].size()-1);

            //on l'efface de la pile
            this.stacks[pile].remove(this.stacks[pile].size()-1);

            //on choisit une pile au hasard
            int pileFinal = rand.nextInt(this.stacks.length);

            //on ajoute le bloc à la pile
            this.stacks[pileFinal].add(block);
        }
    }
}
