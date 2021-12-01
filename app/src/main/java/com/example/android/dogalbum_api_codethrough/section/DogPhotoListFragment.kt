package com.example.android.dogalbum_api_codethrough.section

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.android.dogalbum_api_codethrough.databinding.FragmentDogPhotoListBinding
import com.example.android.dogalbum_api_codethrough.model.DogViewModel

/**
 * A fragment representing a list of Items.
 */
class DogPhotoFragment : Fragment() {

    private var _binding: FragmentDogPhotoListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DogPhotoRecycleViewAdapter
    private val viewModel: DogViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDogPhotoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dogPhoto.observe(viewLifecycleOwner,{dogPhoto ->
            val dogPhotoList = dogPhoto.imageUrl
            adapter = DogPhotoRecycleViewAdapter( dogPhotoList!!)
            binding.list.adapter = adapter
        })
    }
}
