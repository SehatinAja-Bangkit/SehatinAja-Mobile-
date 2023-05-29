// Generated by view binder compiler. Do not edit!
package com.example.sehatinaja.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.sehatinaja.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginWithPhoneBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageButton backBtn3;

  @NonNull
  public final EditText editTextOtp;

  @NonNull
  public final EditText editTextPhone;

  @NonNull
  public final AppCompatButton kodeOtp;

  @NonNull
  public final AppCompatButton loginBtn;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView2;

  private ActivityLoginWithPhoneBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageButton backBtn3, @NonNull EditText editTextOtp, @NonNull EditText editTextPhone,
      @NonNull AppCompatButton kodeOtp, @NonNull AppCompatButton loginBtn,
      @NonNull TextView textView, @NonNull TextView textView2) {
    this.rootView = rootView;
    this.backBtn3 = backBtn3;
    this.editTextOtp = editTextOtp;
    this.editTextPhone = editTextPhone;
    this.kodeOtp = kodeOtp;
    this.loginBtn = loginBtn;
    this.textView = textView;
    this.textView2 = textView2;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginWithPhoneBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginWithPhoneBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login_with_phone, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginWithPhoneBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.back_btn3;
      ImageButton backBtn3 = ViewBindings.findChildViewById(rootView, id);
      if (backBtn3 == null) {
        break missingId;
      }

      id = R.id.editTextOtp;
      EditText editTextOtp = ViewBindings.findChildViewById(rootView, id);
      if (editTextOtp == null) {
        break missingId;
      }

      id = R.id.editTextPhone;
      EditText editTextPhone = ViewBindings.findChildViewById(rootView, id);
      if (editTextPhone == null) {
        break missingId;
      }

      id = R.id.kodeOtp;
      AppCompatButton kodeOtp = ViewBindings.findChildViewById(rootView, id);
      if (kodeOtp == null) {
        break missingId;
      }

      id = R.id.login_btn;
      AppCompatButton loginBtn = ViewBindings.findChildViewById(rootView, id);
      if (loginBtn == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      return new ActivityLoginWithPhoneBinding((ConstraintLayout) rootView, backBtn3, editTextOtp,
          editTextPhone, kodeOtp, loginBtn, textView, textView2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}