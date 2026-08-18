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

        double[][] gridCopy;
        gridCopy = new double[dimX][dimY];

        for (int i = 0; i < dimX; i++){
            for (int j = 0; j < dimY; j++){
                gridCopy[i][j] = grid[i][j];
            }
        }
        return gridCopy;
    }

 @Override
    public double[][] getSurroundingSumGrid() {
        double[][] base = getBaseGrid();  
        double[][] surroundingSumGrid = new double[dimX][dimY];

        for (int i = 0; i < dimX; i++){
            for (int j = 0; j < dimY; j++){
                if (i + 1 < dimX) surroundingSumGrid[i][j] += base[i + 1][j];
                else surroundingSumGrid[i][j] += base[i][j];

                if (j + 1 < dimY) surroundingSumGrid[i][j] += base[i][j + 1];
                else surroundingSumGrid[i][j] += base[i][j];

                if (i - 1 >= 0) surroundingSumGrid[i][j] += base[i - 1][j];
                else surroundingSumGrid[i][j] += base[i][j];

                if (j - 1 >= 0) surroundingSumGrid[i][j] += base[i][j - 1];
                else surroundingSumGrid[i][j] += base[i][j];
            }
        }
        return surroundingSumGrid;
    }

    @Override
    public double[][] getSurroundingAvgGrid() {
        double[][] sum = getSurroundingSumGrid(); 
        double[][] surroundingAvgGrid = new double[dimX][dimY];

        for (int i = 0; i < dimX; i++){
            for (int j = 0; j < dimY; j++){
                surroundingAvgGrid[i][j] = sum[i][j] / 4.0;
            }
        }
        return surroundingAvgGrid;
    }

    @Override
    public double[][] getDeltaGrid() {
        double[][] avg = getSurroundingAvgGrid();  
        double[][] deltaGrid = new double[dimX][dimY];

        for (int i = 0; i < dimX; i++){
            for (int j = 0; j < dimY; j++){
                deltaGrid[i][j] = Math.abs(avg[i][j] / 2.0);
            }
        }
        return deltaGrid;
    }

    @Override
    public boolean[][] getDangerGrid() {
        double[][] base = getBaseGrid();
        double[][] deltaGrid = getDeltaGrid();
        double[][] avggrid = getSurroundingAvgGrid();
        boolean[][] dangerGrid = new boolean[dimX][dimY];

        for (int i = 0; i < dimX; i++){
            for (int j = 0; j < dimY; j++){
                double num = base[i][j];
                double delta = deltaGrid[i][j];
                double avg = avggrid[i][j];

                if (num < (avg - delta) || num > (avg + delta)) {
                    dangerGrid[i][j] = true;
                } else {
                    dangerGrid[i][j] = false;
                }
            }
        }
        return dangerGrid;
    }

    @Override
    public String toString() {
        double[][] base = getBaseGrid();
        String gridString = "";

        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {
                gridString += base[i][j];
                if (j < dimY - 1) {
                    gridString += " ";
                }
            }
            gridString += "\n";
        }

        return gridString;
    }
}