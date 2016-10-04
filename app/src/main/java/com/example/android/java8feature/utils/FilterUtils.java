package com.example.android.java8feature.utils;

import android.util.Log;

import com.example.android.java8feature.model.ToDoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Simple utility class, used in {@link com.example.android.java8feature.view.MainActivity}
 *
 * @author pranit
 * @version 1.0
 * @since 29/9/16
 */

public class FilterUtils {

    /**
     * Utility class should not have public constructor
     */
    private FilterUtils() {
        throw new IllegalAccessError("Utility class");
    }

    private static final String TAG = FilterUtils.class.getSimpleName();

    /**
     * This method is used to filter list which contains query text. Which showcases use of Utility APIs: {@link java.util.function}
     *
     * @param todos     list of todos
     * @param mapper    will map {@link ToDoModel} to {@link ToDoModel#getTitle()}
     * @param checkText this is {@link Predicate} functional interface used for checking qiery text.
     * @param block     this is @{@link Consumer} functional interface which return filtered {@code List<ToDoModel>}
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html">java.util.function</a>
     */
    public static void filter(List<ToDoModel> todos, Function<ToDoModel, String> mapper, Predicate<String> checkText, Consumer<List<ToDoModel>> block) {
        final List<ToDoModel> filteredModelList = new ArrayList<>();

        for (ToDoModel toDo : todos) {
            final String title = mapper.apply(toDo);
            if (checkText.test(title)) {
                filteredModelList.add(toDo);
            }
        }
        block.accept(filteredModelList);
    }

    /**
     * Same as {@code filter} showcases different use case of functional interface
     *
     * @param todos     list of todos
     * @param query     query text
     * @param checkText condition that we verifying
     * @param mapper    maps {@link ToDoModel} to {@link ToDoModel#getTitle()}
     * @param block     prints logs
     */
    public static void filterLogger(List<ToDoModel> todos, String query, Predicate<ToDoModel> checkText, Function<ToDoModel, String> mapper, Consumer<String> block) {
        for (ToDoModel toDo :
                todos) {
            final String title = toDo.getTitle().toLowerCase();
            if (title.contains(query)) {
                final String text = mapper.apply(toDo);
                block.accept(text);
            }
        }
    }

    /**
     * Following three methods showing step by step use of {@link Predicate}, {@link Consumer} and {@link Function}
     *
     * @param todos        list of todos
     * @param lengthTester if condition before printing logs
     */
    public static void printTodosWithPredicate(List<ToDoModel> todos, Predicate<ToDoModel> lengthTester) {
        for (ToDoModel toDo : todos
                ) {
            if (lengthTester.test(toDo)) {
                Log.i(TAG, "printTodosWithPredicate: " + toDo.getTitle());
            }
        }

    }

    public static void printTodosWithPredicateConsume(List<ToDoModel> todos, Predicate<ToDoModel> lengthTester, Consumer<ToDoModel> block) {
        for (ToDoModel toDo : todos
                ) {
            if (lengthTester.test(toDo)) {
                block.accept(toDo);
            }
        }
    }

    public static void printTodosWithPredicateFunctionConsume(List<ToDoModel> todos, Predicate<ToDoModel> lengthTester, Function<ToDoModel, String> mapper, Consumer<String> block) {
        for (ToDoModel toDo : todos
                ) {
            if (lengthTester.test(toDo)) {
                String title = mapper.apply(toDo);
                block.accept(title);
            }
        }
    }
}
