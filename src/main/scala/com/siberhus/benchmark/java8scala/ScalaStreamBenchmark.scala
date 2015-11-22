package com.siberhus.benchmark.java8scala

import org.openjdk.jmh.annotations._
import com.siberhus.benchmark.java8scala.data._
import java.time.LocalDate
import java.time.Month

class ScalaStreamBenchmark {
  
  import ScalaStates._
  
  @Benchmark
  def runFilterThenCount(state: BenchmarkState): Int = {
    state.pets.toStream.count{ pet => pet.getWeight > 50}
  }

  @Benchmark
  def runSortThenCollect(state: BenchmarkState): Seq[Pet] = {
    state.pets.toStream.sortBy{pet => (pet.getType, pet.getName)}
  }
  
  @Benchmark
  def runGrouping(state: BenchmarkState): Map[Pet.Type, Seq[Pet]] = {
    state.pets.toStream.groupBy{ pet => pet.getType }
  }
  
  @Benchmark
  def runMapThenCollect(state: BenchmarkState): Seq[String] = {
    state.pets.toStream.map(_.getName)
  }
  
  @Benchmark
  def runMapThenReduce(state: BenchmarkState): Int = {
    state.pets.toStream.map(_.getWeight).reduceLeft{(a, b) => a + b}
  }
  
  @Benchmark
  def runFindFirst(state: BenchmarkState): Option[Pet] = {
    state.pets.toStream.find { pet => pet.getName == "Handsome" }
  }
  
  @Benchmark
  def runFilterThenSortThenMapThenCollect(state: BenchmarkState): Seq[String] = {
    state.pets.toStream.filter { pet => 
        pet.getBirthdate.isBefore(LocalDate.of(2013, Month.JANUARY, 1)) && pet.getWeight > 50 }
      .sortBy { pet => (pet.getType, pet.getName, pet.getColor) }
      .map{ pet => s"${pet.getType} - name: ${pet.getName}, color: ${pet.getColor}" }
  }
  
  @Benchmark
  def runFilterThenSortThenMapThenCollect2(state: BenchmarkState): Seq[String] = {
    
    state.pets.toStream.filter { pet => pet.getBirthdate.isBefore(LocalDate.of(2013, Month.JANUARY, 1))}
      .filter { pet => pet.getWeight > 50 }
      .sortBy { pet => (pet.getType, pet.getName, pet.getColor) }
      .map{ pet => s"${pet.getType} - name: ${pet.getName}, color: ${pet.getColor}" }
  }
}
