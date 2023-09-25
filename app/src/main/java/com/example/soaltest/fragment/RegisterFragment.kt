package com.example.soaltest.fragment

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.room.Room
import com.example.soaltest.R
import com.example.soaltest.databinding.FragmentLoginBinding
import com.example.soaltest.databinding.FragmentRegisterBinding
import com.example.soaltest.db.AppDatabase
import com.example.soaltest.db.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root

        db = Room.databaseBuilder(requireContext().applicationContext, AppDatabase::class.java, "test-db").build()

        binding.password.typeface = Typeface.DEFAULT
        binding.confirmPassword.typeface = Typeface.DEFAULT

        binding.btnRegister.setOnClickListener {

            if (isValidation()) {
                val user = User(
                    username = binding.username.text.toString(), password = binding.password.text.toString(), age = binding.age.text.toString().toInt()
                )

                GlobalScope.launch {
                    db.userDao().insertAll(user)
                }

                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
            }

        }

        binding.btnBack.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame, LoginFragment())
            transaction.commit()
        }

        return view
    }

    fun isValidation() : Boolean {

        val minimal6 = Pattern.compile("^.{6,}$")

        if (!minimal6.matcher(binding.password.text.toString()).matches()) {
            Toast.makeText(context, "Password minimal 6 karakter!", Toast.LENGTH_SHORT).show()
            return false
        }

        val gabunganHurufDanAngka = Pattern.compile("^(?=.*[a-z])$")
        if (!gabunganHurufDanAngka.matcher(binding.password.text.toString()).matches()) {
            Toast.makeText(context, "Password gabungan huruf dan angka!", Toast.LENGTH_SHORT).show()
            return false
        }

        Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show()
        return true


//        if (binding.username.text.toString() == "") {
//            Toast.makeText(context, "Username wajib diisi!", Toast.LENGTH_SHORT).show()
//        } else if (binding.password.text.toString() == "") {
//            Toast.makeText(context, "Password wajib diisi!", Toast.LENGTH_SHORT).show()
//        } else if (binding.confirmPassword.text.toString() == "") {
//            Toast.makeText(context, "Confirm Password wajib diisi!", Toast.LENGTH_SHORT).show()
//        }  else if (binding.age.text.toString() == "") {
//            Toast.makeText(context, "Age wajib diisi!", Toast.LENGTH_SHORT).show()
//        } else if (binding.password.text.toString() != binding.confirmPassword.text.toString()) {
//            Toast.makeText(context, "Password tidak sama!", Toast.LENGTH_SHORT).show()
//        } else if (binding.age.text.toString() != "") {
//            if (binding.age.text.toString().toInt() < 18) {
//                Toast.makeText(context, "Age tidak boleh kurang dari 18 Tahun", Toast.LENGTH_SHORT).show()
//            } else {
//                return true
//            }
//        } else {
//            return true
//        }
//
//        return false
    }

}