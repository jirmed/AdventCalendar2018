package net.konzult.adventcalendar2018.day16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Processor {


    private int[] reg = new int[4];
    private Map<Integer, Instruction> instructionMap;
    private List<int[]> program = new ArrayList<>();

    public void parseProgram(List<String> strings) {
        strings.stream().forEach(s -> {
            program.add(Arrays.stream(s.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray());
        });
    }

    public List<int[]> getProgram() {
        return program;
    }

    public void setProgram(List<int[]> program) {
        this.program = program;
    }

    public Map<Integer, Instruction> getInstructionMap() {
        return instructionMap;
    }

    public void setInstructionMap(Map<Integer, Instruction> instructionMap) {
        this.instructionMap = instructionMap;
    }

    public int[] getReg() {
        return reg;
    }

    public void setReg(int[] reg) {
        this.reg = reg;
    }

    @Override
    public String toString() {
        return "Processor{" +
                "reg=" + Arrays.toString(reg) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Processor processor = (Processor) o;
        return Arrays.equals(reg, processor.reg);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(reg);
    }

    public int[] operation(Instruction instruction, int a, int b, int c) {
        switch (instruction) {
            case ADDR:
                reg[c] = reg[a] + reg[b];
                break;
            case ADDI:
                reg[c] = reg[a] + b;
                break;
            case MULR:
                reg[c] = reg[a] * reg[b];
                break;
            case MULI:
                reg[c] = reg[a] * b;
                break;
            case BANR:
                reg[c] = reg[a] & reg[b];
                break;
            case BANI:
                reg[c] = reg[a] & b;
                break;
            case BORR:
                reg[c] = reg[a] | reg[b];
                break;
            case BORI:
                reg[c] = reg[a] | b;
                break;
            case SETR:
                reg[c] = reg[a];
                break;
            case SETI:
                reg[c] = a;
                break;
            case GTIR:
                reg[c] = (a > reg[b] ? 1 : 0);
                break;
            case GTRI:
                reg[c] = (reg[a] > b ? 1 : 0);
                break;
            case GTRR:
                reg[c] = (reg[a] > reg[b] ? 1 : 0);
                break;
            case EQIR:
                reg[c] = (a == reg[b] ? 1 : 0);
                break;
            case EQRI:
                reg[c] = (reg[a] == b ? 1 : 0);
                break;
            case EQRR:
                reg[c] = (reg[a] == reg[b] ? 1 : 0);
                break;
        }
        return reg;
    }

    public void run() {
        program.stream().forEach(line
                -> this.operation(instructionMap.get(line[0]),
                line[1], line[2], line[3]));
    }
}
