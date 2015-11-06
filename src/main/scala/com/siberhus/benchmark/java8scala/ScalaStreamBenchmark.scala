package com.siberhus.benchmark.java8scala

import org.openjdk.jmh.annotations._
import com.siberhus.benchmark.java8scala.data._
import java.time.LocalDate
import java.time.Month

class ScalaStreamBenchmark {
  
  import ScalaBenchmarkData._
  
//  @Benchmark
//  def testFilterThenCount(): Int = {
//    pets.toStream.count{ pet => pet.getWeight > 50}
//  }
//
//  @Benchmark
//  def testSortThenCollect(): Seq[Pet] = {
//    pets.toStream.sortBy{pet => (pet.getType, pet.getName)}
//  }
//  
//  @Benchmark
//  def testGrouping(): Map[Pet.Type, Seq[Pet]] = {
//    pets.toStream.groupBy{ pet => pet.getType }
//  }
//  
//  @Benchmark
//  def testMapThenCollect(): Seq[String] = {
//    pets.toStream.map(_.getName)
//  }
//  
//  @Benchmark
//  def testMapThenReduce(): Int = {
//    pets.toStream.map(_.getWeight).reduceLeft{(a, b) => a + b}
//  }
//  
//  @Benchmark
//  def testFindFirst(): Option[Pet] = {
//    pets.toStream.find { pet => pet.getName == "Handsome" }
//  }
  
  @Benchmark
  def testFilterThenSortThenMapThenCollect(): Seq[String] = {
    pets.toStream.filter { pet => 
        pet.getBirthdate.isBefore(LocalDate.of(2013, Month.JANUARY, 1)) && pet.getWeight > 50 }
      .sortBy { pet => (pet.getType, pet.getName, pet.getColor) }
      .map{ pet => s"${pet.getType} - name: ${pet.getName}, color: ${pet.getColor}" }
  }
  
  @Benchmark
  def testFilterThenSortThenMapThenCollect2(): Seq[String] = {
    pets.toStream.filter { pet => pet.getBirthdate.isBefore(LocalDate.of(2013, Month.JANUARY, 1))}
      .filter { pet => pet.getWeight > 50 }
      .sortBy { pet => (pet.getType, pet.getName, pet.getColor) }
      .map{ pet => s"${pet.getType} - name: ${pet.getName}, color: ${pet.getColor}" }
  }
}
