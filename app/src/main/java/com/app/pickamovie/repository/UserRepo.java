package com.app.pickamovie.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.app.pickamovie.viewModel.UserLiveData;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseUser;

public class UserRepo {
    private final UserLiveData currentUser;
    private final Application app;
    private static UserRepo instance;

    private UserRepo(Application app) {
        this.app = app;
        currentUser = new UserLiveData();
    }

    public static synchronized UserRepo getInstance(Application app) {
        if (instance == null)
            instance = new UserRepo(app);
        return instance;
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }

    public void signOut() {
        AuthUI.getInstance()
                .signOut(app.getApplicationContext());
    }
}
