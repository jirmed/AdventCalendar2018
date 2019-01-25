package net.konzult.adventcalendar2018;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author jiri21
 */
public class Device {

    public static final int MAX_CALIBRATE_LOOPS = 1000;

    private static void calibrate(String filename) throws IOException {
        Device device = new Device();
        File file = new File(".//Resources//" + filename);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found");
        }
        String st;
        while ((st = br.readLine()) != null) {
            device.adjust(Integer.parseInt(st));
        }
        System.out.println(device);
    }

    private int frequency;
    private boolean calibrated;

    public Device() {
        this.frequency = 0;
    }

    public Device adjust(int adjustment) {
        this.frequency += adjustment;
        return this;
    }

    public Device calibrate(List<Integer> freqs) throws CannotCalibrateException {
        List<Integer> usedFreqs = new ArrayList<>();
        this.setCalibrated(false);
        usedFreqs.add(this.getFrequency());
        for (int i = 0; i < MAX_CALIBRATE_LOOPS; i++) {
            for (Integer freq : freqs) {
                this.adjust(freq);
                if (usedFreqs.contains(this.getFrequency())) {
                    this.setCalibrated(true);
                    return this;
                } else
                    usedFreqs.add(this.getFrequency());
            }
        }

        throw new CannotCalibrateException();

    }

    public int calculateChecksum(List<String> ids) {
        int twos = 0;
        int threes = 0;

        for (String id : ids) {
            for (char c : id.toCharArray()) {
                if (id.chars().filter(ch -> ch == c).count() == 2) {
                    twos++;
                    break;
                }
            }
            for (char c : id.toCharArray()) {
                if (id.chars().filter(ch -> ch == c).count() == 3) {
                    threes++;
                    break;
                }
            }
        }
        return twos*threes;
    }

    public String findSimilar(List<String> ids) {
        for (int i = 0; i < ids.size(); i++) {
            Box box = new Box(ids.get(i));
            String similarId = box.findFirstSimilarId(ids);
            if (similarId != null ) {
                return box.findCommonPart(similarId);
            }
        }
        return null;
    }



    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public boolean isCalibrated() {
        return calibrated;
    }

    public void setCalibrated(boolean calibrated) {
        this.calibrated = calibrated;
    }

    @Override
    public String toString() {
        return "Device{" + "frequency=" + frequency + '}';
    }

}
