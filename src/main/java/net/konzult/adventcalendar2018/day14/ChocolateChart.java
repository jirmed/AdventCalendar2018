package net.konzult.adventcalendar2018.day14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChocolateChart {
    private final List<Integer> recipes;
    private final List<Integer> elves;

    private ChocolateChart(ArrayList<Integer> recipes, List<Integer> elves) {
        this.recipes = recipes;
        this.elves = elves;
    }

    public ChocolateChart() {
        this(new ArrayList<>(Arrays.asList(3, 7)),
                Arrays.asList(0, 1));
    }

    void round() {
        int recipeBase = 0;
        for (Integer elf : elves)
            recipeBase += recipes.get(elf);
        if (recipeBase / 10 > 0) {
            recipes.add(recipeBase / 10);
        }
        recipes.add(recipeBase % 10);

        for (int i = 0; i < elves.size(); i++) {
            Integer elf = elves.get(i);
            elf += recipes.get(elf) + 1;
            if (elf >= recipes.size())
                elf %= recipes.size();
            elves.set(i, elf);
        }
    }

//    private void addIntToRecipes(int digit) {
//        recipes = new StringBuilder(recipes).append(digit).toString();
//    }

    public String scoreTenAfter(int skip) {
        while (recipes.size() < skip + 10)
            round();

        StringBuilder score = new StringBuilder();
        for (int i = skip; i < skip + 10; i++) {
            score.append(recipes.get(i));
        }
        return score.toString();
    }

    List<Integer> getRecipes() {
        return recipes;
    }

    @Override
    public String toString() {
        return "ChocolateChart{" +
                "recipes=" + recipes +
                ", elves=" + elves +
                '}';
    }

    public List<Integer> getElves() {
        return elves;
    }

    public int findRecipe(String recipe) {
        int recipeLength = recipe.length();
        ArrayList<Integer> recipeList = new ArrayList<>(recipeLength);
        for (int i = 0; i < recipeLength; i++) {
            recipeList.add(recipe.charAt(i) - '0');
        }
        int recipesLength = recipes.size();
        while (true) {
            if (recipeLength < recipesLength) {
                if (recipes.subList(recipesLength - recipeLength - 1, recipesLength - 1)
                        .equals(recipeList))
                    return recipesLength - recipeLength - 1;
                if (recipes.subList(recipesLength - recipeLength, recipesLength)
                        .equals(recipeList))
                    return recipesLength - recipeLength;
            }
            round();
            recipesLength = recipes.size();
        }

    }
}
