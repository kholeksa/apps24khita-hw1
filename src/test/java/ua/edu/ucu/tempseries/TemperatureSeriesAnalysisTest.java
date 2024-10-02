package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import java.util.InputMismatchException;

import org.junit.Test;

import ua.edu.ucu.apps.tempseries.TempSummaryStatistics;
import ua.edu.ucu.apps.tempseries.TemperatureSeriesAnalysis;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void test() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expected = -1.0;

        double result = seriesAnalysis.average();
        assertEquals(expected, result, 0.00001);
    }

    @Test
    public void testConstructorInvalidTemperature() {
        double[] temperatureSeries = {1.0, -274.0, 3.0};
        assertThrows(InputMismatchException.class, () -> new TemperatureSeriesAnalysis(temperatureSeries));
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expected = 1.0;

        double result = seriesAnalysis.average();

        assertEquals(expected, result, 0.00001);
    }

    @Test
    public void testAverageEmpty() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        assertThrows(IllegalArgumentException.class, seriesAnalysis::average);
    }
        
    @Test
    public void testDeviation() {
        double[] temperatureSeries = {1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expected = 0.81;

        double result = seriesAnalysis.deviation();
        assertEquals(expected, result, 0.05);
    }

    @Test
    public void testDeviationEmpty() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        assertThrows(IllegalArgumentException.class, seriesAnalysis::deviation);
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {-1.0, 1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expected = -1.0;

        double result = seriesAnalysis.min();
        assertEquals(expected, result, 0.00001);
    }

    @Test
    public void testMinEmptySeries() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        assertThrows(IllegalArgumentException.class, seriesAnalysis::min);
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expected = 3.0;

        double result = seriesAnalysis.max();
        assertEquals(expected, result, 0.00001);
    }

    @Test
    public void testMaxEmptySeries() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        assertThrows(IllegalArgumentException.class, seriesAnalysis::max);
    }

    @Test
    public void testFindTempClosestToZero() {
        double[] temperatureSeries = {-2.0, -1.0, 1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expected = 1.0;

        double result = seriesAnalysis.findTempClosestToZero();
        assertEquals(expected, result, 0.00001);
    }

    @Test
    public void testFindTempClosestToValueWithDifferentNumbers() {
        double[] temperatureSeries = {-5.0, -2.0, 0.0, 3.0, 7.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expected = 3.0;

        double result = seriesAnalysis.findTempClosestToValue(4.0);
        assertEquals(expected, result, 0.00001);
    }

    @Test
    public void testFindTempsLessThan() {
        double[] temperatureSeries = {1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expected = {1.0, 2.0};

        double[] result = seriesAnalysis.findTempsLessThan(3.0);
        assertArrayEquals(expected, result, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThan() {
        double[] temperatureSeries = {1.0, 2.0, 3.0, 4.0, 5.0}; 
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expected = {2.0, 3.0, 4.0, 5.0};

        double[] result = seriesAnalysis.findTempsGreaterThan(2.0);
        assertArrayEquals(expected, result, 0.00001);
    }

    @Test
    public void testFindTempsInRange() {
        double[] temperatureSeries = {1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expected = {1.0, 2.0};

        double[] result = seriesAnalysis.findTempsInRange(1.0, 2.0);
        assertArrayEquals(expected, result, 0.00001);
    }

    @Test
    public void testReset() {
        double[] temperatureSeries = {1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.reset();
        assertThrows(IllegalArgumentException.class, seriesAnalysis::min);
    }

    @Test
    public void testSortTemps() {
        double[] temperatureSeries = {3.0, 1.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expected = {1.0, 2.0, 3.0};

        double[] result = seriesAnalysis.sortTemps();
        assertArrayEquals(expected, result, 0.00001);
    }

    @Test
    public void testSummaryStatistics() {
        double[] temperatureSeries = {1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics stats = seriesAnalysis.summaryStatistics();
        assertEquals(2.0, stats.getAvgTemp(), 0.00001);
        assertEquals(0.8, stats.getDevTemp(), 0.05);
        assertEquals(1.0, stats.getMinTemp(), 0.00001);
        assertEquals(3.0, stats.getMaxTemp(), 0.00001);
    }

    @Test
    public void testSummaryStatisticsEmpty() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        assertThrows(IllegalArgumentException.class, seriesAnalysis::summaryStatistics);
    }

    @Test
    public void testAddTemps() {
        double[] temperatureSeries = {1.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        int expected = 4;

        int result = seriesAnalysis.addTemps(3.0, 4.0);
        assertEquals(expected, result);
        assertArrayEquals(new double[]{1.0, 2.0, 3.0, 4.0}, seriesAnalysis.sortTemps(), 0.00001);
    }

    @Test
    public void testNegativeTemperatures() {
        double[] temperatureSeries = {-1.0, -2.0, -3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(-2.0, seriesAnalysis.average(), 0.00001);
        assertEquals(0.8, seriesAnalysis.deviation(), 0.03);
        assertEquals(-3.0, seriesAnalysis.min(), 0.00001);
        assertEquals(-1.0, seriesAnalysis.max(), 0.00001);
    }

    @Test
    public void testLargeDataset() {
        double[] temperatureSeries = new double[1000];
        for (int i = 0; i < 1000; i++) {
            temperatureSeries[i] = i;
        }
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(499.5, seriesAnalysis.average(), 0.00001);
        assertEquals(288.67499, seriesAnalysis.deviation(), 0.00001);
        assertEquals(0.0, seriesAnalysis.min(), 0.00001);
        assertEquals(999.0, seriesAnalysis.max(), 0.00001);
    }
}

