package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.myapplication.api.RetrofitBuilder
import com.example.myapplication.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JoinActivity : AppCompatActivity() {

    private val editId: EditText by lazy {
        findViewById(R.id.editIdJoin)
    }
    private val editPassword: EditText by lazy {
        findViewById(R.id.editPasswordJoin)
    }
    private val editNickName: EditText by lazy {
        findViewById(R.id.editNickNameJoin)
    }
    private val successButton: Button by lazy {
        findViewById(R.id.buttonSuccessJoin)
    }
    private var id: String? = null
    private var password: String? = null
    private var nickName: String? = null
    private var successButtonOn: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        initEditText()
        initButton()
    }

    private fun initButton() {
        successButton.setOnClickListener {
            var data: MutableMap<String, String?> = mutableMapOf()
            data["email"] = id
            data["nick"] = nickName
            data["password"] = password
            Log.d("JoinActivity","${data["email"]} ${data["nick"]} ${data["password"]}")
            RetrofitBuilder.retrofitService.joinUser(data)
                .enqueue(object : Callback<UserResponse> {
                    override fun onResponse(
                        call: Call<UserResponse>?,
                        response: Response<UserResponse>?
                    ) {
                        if (response!!.isSuccessful) {
                            when (response!!.code()) {
                                201 -> {
                                    Toast.makeText(
                                        this@JoinActivity,
                                        "${nickName}님이 회원 가입에 성공하셨습니다.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    finish()
                                }
                                400 -> Toast.makeText(
                                    this@JoinActivity,
                                    "이미 로그인 된 아이디입니다.",
                                    Toast.LENGTH_LONG
                                ).show()
                                422 -> Toast.makeText(
                                    this@JoinActivity,
                                    "이미 존재하는 아이디입니다.",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                        } else Toast.makeText(
                            this@JoinActivity,
                            "이미 존재하는 아이디입니다.",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }

                    override fun onFailure(call: Call<UserResponse>?, t: Throwable?) {
                        Toast.makeText(
                            this@JoinActivity,
                            "응답에 실패했습니다..",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }

                })
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun initEditText() {
        editId.addTextChangedListener {
            id = editId.text.toString()
            setSuccessButton()
        }
        editPassword.addTextChangedListener {
            password = editPassword.text.toString()
            setSuccessButton()
        }
        editNickName.addTextChangedListener {
            nickName = editNickName.text.toString()
            setSuccessButton()
        }
    }

    private fun setSuccessButton() {
        successButtonOn =
            (!id.isNullOrEmpty() && !password.isNullOrEmpty() && !nickName.isNullOrEmpty())
        successButton.isEnabled = successButtonOn
    }
}