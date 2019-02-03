package net.konzult.adventcalendar2018.day10;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void testReadMessageFile() throws Exception {
        Message message = Message.readMessageFile("day10test.txt");

        assertThat(message.getPoints().size()).isEqualTo(31);
    }

    @Test
    void testStep() throws Exception {
        Message message = Message.readMessageFile("day10test.txt");

        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
            for (String line : message.render()) {
                System.out.println(line);
            }
            message.step();
        }
    }

    @Test
    void testGetSmallestMessage() throws Exception {
        Message message = Message.readMessageFile("day10test.txt");
        List<String> smallestMessage = message.getSmallestMessage();
        for (String line : smallestMessage) {
            System.out.println(line);
        }
        assertThat(smallestMessage.size()).isEqualTo(12);
        assertThat(message.getMinute()).isEqualToComparingFieldByFieldRecursively(3);

    }
}