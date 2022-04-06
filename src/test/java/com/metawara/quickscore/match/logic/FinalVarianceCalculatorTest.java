package com.metawara.quickscore.match.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest(classes = FinalVarianceCalculatorTest.class)
@RunWith(MockitoJUnitRunner.class)
public class FinalVarianceCalculatorTest {

    @Test
    public void testFVCCalculation_noVariances_shouldReturnBaseChanceOfWinning(){
        double chanceOfWinning = 0.5;
        double positiveVar = 0;
        double negativeVar = 0;

        double finalChanceOfWinning = FinalVarianceCalculator.calculate(chanceOfWinning, positiveVar, negativeVar);
        assertEquals(chanceOfWinning, finalChanceOfWinning, 0);
    }

    @Test
    public void testFVCCalculation_negativeVariance_shouldReturnLowerChanceOfWinning(){
        double chanceOfWinning = 0.5;
        double positiveVar = 0;
        double negativeVar = 0.2;

        double finalChanceOfWinning = FinalVarianceCalculator.calculate(chanceOfWinning, positiveVar, negativeVar);
        assertTrue(chanceOfWinning > finalChanceOfWinning);
    }

    @Test
    public void testFVCCalculation_positiveVariance_shouldReturnHigherChanceOfWinning(){
        double chanceOfWinning = 0.5;
        double positiveVar = 0.2;
        double negativeVar = 0;

        double finalChanceOfWinning = FinalVarianceCalculator.calculate(chanceOfWinning, positiveVar, negativeVar);
        assertTrue(chanceOfWinning < finalChanceOfWinning);
    }

    @Test
    public void testFVCCalculation_bothVariances_shouldReturnDifferentChanceOfWinning(){
        double chanceOfWinning = 0.5;
        double positiveVar = 0.4;
        double negativeVar = 0.1;

        double finalChanceOfWinning = FinalVarianceCalculator.calculate(chanceOfWinning, positiveVar, negativeVar);
        assertNotEquals(chanceOfWinning, finalChanceOfWinning);
    }
}
