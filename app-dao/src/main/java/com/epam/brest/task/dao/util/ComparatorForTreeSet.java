package com.epam.brest.task.dao.util;

import com.epam.brest.task.domain.Galaxy;
import com.epam.brest.task.domain.Star;

import java.util.Comparator;

public class ComparatorForTreeSet implements Comparator<Object>{

    @Override
    public int compare(Object o1, Object o2) {


        if(o1 != null && o2 != null){
            if(o1 instanceof Galaxy){
                Long id1 = ((Galaxy)o1).getGalaxyId();
                Long id2 = ((Galaxy)o2).getGalaxyId();
                return id1 < id2 ? -1 : id1 > id2 ? 1 : 0;
            } else if(o1 instanceof Star){
                Long id1 = ((Star)o1).getStarId();
                Long id2 = ((Star)o2).getStarId();
                return id1 < id2 ? -1 : id1 > id2 ? 1 : 0;
            } else{
                return 0;
            }
        } else{
            return 0;
        }
    }
}
