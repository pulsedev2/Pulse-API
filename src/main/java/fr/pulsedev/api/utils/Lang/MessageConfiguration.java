/*
 *
 *  Message is a part of primapi.
 *  Copyright (c) Niout - All rights reserved
 *
 *   @author Niout
 *   Created the 18/06/2020 00:56
 *   Last modified: 18/06/2020 00:56
 *
 */

package fr.pulsedev.api.utils.Lang;

import org.jetbrains.annotations.Nullable;

import java.util.TreeMap;

public class MessageConfiguration {

    private TreeMap<String, String> replacement;
    private String id;

    public MessageConfiguration(String id, @Nullable TreeMap<String, String> replacement){
        this.id = id;
        this.replacement = replacement;
    }

    public void addReplacement(String from, String to){
        if(isAlredyIn(from)){
            getNewString(from);
        }
        replacement.put(from, to);
    }

    public void removeReplacement(String from){
        replacement.remove(from);
    }

    public void changeReplacement(String of, String by){
        replacement.remove(of);
        addReplacement(of,by);
    }

    public String getNewString(String base){
        int i = 0;
        String newKey = base;
        while (isAlredyIn(base)){
            if(i >= 5){
                break;
            }
            newKey += "_";
            i++;
        }
        if(i == 5){
            throw new ArrayIndexOutOfBoundsException("You've reach maximum for this key !  KEY =" + base);
        }
        return newKey;
    }

    public boolean isAlredyIn(String toVerify){
        return !replacement.containsKey(toVerify);
    }

    public String getReplacement(String of){
        return replacement.get(of);
    }
    public TreeMap<String, String> getListOfReplacement(){
        return this.replacement;
    }
    public String getId() {
        return id;
    }
}
