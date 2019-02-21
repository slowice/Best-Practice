package xb;

import org.springframework.stereotype.Controller;
import sun.jvmstat.perfdata.monitor.PerfStringVariableMonitor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Controller
public class SimpleTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> taskA = new FutureTask<>(new SimpleTaskA());
        FutureTask<Integer> taskB = new FutureTask<>(new SimpleTaskB());
        FutureTask<Integer> taskC = new FutureTask<>(new SimpleTaskC());
        Thread a = new Thread(taskA);
        Thread b = new Thread(taskB);
        Thread c = new Thread(taskC);
        a.start();
        b.start();
        c.start();
        int result = taskA.get()+taskB.get()+taskC.get();
        System.out.println( "*****************" + result);
    }

    static class SimpleTaskA implements Callable {
        @Override
        public Object call() throws Exception {
            return 1;
        }
    }
    static class SimpleTaskB implements Callable{
        @Override
        public Object call() throws Exception {
            return 2;
        }
    }
    static class SimpleTaskC implements Callable{
        @Override
        public Object call() throws Exception {
            return 3;
        }
    }
}
