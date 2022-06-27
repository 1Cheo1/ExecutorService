package com.cmpro.golf.rxexample.repository;

import android.os.Handler;

import com.cmpro.golf.rxexample.RepositoryCallback;
import com.cmpro.golf.rxexample.Result;

import java.util.concurrent.Executor;

public class NumberRepository {

    private final Executor executor;
    private final Handler resultHandler;

    public NumberRepository(Handler resultHandler, Executor executor) {
        this.resultHandler = resultHandler;
        this.executor = executor;
    }

    public void longTask(RepositoryCallback<Integer> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // background 에서 수행될 코드
                    int num = 0;
                    for (int i = 0; i < 100; i++) {
                        num++;
                        // UI 갱신 위해서 콜백
                        Result<Integer> result = new Result.Success<>(num);
                        notifyResult(result, callback);
                        Thread.sleep(100);
                    }
                } catch (Exception e) {
                    Result<Integer> result = new Result.Error<Integer>(e);
                    notifyResult(result, callback);
                }
            }
        });
    }

    private void notifyResult(
            final Result<Integer> result,
            final RepositoryCallback<Integer> callback) {
        resultHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onComplete(result);
            }
        });
    }

}


