package com.se459.floor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.se459.floor.interfaces.IFloorLayout;
import com.se459.floor.interfaces.ISurfaces;
import com.se459.floor.interfaces.ISurfacesFactory;

import org.json.JSONArray;
import org.json.JSONObject;

public class FloorLayout implements IFloorLayout {
    
    private final List<List<ISurfaces>> floor;
    private JSONArray jsonArray;

    public FloorLayout(String jsonFileName) {
        List<List<ISurfaces>> grid = null;
        StringBuilder stringInput = new StringBuilder();
        String jsonPath = "/json/"+jsonFileName;

        try {
            InputStream inputStream = this.getClass().getResourceAsStream(jsonPath);    
            Scanner scan = new Scanner(inputStream);

            while (scan.hasNextLine()) {
                stringInput.append(scan.nextLine());
            }

            jsonArray = new JSONArray(stringInput.toString());
            grid = jsonToArrayLists();

            scan.close();
            inputStream.close();

        } catch(NullPointerException e) {
            System.err.printf("***ERROR: Could not open file.\n");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        floor = grid;
    }

    private List<List<ISurfaces>> jsonToArrayLists() {
        List<List<ISurfaces>> layout = new ArrayList<>();
        ISurfacesFactory factory = new SurfacesFactory();

        for (int i = 0; i < jsonArray.length(); i++) {
            List<ISurfaces> row = new ArrayList<>();
            layout.add(row);

            for (int j = 0; j < jsonArray.getJSONArray(i).length(); j++) {
                JSONArray col = jsonArray.getJSONArray(i);
                JSONObject jsonTile = col.getJSONObject(j);

                // creating tile from json data
                try {
                    ISurfaces tile = null;
                    String tileType = jsonTile.getString("type");
                    int dirtAmt = jsonTile.getInt("dirtAmt");
                    boolean hasChargeStation = jsonTile.getBoolean("hasChargeStation");

                    String northEdge = (jsonTile.has("northEdge"))? jsonTile.getString("northEdge") : "unknown";
                    String eastEdge = (jsonTile.has("eastEdge"))? jsonTile.getString("eastEdge") : "unknown";
                    String southEdge = (jsonTile.has("southEdge"))? jsonTile.getString("southEdge") : "unknown";
                    String westEdge = (jsonTile.has("westEdge"))? jsonTile.getString("westEdge") : "unknown";
                
                    if (tileType.equals("bare")) {
                        tile = factory.createBareSurface(dirtAmt, hasChargeStation, i, j,
                                                            northEdge, eastEdge, southEdge, westEdge);
                    } else if (tileType.equals("lowCarpet")) {
                        tile = factory.createLowCarpet(dirtAmt, hasChargeStation, i, j,
                                                            northEdge, eastEdge, southEdge, westEdge);
                    } else if (tileType.equals("highCarpet")) {
                        tile = factory.createHighCarpet(dirtAmt, hasChargeStation, i, j,
                                                            northEdge, eastEdge, southEdge, westEdge);
                    } else {
                        System.err.println("***ERROR: unknown surface detected.");
                        throw new Exception();
                    }

                    // adding tile to the layout
                    layout.get(i).add(tile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return layout;
    }

    private String determineIcon(char cardinalDirection, String tileEdgeDirection) {
        String icon = "?";        

        if (tileEdgeDirection.equals("open")) {
            // check cardinal direction
            if ((cardinalDirection == 'N') || (cardinalDirection == 'S'))
                icon = "|";
            else if ((cardinalDirection == 'E') || (cardinalDirection == 'W'))
                icon = "=";
        } else if (tileEdgeDirection.equals("obstacle")) {
            icon = "X";
        } else if (tileEdgeDirection.equals("stairs")) {
            icon = "S";
        }
        
        return icon;
    }
    
    /***
     * 
     * Method to return the floor
     */
    @Override
    public List<List<ISurfaces>> getFloor() { return floor; }

    /***
     * 
     * Method to print all floor tiles in detail as they appear in the floor layout
     */
    @Override
    public void printDetailLayout() {
        if (floor == null) throw new NullPointerException();

        for (List<ISurfaces> tilesList : floor)
            System.out.printf("[%s]\n", tilesList);
    }

    /***
     * 
     * Method toString prints a simple view of the floor layout
     */
    @Override
    public String toString() {
        if (floor == null) throw new NullPointerException();

        StringBuilder grid = new StringBuilder();

        for (List<ISurfaces> row : floor) {
            for (ISurfaces tile : row) {
                int x = tile.getXCoord();
                int y = tile.getYCoord();
                String surface = tile.getSurfaceType();
                boolean dirty = tile.hasDirt();
                String isClean = (!dirty)? "clean": "dirty";

                grid.append(String.format("[(%d,%d): %-10s, %s %-5s", x, y, surface, isClean,"]"));
            }
            grid.append("\n");
        }

        return grid.toString();
    }

}
