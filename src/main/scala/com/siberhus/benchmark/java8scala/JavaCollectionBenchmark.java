package com.siberhus.benchmark.java8scala;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import com.siberhus.benchmark.java8scala.data.Pet;
import com.siberhus.benchmark.java8scala.data.Pet.Type;
import com.siberhus.benchmark.java8scala.data.Pets;

/**
 * 
 * @author Hussachai
 *
 */

public class JavaCollectionBenchmark {
    
    @State(Scope.Benchmark)
    public static class BenchmarkState {
        List<Pet> pets;
        @Setup
        public void setup(){
            pets = new ArrayList<>(Pets.DATA);
        }
    }
    
    @State(Scope.Thread)
    public static class ThreadState {
        List<Pet> pets;
        
        @Setup(Level.Invocation)
        public void setup(){
            pets = new ArrayList<>(Pets.DATA);
        }
    }
    
    @Benchmark
    public long runFilterThenCount(BenchmarkState state){
      List<Pet> result = new ArrayList<>();
      for(Pet pet: state.pets){
          if(pet.getWeight() > 50){
              result.add(pet);
          }
      }
      return result.size();
    }
    
    @Benchmark
    public long runFilterWithRemoveIfThenCount(ThreadState state){
        state.pets.removeIf(pet -> pet.getWeight() > 50);
        return state.pets.size();
    }
    
    @Benchmark
    public List<Pet> runSortThenCollect(ThreadState state){
        Collections.sort(state.pets, new Comparator<Pet>() {
            @Override
            public int compare(Pet o1, Pet o2) {
                int i = o1.getType().compareTo(o2.getType());
                if(i == 0){
                    i = o1.getName().compareTo(o2.getName());
                }
                return i;
            }
        });
        return state.pets;
    }
    
    @Benchmark
    public Map<Type, List<Pet>> runGrouping(BenchmarkState state){
        Map<Type, List<Pet>> result = new HashMap<>();
        for(Pet pet: state.pets){
            List<Pet> pets = result.get(pet.getType());
            if(pets == null){
                pets = new ArrayList<>();
                result.put(pet.getType(), pets);
            }
            pets.add(pet);
        }
        return result;
    }
    
    @Benchmark
    public List<String> runMapThenCollect(BenchmarkState state){
        List<String> result = new ArrayList<>();
        for(Pet pet: state.pets){
            result.add(pet.getName());
        }
        return result;
    }
    
    @Benchmark
    public int runMapThenReduce(BenchmarkState state){
        int result = 0;
        for(Pet pet: state.pets){
            result += pet.getWeight();
        }
        return result;
    }
    
    @Benchmark
    public Optional<Pet> runFindFirst(BenchmarkState state){
        for(Pet pet: state.pets){
            if(pet.getName().equals("Handsome")){
                return Optional.of(pet);
            }
        }
        return Optional.empty();
    }
    
    
    @Benchmark
    public List<String> runFilterThenSortThenMapThenCollect(BenchmarkState state){
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
        List<String> result = new ArrayList<String>();
        for(Pet pet: tmpList){
            result.add(String.format("%s - name: %s, color: %s", 
                    pet.getType(), pet.getName(), pet.getColor()));
        }
        return result;
    }
    
}
