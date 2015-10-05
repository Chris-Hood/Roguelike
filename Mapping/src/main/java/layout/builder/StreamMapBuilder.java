package layout.builder;

import layout.Map;
import layout.TileSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Chris on 10/2/2015.
 */
public class StreamMapBuilder implements IMapBuilder {
    TileSet tileSet;
    InputStream inputStream;

    public StreamMapBuilder(TileSet tileSet, InputStream inputStream) {
        this.tileSet = tileSet;
        this.inputStream = inputStream;
    }

    @Override
    public Map build() {
        Map map = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            int y = 0;
            String str = reader.readLine();
            String[] dim = str.split(",");
            int xSize = Integer.parseInt(dim[0]);
            int ySize = Integer.parseInt(dim[1]);
            map = new Map(ySize, xSize);

            while ((str = reader.readLine()) != null) {
                String[] tileData = str.split(",");
                for (int x = 0; x < tileData.length; ++x) {
                    map.setTile(y, x, tileSet.getTile(Integer.parseInt(tileData[x])));
                }
                ++y;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return map;
    }
}
