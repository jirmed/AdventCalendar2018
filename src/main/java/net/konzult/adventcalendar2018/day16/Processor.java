package net.konzult.adventcalendar2018.day16;

import java.util.*;
import java.util.regex.Pattern;

public class Processor {


    private int[] reg;
    private Map<Integer, Instruction> instructionMap = new HashMap<>();
    private int ipRegister = -1;
    private List<Command> program;
    private long instrCount;
    private int minReg5At28 = Integer.MAX_VALUE;
    private Set<Integer> reg5Set = new HashSet<>();


    public Processor() {
        this(4);
    }

    public Processor(int registers) {
        reg = new int[registers];
    }


    public void parseProgram(List<String> strings) {
        ipRegister = Pattern.compile("^#ip\\s+(\\d+)$")
                .matcher(strings.get(0))
                .results()
                .map(m -> m.group(1))
                .mapToInt(Integer::parseInt)
                .findFirst()
                .orElse(-1);
        List<Command> p = new ArrayList<>();
        strings.subList(1, strings.size())
                .stream().forEach(s -> {
            String[] parts = s.split(" ");
            p.add(new Command(
                    Instruction.valueOf(parts[0].toUpperCase()),
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2]),
                    Integer.parseInt(parts[3])));
        });
        program = Collections.unmodifiableList(p);
    }

    public void parseProgramRaw(List<String> strings) {
        List<Command> p = new ArrayList<>();
        strings.stream().forEach(s -> {
            int[] parts = Arrays.stream(s.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            p.add(new Command(
                    instructionMap.get(parts[0]),
                    parts[1],
                    parts[2],
                    parts[3]));
        });
        program = Collections.unmodifiableList(p);
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
        instrCount++;
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

    public int[] operation(Command command) {
        return operation(command.getInstruction(), command.getA(), command.getB(), command.getC());
    }

    public void run(int breakpoint, long maxOperations) {
        if (ipRegister < 0)
            program.stream().forEach(line
                    -> this.operation(line));
        else {
            int ip = reg[ipRegister];
            while (ip < program.size() && ip != breakpoint && (maxOperations == 0 || maxOperations >= instrCount)) {
                Command command = program.get(ip);
                if (ip == 28) {
                    if (!reg5Set.contains(reg[5])) {
                        minReg5At28 = reg[5];
                        reg5Set.add(reg[5]);
                        System.out.println(minReg5At28);
                    }
                }
                operation(command);
                ip = ++reg[ipRegister];
            }
        }
    }

    public void run(boolean speedUp) {
        if (speedUp) {
            run(2, 0);
            reg[0] = getSumOfDividers(reg[4]);
        } else {
            run(-1, 0);
        }
    }

    public static int getSumOfDividers(int input) {
        int result = input;
        for (int i = 1; i <= input / 2 + 1; i++) {
            if (input % i == 0)
                result += i;
        }
        return result;
    }

    public int getIpRegister() {
        return ipRegister;
    }

    public void setIpRegister(int ipRegister) {
        this.ipRegister = ipRegister;
    }

    public List<Command> getProgram() {
        return program;
    }

    public long getInstrCount() {
        return instrCount;
    }

    public void reset() {
        reg = new int[reg.length];
        instrCount = 0;
    }
}
