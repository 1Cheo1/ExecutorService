package com.cmpro.golf.rxexample;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App  extends Application {
    //현재 상태의 프로세스 갯수를 얻는다.
    private static int NUMBER_OR_CORES = Runtime.getRuntime().availableProcessors();
     public ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OR_CORES);
     public Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());

}
