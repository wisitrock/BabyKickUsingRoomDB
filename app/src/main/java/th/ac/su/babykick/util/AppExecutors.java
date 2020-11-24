package th.ac.su.babykick.util;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors { // คลาสที่ใช้สำหรับการเรียกใช้ Thread เพื่อทำการ ดึงข้อมูลจาก database แล้วใช้ Thread เพื่อไป set  UI ต่างๆ

    private final Executor diskIO, mainThread;

    AppExecutors(Executor diskIO, Executor mainThread) {
        this.diskIO = diskIO;
        this.mainThread = mainThread;
    }

    public AppExecutors() {
        this(
                Executors.newSingleThreadExecutor(),
                new MainThreadExecutor()
        );
    }

    public Executor diskIO() {
        return diskIO;
    }

    public Executor mainThread() {
        return mainThread;
    }

    private static class MainThreadExecutor implements Executor {
        private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}