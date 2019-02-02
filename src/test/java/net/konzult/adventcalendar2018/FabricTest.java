package net.konzult.adventcalendar2018;

import net.konzult.adventcalendar2018.day3.Fabric;
import net.konzult.adventcalendar2018.day3.Rectangle;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FabricTest {

    @Test
    void cutRectangle() {
        Fabric fabric = new Fabric(11, 11);

        Rectangle rectangle1 = new Rectangle(1, 1, 3, 4, 4);
        Rectangle rectangle2 = new Rectangle(2, 3, 1, 4, 4);
        Rectangle rectangle3 = new Rectangle(3, 5, 5, 2, 2);
        fabric.cutRectangle(rectangle1);
        fabric.cutRectangle(rectangle2);
        fabric.cutRectangle(rectangle3);

        assertThat(fabric.getDuplicateTiles().size()).isEqualTo(4);
        assertThat(fabric.getWholeRectangles().size()).isEqualTo(1);
        assertThat(fabric.getWholeRectangles().toArray()[0]).isEqualTo(rectangle3);

    }
}