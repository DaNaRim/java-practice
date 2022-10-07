package multithreading.itvdn.lesson2;

import java.util.concurrent.*;

public class FutureIsDone {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Main начал работу");
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<String> callable = () -> {
            System.out.printf("%s начал работу\n", Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(10);
            return Thread.currentThread().getName();
        };

        Future<String> future = executorService.submit(callable);

        System.out.println("Main продолжает работу");
        TimeUnit.SECONDS.sleep(3);

        while (!future.isDone()) {
            System.out.println("Асинхронная задача исполняется. Ожидаю...");
            TimeUnit.SECONDS.sleep(1);
        }

        System.err.printf("Результат из потока %s\n", future.get());

        executorService.shutdown();

        System.out.println("Main закончил работу");
    }
}
