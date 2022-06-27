package com.cmpro.golf.rxexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cmpro.golf.rxexample.repository.NumberRepository;

import java.util.concurrent.Executor;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private NumberRepository repository;
    private Executor executor;
    private Handler resultHandler;
    private App app;
    private Button button;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app = new App();
        executor = app.executorService;
        resultHandler = app.mainThreadHandler;

        button = findViewById(R.id.button);
        repository = new NumberRepository(resultHandler, executor);
        textView = findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repository.longTask(result -> {
                    if (result instanceof Result.Success) {
                        Log.d(TAG, result.toString());
                        textView.setText(((Result.Success<Integer>) result).data.toString());
                    }
                });
            }
        });


    }
}