package net.konzult.adventcalendar2018.day1;

import net.konzult.adventcalendar2018.day16.Command;
import net.konzult.adventcalendar2018.day16.Processor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author jiri21
 */
public class Device {

    public static final int MAX_CALIBRATE_LOOPS = 1000;

    private int frequency;
    private boolean calibrated;
    private Processor processor = new Processor(5);


    public Device() {
        this.frequency = 0;
    }

    public Device adjust(int adjustment) {
        this.frequency += adjustment;
        return this;
    }

    public Device calibrate(List<Integer> freqs) throws CannotCalibrateException {
        Set<Integer> usedFreqs = new HashSet<>();
        this.setCalibrated(false);
        usedFreqs.add(this.getFrequency());
        for (int i = 0; i < MAX_CALIBRATE_LOOPS; i++) {
            for (Integer freq : freqs) {
                this.adjust(freq);
                if (!usedFreqs.add(frequency)) {
                    this.setCalibrated(true);
                    return this;
                }
            }
        }
        throw new CannotCalibrateException();
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
