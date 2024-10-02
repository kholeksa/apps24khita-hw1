package ua.edu.ucu.apps.tempseries;

import java.util.InputMismatchException;
import java.util.Arrays;

public class TemperatureSeriesAnalysis {
    private static final double zeroTemp = -273.0;
    private double[] temperatures;
    private int size;

    public TemperatureSeriesAnalysis() {
        this.temperatures = new double[0];
        this.size = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatures) {
        if (Arrays.stream(temperatures).min().orElse(0) < zeroTemp 
         || temperatures.length == 0) {
            throw new InputMismatchException();
        }
        this.temperatures = Arrays.copyOf(temperatures, temperatures.length);
        this.size = temperatures.length;
    }

    public double average() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }

        return Arrays.stream(temperatures).sum() / size;
    }

    public double deviation() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        
        double average = average();
        double total = 0;
        for (double temp : temperatures) {
            total += (temp - average)*(temp - average);
        }

        return Math.sqrt(total / size);
    }

    public double min() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }

        double min = temperatures[0];
        for (double temp : temperatures) {
            if (temp < min) {
                min = temp;
            }
        }

        return min;
    }

    public double max() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        double max = temperatures[0];
        for (double temp : temperatures) {
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double value) {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        double closest = temperatures[0];
        for (double temp : temperatures) {
            if (Math.abs(temp - value) < Math.abs(closest - value)
                || (Math.abs(temp - value) == Math.abs(closest - value)
                && temp > closest)) {
                closest = temp;
            }
        }
        
        double absClosest = Math.abs(closest);
        if (Arrays.stream(temperatures).anyMatch(temp -> temp == absClosest)) {
            return absClosest;
        }
        return closest;
    }

    public double[] findTempsLessThan(double value) {
        return Arrays.stream(temperatures)
                     .filter(temp -> temp < value)
                     .toArray();
    }

    public double[] findTempsGreaterThan(double value) {
        return Arrays.stream(temperatures)
                     .filter(temp -> temp >= value)
                     .toArray();
    }

    public double[] findTempsInRange(double lower, double upper) {
        return Arrays.stream(temperatures)
                     .filter(temp -> temp >= lower && temp <= upper)
                     .toArray();
    }

    public void reset() {
        temperatures = new double[0];
        size = 0;
    }

    public double[] sortTemps() {
        double[] sorted = Arrays.copyOf(temperatures, size);
        Arrays.sort(sorted);
        return sorted;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }

        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        for (double temp : temps) {
            if (temp < zeroTemp) {
                throw new InputMismatchException();
            }
        }
        if (size + temps.length > temperatures.length) {
            temperatures = Arrays.copyOf(temperatures,
             (size + temps.length) * 2);
        }
        System.arraycopy(temps, 0, temperatures, size, temps.length);
        size += temps.length;
        return size;
    }
}