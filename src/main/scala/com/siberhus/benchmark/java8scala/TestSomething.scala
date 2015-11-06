package com.siberhus.benchmark.java8scala

import scala.collection.JavaConversions._
import com.siberhus.benchmark.java8scala.data._
import java.time.LocalDate
import java.time.Month

object TestSomething extends App {
  
  val pets: List[Pet] = Pets.DATA.toList;
  
//  pets.sortWith((a, b) => a.getType.compareTo(b.getType) < 0).foreach { x => println(x) }
//  pets.view.sortBy(pet => (pet.getType, pet.getName)).foreach { x => println(x) }
//  val r = pets.view.map(_.getWeight).reduceLeftOption((a, b) => a + b)
//  println(r)
  
  val r = pets.view.filter { pet => pet.getBirthdate.isBefore(LocalDate.of(2013, Month.JANUARY, 1))}
      .filter { pet => pet.getWeight > 50 }
      .sortBy { pet => (pet.getType, pet.getName, pet.getColor) }
      .map{ pet => s"${pet.getType} - name: ${pet.getName}, color: ${pet.getColor}" }
  r.toList.foreach(println(_))
}