package Model;

/** MAP contains minatures and names of each map.*/
public enum MAP
{
    CHAINS_OF_WAR ("Model/MapElements/MapMiniatures/chains_of_war.png", "Chains of war"),
    DESERT_SKIRMISH ("Model/MapElements/MapMiniatures/desert_skirmish.png", "Desert skirmish"),
    FOREST_FIGHT ("Model/MapElements/MapMiniatures/forest_fight.png", "Forest fight"),
    SWIM_TRAINING ("Model/MapElements/MapMiniatures/swim_training.png", "Swim training");

    private String mapUrl;
    private String mapDBName;

    MAP(String mapUrl,String mapDBName)
    {
        this.mapUrl = mapUrl;
        this.mapDBName = mapDBName;
    }

    public String getMapUrl()
    {
        return mapUrl;
    }

    public String getMapDBName()
    {
        return mapDBName;
    }
}
