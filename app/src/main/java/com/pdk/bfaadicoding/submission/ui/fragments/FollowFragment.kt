package com.pdk.bfaadicoding.submission.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pdk.bfaadicoding.submission.R
import com.pdk.bfaadicoding.submission.databinding.FragmentFollowBinding
import com.pdk.bfaadicoding.submission.ui.adapters.UsersAdapter
import com.pdk.bfaadicoding.submission.ui.viewmodels.FollowViewModel
import com.pdk.bfaadicoding.submission.utils.Status
import com.pdk.bfaadicoding.submission.utils.TypeView

private const val TYPE = "type"
private const val USERNAME = "username"

class FollowFragment : Fragment() {
    private lateinit var binding: FragmentFollowBinding
    private lateinit var username: String
    private var type: String? = null
    private lateinit var usersAdapter: UsersAdapter

    private lateinit var followViewModel: FollowViewModel

    companion object {
        fun newInstance(username: String, type: String) =
            FollowFragment().apply {
                arguments = Bundle().apply {
                    putString(USERNAME, username)
                    putString(TYPE, type)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(USERNAME).toString()
            type = it.getString(TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FollowViewModel::class.java)

        usersAdapter = UsersAdapter(arrayListOf()) { user, _ ->
            Toast.makeText(context, user, Toast.LENGTH_SHORT).show()
        }

        binding.recylerFollow.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = usersAdapter
        }

        when (type) {
            resources.getString(R.string.following) -> {
                followViewModel.setParam(username, TypeView.FOLLOWING)

            }
            resources.getString(R.string.followers) -> {
                followViewModel.setParam(username, TypeView.FOLLOWER)
            }
            else -> {
                showError(null)
            }
        }

        observeData()
    }

    private fun observeData() {
        followViewModel.dataFollow.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty()) {
                        showSuccess()
                        usersAdapter.run { setData(it.data) }
                    } else {
                        showError(resources.getString(R.string.not_have, username, type))
                    }
                }

                Status.LOADING -> {
                    showLoading()
                }

                Status.ERROR -> {
                    showError(it.message)
                }
            }
        })
    }

    private fun showError(message: String?) {
        binding.errLayout.main.visibility = View.VISIBLE
        binding.errLayout.emptyText.text = message ?: resources.getString(R.string.not_found)
        binding.progress.visibility = View.GONE
        binding.recylerFollow.visibility = View.GONE
    }

    private fun showSuccess() {
        binding.errLayout.main.visibility = View.GONE
        binding.progress.visibility = View.GONE
        binding.recylerFollow.visibility = View.VISIBLE
    }

    private fun showLoading() {
        binding.errLayout.main.visibility = View.GONE
        binding.progress.visibility = View.VISIBLE
        binding.recylerFollow.visibility = View.GONE
    }
}