package com.siberhus.benchmark.java8scala
import scala.collection.JavaConversions._
import org.openjdk.jmh.annotations._
import com.siberhus.benchmark.java8scala.data._

object ScalaStates {
  
  @State(Scope.Benchmark)
  class BenchmarkState {
    var pets: List[Pet] = Pets.DATA.toList;
  }
  
}