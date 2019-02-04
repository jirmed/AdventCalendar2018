package net.konzult.adventcalendar2018.day13;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static net.konzult.adventcalendar2018.day13.TrackType.*;
import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CartTest {

    @Test
    void testTick() throws Exception {
        Cart cart = new Cart(Coordinates.of(3, 4), Coordinates.of(1, 0));

        cart.tick(STRAIGHT);
        assertThat(cart.getPosition()).extracting("x","y").isEqualTo(Arrays.asList(4,4));
        assertThat(cart.getDirection()).extracting("x","y").isEqualTo(Arrays.asList(1,0));

        cart.tick(LEFT);
        assertThat(cart.getPosition()).extracting("x","y").isEqualTo(Arrays.asList(5,4));
        assertThat(cart.getDirection()).extracting("x","y").isEqualTo(Arrays.asList(0,-1));

        cart.tick(RIGHT);
        assertThat(cart.getPosition()).extracting("x","y").isEqualTo(Arrays.asList(5,3));
        assertThat(cart.getDirection()).extracting("x","y").isEqualTo(Arrays.asList(1,0));

        cart.tick(RIGHT);
        assertThat(cart.getPosition()).extracting("x","y").isEqualTo(Arrays.asList(6,3));
        assertThat(cart.getDirection()).extracting("x","y").isEqualTo(Arrays.asList(0,1));

        cart.tick(RIGHT);
        assertThat(cart.getPosition()).extracting("x","y").isEqualTo(Arrays.asList(6,4));
        assertThat(cart.getDirection()).extracting("x","y").isEqualTo(Arrays.asList(-1,0));

        cart.tick(CROSSING); //first crossing LEFT
        assertThat(cart.getPosition()).extracting("x","y").isEqualTo(Arrays.asList(5,4));
        assertThat(cart.getDirection()).extracting("x","y").isEqualTo(Arrays.asList(0,1));

        cart.tick(CROSSING); //second crossing STRAIGHT
        assertThat(cart.getPosition()).extracting("x","y").isEqualTo(Arrays.asList(5,5));
        assertThat(cart.getDirection()).extracting("x","y").isEqualTo(Arrays.asList(0,1));

        cart.tick(CROSSING); //third crossing RIGHT
        assertThat(cart.getPosition()).extracting("x","y").isEqualTo(Arrays.asList(5,6));
        assertThat(cart.getDirection()).extracting("x","y").isEqualTo(Arrays.asList(-1,0));

        cart.tick(CROSSING); //fourth crossing LEFT again
        assertThat(cart.getPosition()).extracting("x","y").isEqualTo(Arrays.asList(4,6));
        assertThat(cart.getDirection()).extracting("x","y").isEqualTo(Arrays.asList(0,1));
    }
}