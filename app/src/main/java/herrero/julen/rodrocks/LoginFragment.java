package herrero.julen.rodrocks;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import herrero.julen.rodrocks.databinding.FragmentLoginBinding;
import viewmodel.UserViewModel;

public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    NavController navController;
    UserViewModel userViewModel;
    Executor executor;
    HashFunction hf;
    private boolean pwdShown = false;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login, container, false);
        return (binding = FragmentLoginBinding.inflate(inflater, container, false)).getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        executor = Executors.newSingleThreadExecutor();

        binding.goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_loginFragment_to_signUpFragment);
            }
        });

        binding.showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int inputType = binding.passwordLoginText.getInputType();

                pwdShown = !pwdShown;

                if (pwdShown) {
                    binding.passwordLoginText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    binding.passwordLoginText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                binding.passwordLoginText.setSelection(binding.passwordLoginText.getText().length());
            }
        });

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hf = Hashing.sha256();
                String usernameInput = binding.userLoginText.getText().toString();
                String pwdInput = binding.passwordLoginText.getText().toString();
                HashCode pwdIn = hf.newHasher()
                        .putString(pwdInput, Charsets.UTF_8)
                        .hash();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        String hashedDb = userViewModel.getPasswordLogin(usernameInput);
                        if (pwdIn.toString().equals(hashedDb)) {
                            requireActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    navController.navigate(R.id.action_loginFragment_to_homeFragment);
                                }
                            });
                        } else {
                            requireActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast t = Toast.makeText(getContext(), "Login failed, please try again", Toast.LENGTH_LONG);
                                    t.show();
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}