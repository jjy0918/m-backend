package com.midas.epkorea.domain.category;

import com.midas.epkorea.domain.manager.Manager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Table
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {

    @Id
    private int no;

    private String parent;

    private String child;

    private static final List<Integer>[] categoryNumbers = new List[]{
            Arrays.asList(1,2,3),
            Arrays.asList(4,5),
            Arrays.asList(6,7,8,9,10),
            Arrays.asList(11,12,13),
            Arrays.asList(14),
    };

    private static final int CATEGORYSIZE=5;


    public static List<Integer> getPMCategoryNumbers(Manager manager){

        List<Boolean> list = new ArrayList<>();

        list.add(manager.isPmUpsSts());
        list.add(manager.isPmEss());
        list.add(manager.isPmCooling());
        list.add(manager.isPmLighting());
        list.add(manager.isPmRailroad());

        return getCategoryNumbers(list);
    }

    public static List<Integer> getCMCategoryNumbers(Manager manager){

        List<Boolean> list = new ArrayList<>();

        list.add(manager.isCmUpsSts());
        list.add(manager.isCmEss());
        list.add(manager.isCmCooling());
        list.add(manager.isCmLighting());
        list.add(manager.isCmRailroad());

        return getCategoryNumbers(list);
    }


    private static List<Integer> getCategoryNumbers(List<Boolean> list){
        List<Integer> result = new ArrayList<>();

        for(int i=0;i<CATEGORYSIZE;i++){
            if(list.get(i)){
                result.addAll(categoryNumbers[i]);
            }
        }

        return result;

    }

}
