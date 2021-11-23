package io.dylanwake.conv;

import io.dylanwake.execution.Dim3i;
import io.dylanwake.execution.ExecutionControl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * edge detector
 */
public class EdgeDetection {
    public static float[][][] convs = new float[][][]{{{1, 2, 1}, {0, 0, 0}, {-1, 2, -1}},
            {{1, 0, -1}, {3, 0, -2}, {1, 0, -1}},
            {{2, 1, 0}, {1, 0, -1}, {0, -1, -2}},
            {{0, -1, -2}, {1, 0, -1}, {2, 1, 0}}};

    public static float sigmoid(float in) {
        return (float) (1 / (1 + Math.exp(-in)));
    }

    public static void convolution(float[][] src, float[][][] maps, float[][][] cores, int x, int y) {
        for (int i = 0; i < maps.length; i++) {
            maps[i][y][x] = 0;
            for (int x0 = -1; x0 < 2; x0++) {
                for (int y0 = -1; y0 < 2; y0++) {
                    maps[i][y][x] += cores[i][y0 + 1][x0 + 1] * src[y + y0][x + x0];
                }
            }
        }
    }

    public static void prepare(ArrayList<Object> args){
        int[][] executionFlags = (int[][]) args.get(args.size() - 3);
        Dim3i blockSize = (Dim3i) args.get(args.size() - 2);
        Dim3i tid = (Dim3i) args.get(args.size() - 1);

        BufferedImage input = (BufferedImage) args.get(0);
        float[][] buffer = (float[][]) args.get(1);
        Dim3i procIndex = new Dim3i( tid.x * (input.getWidth() / blockSize.x), tid.y * (input.getHeight() / blockSize.y));
        System.out.println(procIndex);

        for (int y = 0; y < (tid.y == blockSize.y - 1 ? input.getHeight() - procIndex.y
                : input.getHeight() / blockSize.y); y++) {
            for (int x = 0; x < (tid.x == blockSize.x - 1 ? input.getWidth() - procIndex.x
                    : input.getWidth() / blockSize.x); x++) {
                int rgb = input.getRGB(procIndex.x + x, procIndex.y + y);
                int brightNess = (rgb >> 16 & 0xff) + (rgb >> 8 & 0xff) + (rgb & 0xff);
                buffer[procIndex.y + y + 1][procIndex.x + x + 1] = (float)brightNess / (256F * 3);
            }
        }
    }

    public static void detect(ArrayList<Object> args) {
        int[][] executionFlags = (int[][]) args.get(args.size() - 3);
        Dim3i blockSize = (Dim3i) args.get(args.size() - 2);
        Dim3i tid = (Dim3i) args.get(args.size() - 1);

        BufferedImage input = (BufferedImage) args.get(0);
        BufferedImage output = (BufferedImage) args.get(1);
        float[][] buffer = (float[][]) args.get(2);
        float[][][] featureMaps = (float[][][]) args.get(3);
        Dim3i procIndex = new Dim3i( tid.x * (input.getWidth() / blockSize.x), tid.y * (input.getHeight() / blockSize.y));

        for (int y = 0; y < (tid.y == blockSize.y - 1 ? input.getHeight() - procIndex.y
                : input.getHeight() / blockSize.y); y++) {
            for (int x = 0; x < (tid.x == blockSize.x - 1 ? input.getWidth() - procIndex.x
                    : input.getWidth() / blockSize.x); x++) {
                convolution(buffer, featureMaps, convs, procIndex.x + x + 1, procIndex.y + y + 1);
            }
        }
        ExecutionControl.__syncThreads(tid, executionFlags);

        for (int y = 0; y < (tid.y == blockSize.y - 1 ? input.getHeight() - procIndex.y
                : input.getHeight() / blockSize.y); y++) {
            for (int x = 0; x < (tid.x == blockSize.x - 1 ? input.getWidth() - procIndex.x
                    : input.getWidth() / blockSize.x); x++) {
                float sum = 0;
                //sum += featureMaps[1][procIndex.y + y + 1][procIndex.x + x + 1];
                sum += featureMaps[2][procIndex.y + y + 1][procIndex.x + x + 1];
                //sum += buffer[procIndex.y + y + 1][procIndex.x + x + 1];
                sum = sigmoid(Math.abs(sum));
                //System.out.println(sum);
                output.setRGB(procIndex.x + x, procIndex.y + y, new Color(sum, sum, sum).getRGB());
            }
        }
    }

    public static BufferedImage convDetector(BufferedImage input) {
        float[][] buffer = new float[input.getHeight() + 2][input.getWidth() + 2];
        float[][][] featureMaps = new float[4][input.getHeight() + 2][input.getWidth() + 2];
        System.out.println(input.getHeight()+ " " + input.getWidth());

        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_RGB);
        ArrayList<Object> args = new ArrayList<>();
        args.add(input);
        args.add(buffer);
        ExecutionControl.__allocSynced(new Dim3i(1, 12), EdgeDetection::prepare, args);

        args = new ArrayList<>();
        args.add(input);
        args.add(output);
        args.add(buffer);
        args.add(featureMaps);
        ExecutionControl.__allocSynced(new Dim3i(1, 12), EdgeDetection::detect, args);
        return output;
    }
}

