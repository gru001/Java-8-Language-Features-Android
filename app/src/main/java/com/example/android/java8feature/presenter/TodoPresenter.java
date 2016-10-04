package com.example.android.java8feature.presenter;

import android.util.Log;

import com.example.android.java8feature.model.JsonPlaceHolderService;
import com.example.android.java8feature.model.ToDoModel;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description Please
 *
 * @author pranit
 * @version 1.0
 * @since 29/9/16
 */

public class TodoPresenter implements TodoContract.UserActionListener {
    private static final String TAG = TodoPresenter.class.getSimpleName();

    private Subscription subscription;
    private List<ToDoModel> mToDos;
    private TodoContract.View view;

    public TodoPresenter(TodoContract.View view) {
        this.view = view;
    }

    @Override
    public void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
    }

    @Override
    public void loadTodos() {
        view.setProgressBar(true);
        Observable<List<ToDoModel>> observable = JsonPlaceHolderService.Factory.create().getAllToDos();
        subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ToDoModel>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                        view.setProgressBar(false);
                        if (mToDos != null || mToDos.size() > 0) {
                            view.onSuccessLoadTodos(mToDos);
                        } else {
                            view.onError();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                        view.setProgressBar(false);
                        view.onError();
                    }

                    @Override
                    public void onNext(List<ToDoModel> toDos) {
                        Log.i(TAG, "onNext: ");
                        TodoPresenter.this.mToDos = toDos;
                    }
                });
    }
}
