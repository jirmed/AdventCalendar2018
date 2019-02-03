package net.konzult.adventcalendar2018.day11;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BatteryTest {

    public static final int BATTERY_SIZE = 300;

    @ParameterizedTest
    @CsvSource({
            "3,5,8,4",
            "122,79,57,-5",
            "217,196,39,0",
            "101,153,71,4"
    })
    void testGetCellPowerLevel(int x, int y, int serialNo, int powerLevel) throws Exception {
        Battery battery = new Battery(BATTERY_SIZE, serialNo);

        assertThat(battery.getCellPowerLevel(x, y)).isEqualTo(powerLevel);
    }

    @Test
    void testInit() throws Exception {
        Battery battery = new Battery(BATTERY_SIZE, 18);
        battery.init();

        assertThat(Arrays.copyOfRange(battery.getCells()[32], 44, 47)).isEqualTo(new int[]{4, 3, 1});
    }

    @ParameterizedTest
    @CsvSource({
            "18,33,45,29",
            "42,21,61,30"
    })
    void testGetGridPowerLevel(int serialNo, int x, int y, int powerLevel) throws Exception {
        Battery battery = new Battery(BATTERY_SIZE, serialNo);
        battery.init();

        assertThat(battery.new Grid(x, y, 3).getPowerLevel()).isEqualTo(powerLevel);
    }

    @Test
    void testGetMaxPowerGridWithSize() throws Exception {
        Battery battery = new Battery(BatteryTest.BATTERY_SIZE, 42);
        battery.init();

        assertThat(battery.getMaxPowerGrid(3))
                .extracting("x", "y","size")
                .contains(21, 61,3);

    }

    @ParameterizedTest
    @CsvSource({"18,90,269,16,113",
            "42,232,251,12,119"
            })
    void testGetMaxPowerGridWithoutSize(int serialNo, int x, int y, int size, int powerLevel) throws Exception {
        Battery battery = new Battery(BATTERY_SIZE,serialNo);
        battery.init();

        Battery.Grid grid = battery.getMaxPowerGrid();
        assertThat(grid).extracting("x", "y", "size")
                .contains(y, y, size);
        assertThat(grid.getPowerLevel()).isEqualTo(powerLevel);
    }
}