package com.siberhus.benchmark.java8scala;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.siberhus.benchmark.java8scala.data.Pet;
import com.siberhus.benchmark.java8scala.data.Pet.Type;
import com.siberhus.benchmark.java8scala.data.Pets;

/**
 * 
 * @author Hussachai
 *
 */
public class JavaStreamBenchmark {
    
    @State(Scope.Benchmark)
    public static class BenchmarkState {
        List<Pet> pets = Pets.DATA;
    }
    
    @Benchmark
    public long runFilterThenCount(BenchmarkState state){
        return state.pets.stream().filter(pet -> pet.getWeight() > 50).count();
    }
    
    @Benchmark
    public List<Pet> runSortThenCollect(BenchmarkState state){
        return state.pets.stream()
                .sorted(Comparator.comparing(Pet::getType)
                        .thenComparing(Pet::getName))
                .collect(Collectors.toList());
    }
    
    @Benchmark
    public Map<Type, List<Pet>> runGrouping(BenchmarkState state){
        return state.pets.stream().collect(Collectors.groupingBy(Pet::getType));
    }
    
    @Benchmark
    public List<String> runMapThenCollect(BenchmarkState state){
        return state.pets.stream().map(Pet::getName).collect(Collectors.toList());
    }
    
    @Benchmark
    public int runMapThenReduce(BenchmarkState state){
        return state.pets.stream().map(Pet::getWeight).reduce(0, (a, b) -> a + b);
    }
    
    @Benchmark
    public Optional<Pet> runFindFirst(BenchmarkState state){
        return state.pets.stream().filter(pet -> pet.getName().equals("Handsome")).findFirst();
    }
    
    @Benchmark
    public List<String> runFilterThenSortThenMapThenCollect(BenchmarkState state){
        return state.pets.stream()
                .filter(pet -> 
                    pet.getBirthdate().isBefore(LocalDate.of(2013, Month.JANUARY, 1)) 
                        && pet.getWeight() > 50)
                .sorted(Comparator.comparing(Pet::getType)
                        .thenComparing(Pet::getName)
                        .thenComparing(Pet::getColor))
                .map(pet -> String.format("%s - name: %s, color: %s", 
                        pet.getType(), pet.getName(), pet.getColor()))
                .collect(Collectors.toList());
    }
    
    @Benchmark
    public List<String> runFilterThenSortThenMapThenCollect2(BenchmarkState state){
        return state.pets.stream()
                .filter(pet -> pet.getBirthdate().isBefore(LocalDate.of(2013, Month.JANUARY, 1)))
                .filter(pet -> pet.getWeight() > 50) /* Is it smart enough to combine filter? */
                .sorted(Comparator.comparing(Pet::getType)
                        .thenComparing(Pet::getName)
                        .thenComparing(Pet::getColor))
                .map(pet -> String.format("%s - name: %s, color: %s", 
                        pet.getType(), pet.getName(), pet.getColor()))
                .collect(Collectors.toList());
    }
}
