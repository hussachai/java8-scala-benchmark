package com.siberhus.benchmark.java8scala.data;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Pet {

    public static enum Type {
        CAT, DOG
    }
    
    public static enum Color {
        BLACK, WHITE, BROWN, GREEN
    }
    
    private String name;
    
    private Type type;
    
    private LocalDate birthdate;
    
    private Color color;
    
    private int weight;
    
    public Pet(String name, Type type, LocalDate birthdate, Color color, int weight){
        this.name = name;
        this.type = type;
        this.birthdate = birthdate;
        this.color = color;
        this.weight = weight;
    }
    
    public Pet(String name, Type type, String birthdate, Color color, int weight){
        this(name, type, LocalDate.parse(birthdate), color, weight);
    }
    
    @Override
    public String toString(){
      return String.format("new Pet(\"%s\", Type.%s, \"%s\", Color.%s, %d)", 
              name, type, birthdate, color, weight);
    }
    
    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Color getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }
    
    private static final List<String> NAMES = Arrays.asList(
            "Angel", "Amazing", "Babe", "Baboo", "Bambi", "Bear", "Boo", "Bubby", "Buddy", "Bunny",
            "Candy", "Champ", "Cinnamon", "Cookie", "Cutie", "Daisy", "Dear", "Doll", "Dove",
            "Goofy", "Handsome", "Hero", "Hottie", "Hunky", "Hunny", "Kitten", "Kitty", "Lala",
            "Love", "Luv", "Mama", "Mami", "Monkey", "Muffin", "Peanut", "Pinky", "Pooh", "Poopie");
    
    public static List<Pet> createPets(int n){
        System.out.println("Generating " + n + " pets...");
        LocalDate today = LocalDate.now();
        Random rand = new Random();
        List<Pet> pets = new ArrayList<>();
        
        for(int i = 0; i < n; i++){
            String name = NAMES.get(rand.nextInt(NAMES.size()));
            Type type = rand.nextBoolean()? Type.DOG: Type.CAT;
            int age = rand.nextInt(13);
            LocalDate birthdate = LocalDate.of(
                    today.getYear() - age, 
                    Month.values()[rand.nextInt(Month.values().length)], rand.nextInt(27) + 1);
            Color color = Color.values()[rand.nextInt(Color.values().length)];
            int weight = (age * 10) + rand.nextInt(15);
            Pet pet = new Pet(name, type, birthdate, color, weight);
            pets.add(pet);
        }
        
        return pets;
    }
    
}
