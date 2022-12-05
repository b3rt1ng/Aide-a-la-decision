package blocksworld.planning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;

import blocksworld.representation.StateBuilder;
import bwmodel.BWState;
import bwmodel.BWStateBuilder;
import bwui.BWIntegerGUI;
import planning.Action;
import planning.BFSPlanner;
import planning.BasicGoal;
import planning.DFSPlanner;
import planning.DijkstraPlanner;
import planning.Planner;
import representation.Variable;

public class MainPlanning 
{
    public static void main(String[] args) 
    {
        int nbBlocks = 3;
        int nbStacks = 3;

        BlocksworldActionFactory actionFactory = new BlocksworldActionFactory(nbBlocks, nbStacks);
        Set<Action> actions = actionFactory.getActions();

        /* ----------------------------------------------------------------------------------- */

        /* Etat initial */
        ArrayList<Integer>[] stacks = new ArrayList[nbStacks];
        for(int i = 0; i < nbStacks; i++)
        {
            stacks[i] = new ArrayList<Integer>();
        }

        stacks[0].add(0);
        stacks[0].add(1);
        stacks[0].add(2);

        StateBuilder stateBuilder = new StateBuilder(nbBlocks, nbStacks, stacks);
        Map<Variable, Object> instanciation = stateBuilder.getState();

        /* ----------------------------------------------------------------------------------- */

        //Etat final
        ArrayList<Integer>[] stacksFinal = new ArrayList[nbStacks];
        for(int i = 0; i < nbStacks; i++)
        {
            stacksFinal[i] = new ArrayList<Integer>();
        }

        stacksFinal[0].add(0);
        stacksFinal[1].add(2);
        stacksFinal[1].add(1);

        stateBuilder = new StateBuilder(nbBlocks, nbStacks, stacksFinal);
        Map<Variable, Object> instanciationFinal = stateBuilder.getState();

        /* ----------------------------------------------------------------------------------- */


        Planner planner;
        long startTime;
        long endTime;
        List<Action> plan;

        //Planning : BFS

        planner = new BFSPlanner(instanciation, actions, new BasicGoal(instanciationFinal));

        startTime = System.currentTimeMillis();
        plan = planner.plan();
        endTime = System.currentTimeMillis();

        System.out.println("BFS :");
        System.out.println("temps d'execution : " + (endTime - startTime) + "ms");
        System.out.println("Noeuds explorés : " + planner.getNbNodes());
        System.out.println("Plan :");
        System.out.println(plan);

        System.out.println("");
        System.out.println("");


        //Planning : DFS

        planner = new DFSPlanner(instanciation, actions, new BasicGoal(instanciationFinal));

        startTime = System.currentTimeMillis();
        plan = planner.plan();
        endTime = System.currentTimeMillis();

        System.out.println("DFS :");
        System.out.println("temps d'execution : " + (endTime - startTime) + "ms");
        System.out.println("Noeuds explorés : " + planner.getNbNodes());
        System.out.println("Plan :");
        System.out.println(plan);

        System.out.println("");
        System.out.println("");


        //Planning : DFS

        planner = new DijkstraPlanner(instanciation, actions, new BasicGoal(instanciationFinal));

        startTime = System.currentTimeMillis();
        plan = planner.plan();
        endTime = System.currentTimeMillis();

        System.out.println("Dijkstra :");
        System.out.println("temps d'execution : " + (endTime - startTime) + "ms");
        System.out.println("Noeuds explorés : " + planner.getNbNodes());
        System.out.println("Plan :");
        System.out.println(plan);

        System.out.println("");
        System.out.println("");

    }    
}