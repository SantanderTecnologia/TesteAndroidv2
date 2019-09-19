package com.example.testesantander.login;

import com.example.testesantander.infra.ApiServices;
import com.example.testesantander.infra.BaseCallback;
import com.example.testesantander.infra.RetrofitClient;
import com.example.testesantander.domain.contract.UserContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginAuth implements UserContract.IUserRepository{
    @Override
    public void login(String username, String password, final BaseCallback<LoginResponse> onResult) {
        Call<LoginResponse> call = RetrofitClient // instância da classe RetrofitClient
                .getInstance()
                .createService(ApiServices.class) // criando o serviço que esta na classe ApiServices
                .userLogin(username, password); // instância do metodo de Login
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    LoginResponse loginResponse = response.body();
                   onResult.onSuccessful(loginResponse);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
               onResult.onUnsucessful(t.getMessage());
            }
        });
    }
}
