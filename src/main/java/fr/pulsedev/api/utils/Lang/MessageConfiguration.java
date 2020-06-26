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

    private final TreeMap<String, String> replacement;
    private final String id;

    public MessageConfiguration(String id, @Nullable TreeMap<String, String> replacement){
        this.id = id;
        this.replacement = replacement;
    }

    public void addReplacement(String from, String to){
        if(isAlreadyIn(from)){
            from = getNewString(from);
        }
        assert replacement != null;
        replacement.put(from, to);
    }

    public void removeReplacement(String from){
        assert replacement != null;
        replacement.remove(from);
    }

    public void changeReplacement(String of, String by){
        assert replacement != null;
        replacement.remove(of);
        addReplacement(of,by);
    }

    public String getNewString(String base){
        int i = 0;
        StringBuilder newKey = new StringBuilder(base);
        while (isAlreadyIn(base)){
            if(i >= 5)break;
            newKey.append("_");
            i++;
        }
        if(i == 5){
            throw new ArrayIndexOutOfBoundsException("You've reach maximum for this key !  KEY =" + base);
        }
        return newKey.toString();
    }

    public boolean isAlreadyIn(String toVerify){
        assert replacement != null;
        return !replacement.containsKey(toVerify);
    }

    public String getReplacement(String of){
        assert replacement != null;
        return replacement.get(of);
    }

    public TreeMap<String, String> getListOfReplacement(){
        return this.replacement;
    }
    public String getId() {
        return id;
    }
}
