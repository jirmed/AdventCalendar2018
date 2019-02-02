package net.konzult.adventcalendar2018.day5;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.primitives.Chars;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polymer {
    private String formula;

    public Polymer(String formula) {
        this.formula = formula;
    }

    public void reduce() {

        Pattern pattern = buildPattern();
        Matcher matcher = pattern.matcher(formula);

        while (matcher.find()) {
            formula = matcher.replaceAll("");
            matcher = pattern.matcher(formula);
        }
    }

    public void removeUnit(char unit) {
        Matcher matcher = Pattern.compile(""+unit,Pattern.CASE_INSENSITIVE).matcher(formula);
        formula = matcher.replaceAll("");
    }

    public void maxReduceByRemovingAnyUnit() {
        this.reduce();
        int minLength = Integer.MAX_VALUE;
        String newFormula = formula;
        for (char letter = 'a'; letter <= 'z' ; letter++) {
            Polymer testedPolymer = new Polymer(this.formula);
            testedPolymer.removeUnit(letter);
            testedPolymer.reduce();
            if (testedPolymer.getFormula().length()<minLength) {
                newFormula = testedPolymer.formula;
                minLength=newFormula.length();
            }
        }
        formula = newFormula;
    }

    private Pattern buildPattern() {
        StringBuilder tokens = new StringBuilder();
        for (char lowerCaseLetter = 'a'; lowerCaseLetter <= 'z'; lowerCaseLetter++) {
            char upperCaseLetter = Character.toUpperCase(lowerCaseLetter);
            ((StringBuilder) tokens)
                    .append(lowerCaseLetter)
                    .append(upperCaseLetter)
                    .append('|')
                    .append(upperCaseLetter)
                    .append(lowerCaseLetter)
                    .append('|');
        }
        tokens.setLength(tokens.length() - 1);
        return Pattern.compile( tokens.toString() );
    }

    public String getFormula() {
        return formula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polymer polymer = (Polymer) o;
        return Objects.equals(formula, polymer.formula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(formula);
    }
}
