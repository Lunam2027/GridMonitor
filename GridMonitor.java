import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class GridMonitor implements GridMonitorInterface
{
    private double[][] grid;
    public int dimX;
    public int dimY;

    public GridMonitor(String filename) throws FileNotFoundException
    {
        Scanner s = new Scanner(new File(filename));
        String[] dimStr = s.nextLine().split("\\s");
        dimX = Integer.parseInt(dimStr[0]);
        dimY = Integer.parseInt(dimStr[1]);
        grid = new double[dimX][dimY];
        int j = 0;
        while (s.hasNextLine()) {
            String[] row = s.nextLine().split("\\s");
            for ( int i = 0; i < dimY; i++ ) {
                double bingo = Double.parseDouble(row[i]);
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
        double[][] surroundingSumGrid;
        surroundingSumGrid = new double[dimX][dimY];
        grid = getBaseGrid();

        for (int i = 0; i < dimX; i++){
            for (int j = 0; j < dimY; j++){
                if (i + 1 < dimX) {
                    surroundingSumGrid[i][j] += (grid[i + 1][j]);
                } else {
                    surroundingSumGrid[i][j] += grid[i][j];
                }

                if (j + 1 < dimY) {
                    surroundingSumGrid[i][j] += (grid[i][j + 1]);
                } else {
                    surroundingSumGrid[i][j] += grid[i][j];}; 

                if (i - 1 >= 0) {
                    surroundingSumGrid[i][j] += (grid[i - 1][j]);
                } else {
                    surroundingSumGrid[i][j] += grid[i][j];};

                if (j - 1 >= 0) {
                    surroundingSumGrid[i][j] += (grid[i][j - 1]);
                } else {
                    surroundingSumGrid[i][j] += grid[i][j];};
            }
        }
        return surroundingSumGrid;
    }

    @Override 
    public double[][] getSurroundingAvgGrid() {

        double[][] SurroundingAvgGrid;
        SurroundingAvgGrid = new double[dimX][dimY];
        grid = getSurroundingSumGrid();

        for (int i = 0; i < dimX; i++){
            for (int j = 0; j < dimY; j++){
                double num;
                num = grid[i][j];

                double DIVISOR;
                DIVISOR = 4.0;
                num = num / DIVISOR;

                SurroundingAvgGrid[i][j] = num;
            }
        }

        return SurroundingAvgGrid;
    }

    @Override
    public double[][] getDeltaGrid() {
      
        double[][] deltaGrid;
        deltaGrid = new double[dimX][dimY];
        grid = getSurroundingAvgGrid();

        for (int i = 0; i < dimX; i++){
            for (int j = 0; j < dimY; j++){
                double num;
                num = grid[i][j];

                double DIVISOR;
                DIVISOR = 2.0;
                num = num / DIVISOR;

                deltaGrid[i][j] = Math.abs(num);
            }
        }

        return deltaGrid;
        
    }

    @Override
    public boolean[][] getDangerGrid() {
        boolean[][] DangerGrid;
        DangerGrid = new boolean[dimX][dimY];

        grid = getBaseGrid();
        double[][] deltaGrid = getDeltaGrid();
        double[][] avggrid = getSurroundingAvgGrid();


        for (int i = 0; i < dimX; i++){
            for (int j = 0; j < dimY; j++){
                double num;
                double delta;
                double avg;

                num = grid[i][j];
                delta = deltaGrid[i][j];
                avg = avggrid[i][j];

                if (((avg + delta) >= num) || ((avg - delta) <= num)) {
                    DangerGrid[i][j] = false;
                } else {
                    DangerGrid[i][j] = true;
                };

            }
        }
    


      return DangerGrid;

    }
    
}