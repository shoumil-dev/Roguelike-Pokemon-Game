package game.maps;

import edu.monash.fit2099.engine.positions.Location;
import java.util.HashMap;
import java.util.Map;

public class TeleportManager {

    private static TeleportManager instance;

    private final Map<MapList, Location> teleportLocations;

    private TeleportManager() {
        this.teleportLocations = new HashMap<>();
    }

    public static TeleportManager getInstance() {
        if (instance == null) {
            instance = new TeleportManager();
        }
        return instance;
    }
    public void addLocation(MapList mapName, Location location) {
        teleportLocations.put(mapName,location);
    }

    public Location getLocation(MapList teleportMap) {
        return teleportLocations.get(teleportMap);
    }
}
