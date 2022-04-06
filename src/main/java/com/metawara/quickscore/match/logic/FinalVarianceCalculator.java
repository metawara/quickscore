package com.metawara.quickscore.match.logic;

import com.metawara.quickscore.utils.RandomSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class FinalVarianceCalculator {

    private static final Logger logger = LoggerFactory.getLogger(FinalVarianceCalculator.class);
    static Random rand = RandomSingleton.getInstance().getRnd();

    private FinalVarianceCalculator() {
    }

    public static double calculate(double baseVariance, double positiveVar, double negativeVar) {
        double finalPositiveVar = round(rand.nextDouble() * positiveVar);
        logger.debug("Calculated finalPositiveVar [{}] for positiveVar [{}]", finalPositiveVar, positiveVar);
        double finalNegativeVar = round(rand.nextDouble() * negativeVar);
        logger.debug("Calculated finalNegativeVar [{}] for negativeVar [{}]", finalNegativeVar, negativeVar);
        double finalVariance =  baseVariance + finalPositiveVar - finalNegativeVar;
        logger.debug("Calculated finalVariance [{}] for base variance [{}]", finalVariance, baseVariance);
        return finalVariance;
    }

    private static double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(3, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
