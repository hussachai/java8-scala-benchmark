package com.siberhus.benchmark.java8scala;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.siberhus.benchmark.java8scala.data.Pet;
import com.siberhus.benchmark.java8scala.data.Pets;

/**
 * 
 * @author Hussachai
 *
 */
public class JavaCollectionBenchmark {
    
    @State(Scope.Benchmark)
    public static class BenchmarkState {
        volatile List<Pet> pets = Pets.DATA;
    }
    
    @Benchmark
    public List<String> testFilterThenSortThenMapThenCollect(BenchmarkState state){
        List<Pet> tmpList = new ArrayList<>();
        for(Pet pet: state.pets){
            if(pet.getBirthdate().isBefore(LocalDate.of(2013, Month.JANUARY, 1))
                    && pet.getWeight() > 50){
                tmpList.add(pet);
            }
        }
        Collections.sort(tmpList, new Comparator<Pet>() {
            @Override
            public int compare(Pet o1, Pet o2) {
                int i = o1.getType().compareTo(o2.getType());
                if(i == 0){
                    i = o1.getName().compareTo(o2.getName());
                    if(i == 0){
                        i = o1.getColor().compareTo(o2.getColor());
                    }
                }
                return i;
            }
        });
        List<String> tmpList2 = new ArrayList<String>();
        for(Pet pet: tmpList){
            tmpList2.add(String.format("%s - name: %s, color: %s", 
                    pet.getType(), pet.getName(), pet.getColor()));
        }
        return tmpList2;
    }
    
}
