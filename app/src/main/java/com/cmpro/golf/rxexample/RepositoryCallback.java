package com.cmpro.golf.rxexample;


import com.cmpro.golf.rxexample.Result;

public interface RepositoryCallback<T> {
    void onComplete(Result<T> result);
}

