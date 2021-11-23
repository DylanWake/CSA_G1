package io.dylanwake.execution;

public class Dim3i {
    public int x;
    public int y;
    public int z;

    public Dim3i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Dim3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "Dim3i{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
