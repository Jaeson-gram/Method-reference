package com.Relearn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

class PlainOld {

    private static int last_id = 1;
    private int id;

    public PlainOld() {
        id = PlainOld.last_id++;
        System.out.println("Creating a Plain Old Object: id = "  + id);
    }
}

public class Main {

    public static void main(String[] args) {
	// write your code here

        List<String> list = new ArrayList<>(List.of(
                "Dave", "Maryanne", "Anita", "Perry", "June"
        ));

        // replacing (s -> System.out.println) with the method reference System.out::println
        list.forEach(System.out::println); list.forEach(s -> System.out.println());

        calculator(Integer::sum, 10, 25); // replacing:  calculator((a, b) -> a + b, 10,25); with the method reference 'Integer::sum'
        calculator(Double::sum, 15.4, 12.4); // replacing: calculator((a, b) -> a + b, 15.4,12.4); with corresponding method reference


        //lambda Xs and method refs don't get executed immediately, but when the targeted functional method is called, as below:

        Supplier<PlainOld> plainOldSupplier = PlainOld::new;
        PlainOld plainOldReference = plainOldSupplier.get();

        //but why do this, why not just instantiate the class and call the method. = We can instantiate multiple classes at oncelike this,
        // see seedArray(), then the code below:
        System.out.println("Getting array...");
        PlainOld[] pojo1 = seedArray(PlainOld::new, 10);

    }

    private static <T> void  calculator(BinaryOperator<T> function, T value1, T value2){

        T result = function.apply(value1, value2); // the BinaryOperator will work on the values. Any operation defined by the calling code - eg x * y
        System.out.println("Result of operation: " + result);
    }

    private static PlainOld[] seedArray(Supplier<PlainOld> reference, int count){
        PlainOld[] array = new PlainOld[count];
        Arrays.setAll(array, i -> reference.get());
        return array;
    }
}
