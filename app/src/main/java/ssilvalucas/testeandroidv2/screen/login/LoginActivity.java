package ssilvalucas.testeandroidv2.screen.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ssilvalucas.testeandroidv2.R;
import ssilvalucas.testeandroidv2.data.model.ErrorMessage;
import ssilvalucas.testeandroidv2.data.model.UserAccount;
import ssilvalucas.testeandroidv2.data.storage.UserDataStorage;

interface LoginActivityInput{
    void showErrorMessage(String message);
    void onSuccessfulLogin(UserAccount userAccount);
}

public class LoginActivity extends AppCompatActivity implements LoginActivityInput {

    private static final String TAG = LoginActivity.class.getSimpleName();

    protected LoginInteractorInput output;
    protected LoginRouter router;

    private EditText editTextUsername, editTextPassword;
    private Button   btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginConfigurator.INSTANCE.configure(this);

        initViews();

        btnLogin.setOnClickListener(getOnClickListenerBtnLogin());
    }

    private void initViews(){
        editTextUsername = findViewById(R.id.screen_login_edit_text_user);
        editTextUsername.setText(UserDataStorage.readLastUser(getApplicationContext()));
        editTextPassword = findViewById(R.id.screen_login_edit_text_password);
        btnLogin = findViewById(R.id.screen_login_btn);
    }

    private View.OnClickListener getOnClickListenerBtnLogin(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                output.onLogin(username, password);
            }
        };
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        if(errorMessage == ErrorMessage.MESSAGE_EMPTY_USERNAME || errorMessage == ErrorMessage.MESSAGE_INVALID_USERNAME){
            editTextUsername.setError(errorMessage);
        } else if(errorMessage == ErrorMessage.MESSAGE_EMPTY_PASSWORD || errorMessage == ErrorMessage.MESSAGE_INVALID_PASSWORD){
            editTextPassword.setError(errorMessage);
        } else{

        }
    }

    @Override
    public void onSuccessfulLogin(UserAccount userAccount) {
        router.startHomeActivity(userAccount);
    }
}
