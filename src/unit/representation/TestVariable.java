package unit.representation;

import representationtests.VariableTests;

public class TestVariable
{
    public static boolean allTests()
    {
        boolean ok = true ;

        ok = ok && VariableTests.testEquals();
        ok = ok && VariableTests.testHashCode();

        System.out.println(ok ? " All tests OK " : " At least one test KO " );

        return ok;
    }
}