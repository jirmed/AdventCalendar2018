package net.konzult.adventcalendar2018.day16;

public class Command {
    private final Instruction instruction;
    private final int a,b,c;

    public Command(Instruction instruction, int a, int b, int c) {
        this.instruction = instruction;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    @Override
    public String toString() {
        return "Command{" +
                "instruction=" + instruction +
                ", a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
