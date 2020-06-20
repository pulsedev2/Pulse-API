/*
 *
 *  Locate is a part of primapi.
 *  Copyright (c) Niout - All rights reserved
 *
 *   @author Niout
 *   Created the 18/06/2020 00:33
 *   Last modified: 16/06/2020 21:13
 *
 */

package fr.pulsedev.api.utils.Lang;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;

public class Locate {

    private Plugin plugin;
    private HashMap<String, FileConfiguration> locations;
    private File[] locationFiles;

    public HashMap<String, FileConfiguration> getLocations() {
        return locations;
    }

    public File[] getLocationFiles() {
        return locationFiles;
    }

    public Locate(@NotNull Plugin plugin, @Nullable HashMap<String, FileConfiguration> locations, @Nullable File[] locationFiles){
        this.plugin = plugin;
        this.locations = locations;
        this.locationFiles = locationFiles;
    }

    public Locate(@NotNull Plugin plugin){
        this(plugin, new HashMap<>(), new File[]{});
    }

    public void register(){
        File locationFolder = new File(plugin.getDataFolder().getPath() + File.pathSeparator + "location");
        FilenameFilter ymlFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".yml");
            }
        };
        File[] fileList = locationFolder.listFiles(ymlFilter);
        assert fileList != null;
        for(File file:fileList){
            FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            locations.put(file.getName().replace(".yml", ""), configuration);
        }
    }

    public Locate register(@Nullable String ignored){
        File locationFolder = new File(plugin.getDataFolder().getPath() + File.pathSeparator + "location");
        FilenameFilter ymlFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".yml");
            }
        };
        File[] fileList = locationFolder.listFiles(ymlFilter);
        assert fileList != null;
        for(File file:fileList){
            FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            locations.put(file.getName().replace(".yml", ""), configuration);
        }

        return new Locate(this.plugin);
    }

    public String getString(String unlocalizedName, String id){
        return this.locations.get(unlocalizedName).getString(id);
    }
}
