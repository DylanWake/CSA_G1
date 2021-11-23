package io.dylanwake.execution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * A system that preforms multithreadig in calculations
 */
public class ExecutionControl {
      public static void __allocSynced(Dim3i blockSize, Consumer<ArrayList<Object>> func, ArrayList<Object> args) {
          int[][] executionFlags = new int[blockSize.x][blockSize.y];
          ArrayList<Thread> threads = new ArrayList<>();
          for (int i = 0; i < blockSize.x; i++) {
              for (int j = 0; j < blockSize.y; j++) {
                  ArrayList<Object> temp = new ArrayList<>(args);
                  temp.add(executionFlags);
                  temp.add(blockSize);
                  temp.add(new Dim3i(i,j));
                  threads.add(new Thread(() -> func.accept(temp)));
              }
          }
          threads.forEach(Thread::start);
          threads.forEach(t -> {
              try{
                  t.join();
              }catch (InterruptedException e){
                  e.printStackTrace();
              }
          });
      }

      public static void __syncThreads(Dim3i tid, int[][] executionFlags) {
          executionFlags[tid.x][tid.y]++;
          int tmp = executionFlags[tid.x][tid.y];
          START:
          for (int i = 0; i < executionFlags.length; i++) {
              for (int j = 0; j < executionFlags[0].length; j++) {
                  if (executionFlags[i][j] < tmp) {
                      continue START;
                  }
              }
          }
      }
}
