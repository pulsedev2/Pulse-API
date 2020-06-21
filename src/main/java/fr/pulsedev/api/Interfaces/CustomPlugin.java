package fr.pulsedev.api.Interfaces;

import fr.pulsedev.api.DataManagement.SqlManager.SQL;
import fr.pulsedev.api.utils.Lang.Locate;
import org.bukkit.plugin.Plugin;

/**
 * This file is a part of PulseAPI, located on fr.pulsedev.pulseapi.Interfaces
 * Copyright (c) Renard - All rights reserved
 *
 * @author Renard
 * Created the 19/06/2020 at 23:33.
 */
public interface CustomPlugin<K extends  Plugin> {

    Locate getLocate();

    K getInstance();

    SQL getSQL();

}
