package com.demo.sampleproject.datahandler.collection;

import com.demo.sampleproject.datahandler.enumerator.SaroListEnumerator;
import com.demo.sampleproject.datahandler.object.SaroEntry;

import java.util.ArrayList;

/**
 * Created by kevinpanuelos on 11/16/13.
 */
public class SaroList {
    private ArrayList<SaroEntry> saroEntries;

    public SaroList() {
        this.saroEntries = new ArrayList<SaroEntry>();
    }

    public SaroEntry get(int index) {
        return this.saroEntries.get(index);
    }

    public void add(SaroEntry entry) {
        this.saroEntries.add(entry);
    }

    public void remove(int index) {
        this.saroEntries.remove(index);
    }

    public void remove(SaroEntry entry) {
        this.saroEntries.remove(entry);
    }

    public void enumerate(SaroListEnumerator enumerator) {
        for (SaroEntry entry : saroEntries) {
            enumerator.iterate(entry);
        }
    }
    
    public int size() {
    	return this.saroEntries.size();
    }

}
