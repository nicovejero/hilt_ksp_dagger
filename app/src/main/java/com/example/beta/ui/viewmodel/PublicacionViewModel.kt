package com.example.beta.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.beta.data.BreedRepository
import com.example.beta.data.database.entities.BreedEntity
import com.example.beta.util.Result
import com.example.beta.data.database.entities.Pet
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PublicacionViewModel @Inject constructor(
    private val breedRepository: BreedRepository
) : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    // Observes the database and converts Flow to LiveData
    val breedsLiveData: LiveData<List<BreedEntity>> by lazy {
        breedRepository.getAllBreeds().asLiveData()
    }

    fun addPet(pet: Pet, onComplete: (Result<String>) -> Unit) {
        db.collection("pets")
            .add(pet.toMap()) // Corrected: Ensure there is a toMap() method in Pet class
            .addOnSuccessListener { documentReference ->
                onComplete(Result.Success(documentReference.id))
            }
            .addOnFailureListener { e ->
                onComplete(Result.Error(e))
            }
    }
}