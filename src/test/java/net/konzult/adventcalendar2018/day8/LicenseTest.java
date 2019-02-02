package net.konzult.adventcalendar2018.day8;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LicenseTest {

    @Test
    void testReadLicenseFile() throws Exception {
        License license = License.readLicenseFile("day8test.txt");

        assertThat(license.getSource().length).isEqualTo(16);
        assertThat(license.getRootNode().getChildren().size()).isEqualTo(2);
        assertThat(license.getRootNode().getMetadata().size()).isEqualTo(3);
        assertThat(license.getCheckSum()).isEqualTo(138);
        assertThat(license.getRootNode().getValue()).isEqualTo(66);

    }

}