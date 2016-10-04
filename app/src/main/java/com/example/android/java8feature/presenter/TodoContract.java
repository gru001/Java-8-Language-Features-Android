package com.example.android.java8feature.presenter;

import com.example.android.java8feature.model.ToDoModel;

import java.util.List;

/**
 * Description Please
 *
 * @author pranit
 * @version 1.0
 * @since 29/9/16
 */

public interface TodoContract {
    interface View {
        void setProgressBar(boolean active);

        void onSuccessLoadTodos(List<ToDoModel> todos);

        void onError();
    }

    interface UserActionListener {
        void loadTodos();

        void onDestroy();
    }
}
