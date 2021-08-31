package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.myapplication.model.UserLoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val editId: EditText by lazy {
        findViewById(R.id.editId)
    }
    private val editPassword: EditText by lazy {
        findViewById(R.id.editPassword)
    }
    private val loginButton: Button by lazy {
        findViewById(R.id.loginButton)
    }
    private val signButton: Button by lazy {
        findViewById(R.id.signButton)
    }

    private var id: String? = null
    private var password: String? = null
    private var loginButtonOn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        initButton()
        initEditText()
    }

    private fun initEditText() {
        editId.addTextChangedListener {
            id = editId.text.toString()
            setLoginButton()
        }
        editPassword.addTextChangedListener {
            password = editPassword.text.toString()
            setLoginButton()
        }
    }

    private fun setLoginButton() {
        loginButtonOn = (!id.isNullOrEmpty() && !password.isNullOrEmpty())
        loginButton.isEnabled = loginButtonOn
    }

    private fun initButton() {
        signButton.setOnClickListener {
            startActivity(Intent(this, JoinActivity::class.java))
        }
        loginButton.setOnClickListener {
            var data: MutableMap<String, String?> = mutableMapOf()
            data["email"]=id
            data["password"]=password
            Log.d("JoinActivity","${data["email"]} ${data["password"]}")
            RetrofitBuilder.retrofitService.loginUser(data)
                .enqueue(object : Callback<UserLoginResponse> {
                    override fun onResponse(
                        call: Call<UserLoginResponse>?,
                        response: Response<UserLoginResponse>?
                    ) {
                        if (response!!.isSuccessful) {
                            when (response!!.code()) {
                                200 -> {
                                    Toast.makeText(this@MainActivity, "로그인 하셨습니다.", Toast.LENGTH_LONG).show()
                                    Log.d("MainActivity", "${response.body().toString()}")
                                }
                                401 -> Toast.makeText(
                                    this@MainActivity,
                                    "로그인에 실패했습니다.",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                        } else Toast.makeText(
                            this@MainActivity,
                            "로그인에 실패했습니다.",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }

                    override fun onFailure(call: Call<UserLoginResponse>?, t: Throwable?) {
                        Toast.makeText(
                            this@MainActivity,
                            "응답에 실패했습니다 ${t.toString()}.",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }

                })
        }
    }
}
