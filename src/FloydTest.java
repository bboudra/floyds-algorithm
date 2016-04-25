import org.testng.annotations.Test;

import org.junit.Assert;

/**
 * Created by benjamin on 3/23/16.
 */
public class FloydTest {


    @Test
    public void testFloydsAlgorithm(){
        //given

        Floyd f = new Floyd();
        Double[][] testWeights = {{0.,1.,Double.POSITIVE_INFINITY,1.,5.},
                {9.,0.,3.,2.,Double.POSITIVE_INFINITY},
                {Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,0.,4.,Double.POSITIVE_INFINITY},
                {Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,2.,0.,3.},
                {3.,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,0.}};

        Double[][] expectedWeights= {
                {0.,1.,3.,1.,4.},
                {8.,0.,3.,2.,5.},
                {10.,11.,0.,4.,7.},
                {6.,7.,2.,0.,3.},
                {3.,4.,6.,4.,0.}};

        int[][] expectedIntermediates = {
                {0,0,4,0,4},
                {5,0,0,0,4},
                {5,5,0,0,4},
                {5,5,0,0,0},
                {0,1,4,1,0}
        };

        //when

        Object[] object = f.floydsAlgorithm(testWeights);
        Double[][] actualWeights= (Double[][]) object[0];
        int[][] actualIntermediates = (int[][]) object[1];

        //then
        Assert.assertTrue(isEqual(actualWeights, expectedWeights));
        Assert.assertTrue(isEqual(expectedIntermediates,actualIntermediates));
    }

    public boolean isEqual(int[][] a1, int[][] a2)
    {
        for(int i = 0; i < a1.length; i++)
        {
            for(int j = 0; j < a1.length; j++)
            {
                if( !(a1[i][j] == a2[i][j] + 1)) {
                    System.out.println("The values did not match");
                    System.out.println("Expected: " + a1[i][j]);
                    System.out.println("Actual: " + a1[i][j]);
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isEqual(Double[][] a1, Double[][] a2)
    {
        for(int i = 0; i < a1.length; i++)
        {
            for(int j = 0; j < a1.length; j++)
            {
                if(!approxEqual(a1[i][j],a2[i][j], 3))
                {
                    System.out.println("The values did not match");
                    System.out.println("Expected: " + a1[i][j]);
                    System.out.println("Actual: " + a1[i][j]);
                }

            }
        }
        return true;
    }

    public boolean approxEqual(double val1, double val2, double epsilon)
    {
        return Math.abs(val1 - val2) < epsilon;
    }
}