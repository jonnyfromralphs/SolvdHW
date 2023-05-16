package org.example.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambdas {

    public static <T> List<T> generateElements(int count, Supplier<T> supplier) {
        List<T> generatedList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            T generatedElement = supplier.get();
            generatedList.add(generatedElement);
        }
        return generatedList;
    }

    public static <T> List<T> filterElements(List<T> elements, Predicate<T> predicate) {
        List<T> filteredList = new ArrayList<>();
        for (T element : elements) {
            if (predicate.test(element)) {
                filteredList.add(element);
            }
        }
        return filteredList;
    }

    public static <T, R> List<R> transformElements(List<T> elements, Function<T, R> function) {
        List<R> transformedList = new ArrayList<>();
        for (T element : elements) {
            R transformedElement = function.apply(element);
            transformedList.add(transformedElement);
        }
        return transformedList;
    }

    public static <T, U, R> R performOperation(T arg1, U arg2, BiFunction<T, U, R> function) {
        return function.apply(arg1, arg2);
    }
}
