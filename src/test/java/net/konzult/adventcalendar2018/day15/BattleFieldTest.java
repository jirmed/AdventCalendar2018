package net.konzult.adventcalendar2018.day15;

import jdk.nashorn.internal.ir.annotations.Ignore;
import net.konzult.adventcalendar2018.FileParser;
import net.konzult.adventcalendar2018.day13.Coordinates;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class BattleFieldTest {

    private static List<String> strings;
    private BattleField field;

    @BeforeAll
    static void setUpClass() throws Exception {
        strings = FileParser.readStringListFile("day15test.txt");
    }


    @BeforeEach
    void setUp() throws Exception {
        field = BattleField.parse(strings);

    }

    @Test
    void testParse() throws Exception {
        assertThat(field.getFieldMap()).hasSize(31);
    }

    @Test
    void testGetNextStep() throws Exception {
        Item firstElf = field.getFieldMap().values().stream()
                .filter(item -> item.getType() == ItemType.ELF)
                .findFirst()
                .orElse(null);
        Item firstGoblin = field.getFieldMap().values().stream()
                .filter(item -> item.getType() == ItemType.GOBLIN)
                .findFirst()
                .orElse(null);

        BattleField.Move firstElfNextStep = field.nextStep((Warrior) firstElf);
        assertThat(firstElfNextStep)
                .isEqualTo(field.new Move(null, new Warrior(ItemType.GOBLIN, new Coordinates(5, 2))));
        BattleField.Move firstGoblinNextStep = field.nextStep((Warrior) firstGoblin);
        assertThat(firstGoblinNextStep)
                .isEqualTo(field.new Move( new Coordinates(3, 1),null));
    }

    @Test
    void testPlayFirstElf() throws Exception {
        Item firstElf = field.getFieldMap().values().stream()
                .filter(item -> item.getType() == ItemType.ELF)
                .findFirst()
                .orElse(null);
        boolean result = field.playWarrior((Warrior) firstElf);
        assertThat(result).isTrue();
        assertThat(firstElf)
                .isEqualTo(new Warrior(ItemType.ELF, new Coordinates(4, 2)));

    }

    @Test
    void testPlayFirstGoblin() throws Exception {
        Item firstGoblin = field.getFieldMap().values().stream()
                .filter(item -> item.getType() == ItemType.GOBLIN)
                .findFirst()
                .orElse(null);

        boolean result;

        //first step
        result = field.playWarrior((Warrior) firstGoblin);
        assertThat(result).isTrue();
        assertThat(firstGoblin)
                .isEqualTo(new Warrior(ItemType.GOBLIN, new Coordinates(3, 1)));
        assertThat(((Warrior) firstGoblin).getHitPoints())
                .isEqualTo(200);

        //second step
        result = field.playWarrior((Warrior) firstGoblin);
        assertThat(result).isTrue();
        assertThat(firstGoblin)
                .isEqualTo(new Warrior(ItemType.GOBLIN, new Coordinates(4, 1)));
        assertThat(((Warrior) firstGoblin).getHitPoints())
                .isEqualTo(200);

        //third step
        result = field.playWarrior((Warrior) firstGoblin);
        assertThat(result).isTrue();
        assertThat(firstGoblin)
                .isEqualTo(new Warrior(ItemType.GOBLIN, new Coordinates(4, 1)));
    }

    @Test
    @Disabled
    void testPlay() throws Exception {
        int round = 0;
        List<String> strings = FileParser.readStringListFile("day15test2.txt");
        field = BattleField.parse(strings);

        round = field.play(10000);
        assertThat(round).isEqualTo(37);
        assertThat(field.getTotalHitpoints()).isEqualTo(982);


    }

    @Test
    void testPlayWithAlteredElfAttack() throws Exception {
        int round = 0;
        List<String> strings = FileParser.readStringListFile("day15test.txt");
        field = BattleField.parse(strings);
        field.setElfAttack(15);
        int originalElfCount = field.getWarriors(ItemType.ELF).size();
        round = field.play(10000);
        assertThat(round).isEqualTo(29);
        assertThat(field.getWarriors(ItemType.ELF).size()).isEqualTo(originalElfCount);


    }

    @Test
    void testGetMinElfAttackToSurviveAll() throws Exception {
        int round = 0;
        List<String> strings = FileParser.readStringListFile("day15test5.txt");
        assertThat(BattleField.getMinElfAttackToSurviveAll(strings)).isEqualTo(15);
    }
}