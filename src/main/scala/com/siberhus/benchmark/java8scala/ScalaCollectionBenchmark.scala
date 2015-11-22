package com.siberhus.benchmark.java8scala

import org.openjdk.jmh.annotations._
import com.siberhus.benchmark.java8scala.data._
import java.time.LocalDate
import java.time.Month
import org.openjdk.jmh.infra.Blackhole

class ScalaCollectionBenchmark {
  
  import ScalaStates._
  
  @Benchmark
  def runFilterThenCount(state: BenchmarkState): Int = {
    state.pets.count{ pet => pet.getWeight > 50}
  }

  @Benchmark
  def runSortThenCollect(state: BenchmarkState): List[Pet] = {
    state.pets.sortBy{pet => (pet.getType, pet.getName)}
  }
  
  
  @Benchmark
  def runGrouping(state: BenchmarkState): Map[Pet.Type, List[Pet]] = {
    state.pets.groupBy{ pet => pet.getType }
  }
  
  @Benchmark
  def runMapThenCollect(state: BenchmarkState): List[String] = {
    state.pets.map(_.getName)
  }
  
  @Benchmark
  def runMapThenReduce(state: BenchmarkState): Int = {
    state.pets.map(_.getWeight).reduceLeft{(a, b) => a + b}
  }
  
  @Benchmark
  def runFindFirst(state: BenchmarkState): Option[Pet] = {
    state.pets.find { pet => pet.getName == "Handsome" }
  }
  
  @Benchmark
  def runFilterThenSortThenMapThenCollect(state: BenchmarkState): List[String] = {
    state.pets.filter { pet =>  
        pet.getBirthdate.isBefore(LocalDate.of(2013, Month.JANUARY, 1)) && pet.getWeight > 50}
      .sortBy { pet => (pet.getType, pet.getName, pet.getColor) }
      .map{ pet => s"${pet.getType} - name: ${pet.getName}, color: ${pet.getColor}" }
  }
  
  @Benchmark
  def runFilterThenSortThenMapThenCollect2(state: BenchmarkState): List[String] = {
    state.pets.filter { pet => pet.getBirthdate.isBefore(LocalDate.of(2013, Month.JANUARY, 1))}
      .filter { pet => pet.getWeight > 50 }
      .sortBy { pet => (pet.getType, pet.getName, pet.getColor) }
      .map{ pet => s"${pet.getType} - name: ${pet.getName}, color: ${pet.getColor}" }
  }
}
