package net.konzult.adventcalendar2018.day12;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.shouldHaveThrown;

class GardenTest {

    @Test
    void testReadGardenFile() throws Exception {
        Garden garden = Garden.readGardenFile("day12test.txt");

        assertThat(garden.getPlantsSet().size()).isEqualTo(11);
        assertThat(garden.getGrowPatternMap().size()).isEqualTo(14);
    }

    @Test
    void testGetGrowPatternAt() throws Exception {
        Garden garden = Garden.readGardenFile("day12test.txt");

        assertThat(garden.getGrowPatternAt(2).getPattern()).isEqualTo(new boolean[]{true, false, false, true, false});
    }

    @Test
    void testNextDay() throws Exception {
    Garden garden = Garden.readGardenFile("day12test.txt");

    assertThat(garden.nextGeneration().getPlantsSet().size()).isEqualTo(7);
    assertThat(garden.nextGeneration().getPlantsSet().size()).isEqualTo(11);
    assertThat(garden.nextGeneration().getPlantsSet().size()).isEqualTo(9);

    }

    @Test
    void testPlayDays() throws Exception {
        Garden garden = Garden.readGardenFile("day12test.txt");

        assertThat(garden.playGenerations(20l).getPlantsSet().size()).isEqualTo(19);
    }

    @Test
    void testCheckSum() throws Exception {
        Garden garden = Garden.readGardenFile("day12test.txt");
        assertThat(garden.playGenerations(20).getCheckSum()).isEqualTo(325);
    }

    @Test
    void testShiftedPlantsBuild() throws Exception {
        Garden garden = Garden.readGardenFile("day12test.txt");

        garden.nextGeneration();

        Garden.ShiftedPlants shiftedPlants = Garden.ShiftedPlants.build(garden.getPlantsSet(),1);

        assertThat(shiftedPlants.getPlants().size()).isEqualTo(garden.getPlantsSet().size());


    }

    @ParameterizedTest
    @ValueSource(longs = {1,10,100})
    void testGetCheckSum(long generations) throws Exception {
        Garden garden = Garden.readGardenFile("day12test.txt");
        garden.playGenerations(generations);
        Garden.ShiftedPlants shiftedPlants = Garden.ShiftedPlants.build(garden.getPlantsSet(),generations);

        assertThat(garden.getCheckSum(shiftedPlants)).isEqualTo(garden.getCheckSum());
        assertThat(shiftedPlants.getPlants().getPlantsSet().stream().min(Integer::compareTo).get()).isEqualTo(0);

    }

    @ParameterizedTest
    @ValueSource(longs = {1,20,100,200})
    void testGetCheckumAfterManyGenerations(long generations) throws Exception {
        Garden garden = Garden.readGardenFile("day12test.txt");
        Garden referenceGarden = Garden.readGardenFile("day12test.txt");


        assertThat(garden.getCheckumAfterManyGenerations(generations))
                .isEqualTo(referenceGarden.playGenerations(generations).getCheckSum());
    }
}