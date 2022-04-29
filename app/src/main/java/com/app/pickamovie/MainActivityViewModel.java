package com.app.pickamovie;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;

public class MainActivityViewModel extends AndroidViewModel {

    private final UserRepo userRepository;

    public MainActivityViewModel(Application app) {
        super(app);
        userRepository = UserRepo.getInstance(app);
    }

    public void init() {
        String userId = userRepository.getCurrentUser().getValue().getUid();
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepository.getCurrentUser();
    }

    public void signOut() {
        userRepository.signOut();
    }
}
