package com.example.batch54.ui.fragments.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.batch54.databinding.FragmentChatBinding;
import com.example.batch54.ui.fragments.chat.viewmodel.AddToUpcomingViewmodel;
import com.example.batch54.ui.fragments.chat.viewmodel.ChatViewmodel;

import java.util.Objects;

public class ChatFragment extends Fragment {
    private FragmentChatBinding binding;
    private ChatViewmodel viewmodel;
    private AddToUpcomingViewmodel upcomingViewmodel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(inflater, container, false);
        viewmodel = new ViewModelProvider(this).get(ChatViewmodel.class);
        upcomingViewmodel = new ViewModelProvider(this).get(AddToUpcomingViewmodel.class);

        handleMessageClick();
        responseOnObservers();

        return binding.getRoot();
    }

    /**
     * on message send click the inserted message update the value
     * of message in viewmodel
     */
    private void handleMessageClick() {
        binding.message.setOnEditorActionListener((v, actionId, event) -> {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                viewmodel.setMessage(Objects.requireNonNull(binding.message.getText()).toString());
                handled = true;
            }
            return handled;
        });
    }

    /**
     * keep updating the UI based on Viewmodel value changes
     */
    private void responseOnObservers() {
        viewmodel.getMessage().observe(getViewLifecycleOwner(), s -> {
            Thread thread = new Thread(viewmodel);
            thread.start();
        });

        viewmodel.getIsMessageHashed().observe(getViewLifecycleOwner(), aBoolean -> {
            upcomingViewmodel.setHashedMessage(Objects.requireNonNull(binding.message.getText()).toString());
            Thread thread = new Thread(upcomingViewmodel);
            thread.start();
        });

        upcomingViewmodel.getIsSuccessfullyAdded().observe(getViewLifecycleOwner(), aBoolean ->
                Toast.makeText(getContext(), "Hashed messaged detected!! Added to trending", Toast.LENGTH_SHORT).show());
    }
}