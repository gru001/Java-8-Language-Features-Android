package com.example.android.java8feature.utils;

import android.util.Log;

import com.example.android.java8feature.model.ToDo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Description Please
 *
 * @author pranit
 * @version 1.0
 * @since 29/9/16
 */

public class FilterUtils {
    private FilterUtils() {
        throw new IllegalAccessError("Utility class");
    }

    private static final String TAG = FilterUtils.class.getSimpleName();

    public static void filter(List<ToDo> todos, Function<ToDo, String> mapper, Predicate<String> checkText, Consumer<List<ToDo>> block) {
        final List<ToDo> filteredModelList = new ArrayList<>();

        Iterator<ToDo> iterator = todos.iterator();
        while (iterator.hasNext()) {
            final ToDo toDo = iterator.next();
            final String title = mapper.apply(toDo);
            if (checkText.test(title)) {
                filteredModelList.add(toDo);
                block.accept(filteredModelList);
            }
        }

    }

    public static void filterLogger(List<ToDo> todos, String query, Predicate<ToDo> checkText, Function<ToDo, String> mapper, Consumer<String> block) {
        for (ToDo toDo :
                todos) {
            final String title = toDo.getTitle().toLowerCase();
            if (title.contains(query)) {
                final String text = mapper.apply(toDo);
                block.accept(text);
            }
        }
    }

    public static void printTodosWithPredicate(List<ToDo> todos, Predicate<ToDo> lengthTester) {
        for (ToDo toDo : todos
                ) {
            if (lengthTester.test(toDo)) {
                Log.i(TAG, "printTodosWithPredicate: " + toDo.getTitle());
            }
        }

    }

    public static void printTodosWithPredicateConsume(List<ToDo> todos, Predicate<ToDo> lengthTester, Consumer<ToDo> block) {
        for (ToDo toDo : todos
                ) {
            if (lengthTester.test(toDo)) {
                block.accept(toDo);
            }
        }
    }

    public static void printTodosWithPredicateFunctionConsume(List<ToDo> todos, Predicate<ToDo> lengthTester, Function<ToDo, String> mapper, Consumer<String> block) {
        for (ToDo toDo : todos
                ) {
            if (lengthTester.test(toDo)) {
                String title = mapper.apply(toDo);
                block.accept(title);
            }
        }
    }
}
