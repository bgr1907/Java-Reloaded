package com.bgr.future;

import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.concurrent.*;

public class CompletableFutureExample {

    private static Executor executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
        CompletableFuture<String> firstExample = new CompletableFuture<>();
        firstExample.complete("Merhaba");
        String result = firstExample.get();
        System.out.println(result);

        System.out.println(Thread.currentThread().getName());
         */

        //runAsyncExample();

        //String message = runSupplyExample().get();
        //System.out.println(message);

        /*CompletableFuture<String> onlineEducation =
        getEducationName().thenApply(name -> "Online " + name + " Education");

        String educationFullName = onlineEducation.get();
        System.out.println(educationFullName);

        getStudentListAsync().get();*/

        /*CompletableFuture<OptionalDouble> average = getStudentDetails(1)
                .thenCompose(CompletableFutureExample::getStudentAverage);

        System.out.println(average.get().getAsDouble());*/

        System.out.println("Getting Euro exchange...");
        CompletableFuture<Double> euro = euroExchange();

        System.out.println("Getting Usd exchange...");
        CompletableFuture<Double> usd = usdExchange();

        CompletableFuture<Double> parity = euro.thenCombine(usd, (e, u) -> e/u);
        System.out.println(parity.get());

        getEducationName().thenApply(name -> "Online " + name + " Education")
        .handle((r, e) -> {
            return r;
        });

    }

    public static CompletableFuture<Double> euroExchange() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 3.0;
        });
    }

    public static CompletableFuture<Double> usdExchange() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2.0;
        });
    }


    public static CompletableFuture<Student> getStudentDetails(final Integer studentId){
        return CompletableFuture.supplyAsync(() -> StudentService.studentDetail(studentId));
    }

    public static CompletableFuture<OptionalDouble> getStudentAverage(final Student student){
        return CompletableFuture.supplyAsync(() -> GradeService.getGradeAverage(student));
    }

    public static CompletableFuture<Void> getStudentListAsync(){
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return getStudentList();
        }).thenAccept(list -> System.out.println("List size " + list.size()));
    }

    public static List<String> getStudentList(){
        return Arrays.asList("Ahmet", "Mehmet", "Hasan");
    }

    public static CompletableFuture<String> getEducationName(){
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Java Reloaded";
        });
    }

    public static CompletableFuture<Void> runAsyncExample() {
        return CompletableFuture.runAsync(() ->
                {
                    System.out.println("Inside runAsyncExampleMethod " + Thread.currentThread().getName());
                    System.out.println("Merhaba asenkron programlama");
                }, executor);
    }

    public static CompletableFuture<String> runSupplyExample() {
        return CompletableFuture.supplyAsync(() -> "hello runSupplyExample");
    }
}
