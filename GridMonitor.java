import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class GridMonitor implements GridMonitorInterface
{
    private double[][] grid;

    public GridMonitor(String filename) throws FileNotFoundException
    {
        Scanner s = new Scanner(new File(filename));

        s.close();
    }

    @Override
    public double[][] getBaseGrid() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSurroundingSumGrid'");
    }

    @Override
    public double[][] getSurroundingSumGrid() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSurroundingSumGrid'");
    }

    @Override
    public double[][] getSurroundingAvgGrid() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSurroundingAvgGrid'");
    }

    @Override
    public double[][] getDeltaGrid() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeltaGrid'");
    }

    @Override
    public boolean[][] getDangerGrid() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDangerGrid'");
    }
    
}