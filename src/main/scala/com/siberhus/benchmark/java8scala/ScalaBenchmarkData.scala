package com.siberhus.benchmark.java8scala
import scala.collection.JavaConversions._
import org.openjdk.jmh.annotations._
import com.siberhus.benchmark.java8scala.data._

object ScalaBenchmarkData {
  
  @State(Scope.Benchmark)
  val pets: List[Pet] = Pets.DATA.toList;
  
}