package com.nqh.usermanage.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.nqh.usermanage.activities.SignInActivity
import com.nqh.usermanage.activities.SignUpActivity
import com.nqh.usermanage.models.User
import com.nqh.usermanage.utils.Constants

class FirestoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUser(activity: SignUpActivity, userInfo: com.nqh.usermanage.models.User){
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserID())
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegisteredSuccess()
        }.addOnFailureListener {
            e ->
                Log.e("log hiep", "Error")
            }
    }

    fun signInUser(activity: SignInActivity){
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->
                val loggedInUser = document.toObject(User::class.java)
                if(loggedInUser != null)
                    activity.signInSuccess(loggedInUser)
            }.addOnFailureListener {
                    e ->
                Log.e("tag hiep", "Error")
            }
    }


    fun getCurrentUserID(): String{
        return FirebaseAuth.getInstance().currentUser!!.uid
    }

}