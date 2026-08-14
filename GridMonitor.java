import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class GridMonitor implements GridMonitorInterface
{
    private double[][] grid;

    public GridMonitor(String filename) throws FileNotFoundException
    {
        Scanner s = new Scanner(new File(filename));
        String[] dimStr = s.nextLine().split("\\s");
        int dimX = Integer.parseInt(dimStr[0]);
        int dimY = Integer.parseInt(dimStr[1]);
        grid = new double[dimX][dimY];
        int j = 0;
        while (s.hasNextLine()) {
            String[] row = s.nextLine().split("\\s");
            for ( int i = 0; i < dimY; i++ ) {
                int bingo = Integer.parseInt(row[i]);
                grid[j][i] = bingo;
            }
        j++;
        }
        s.close();
    }

    @Override
    public double[][] getBaseGrid() {
        return grid;
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