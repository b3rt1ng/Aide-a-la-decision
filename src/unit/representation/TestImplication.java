package unit.representation;

import representationtests.ImplicationTests;

/**
 * Class containing all functions to tests all functions of the class Implication.
 * 
 * @author Antoine Collenot, Kenzo Lecoindre
 */
public class TestImplication 
{
    /**
     * Launch all the tests concerning this class.
     * 
     * @return true if all tests are ok, false otherwhise.
     */
    public static boolean allTests()
    {
        boolean ok = true;

        ok = ok && ImplicationTests.testGetScope();
        ok = ok && ImplicationTests.testIsSatisfiedBy();

        System.out.println(ok ? " All tests OK " : " At least one test KO " );

        return ok;
    }    
}
