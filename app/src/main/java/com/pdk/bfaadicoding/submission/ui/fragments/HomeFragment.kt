package com.pdk.bfaadicoding.submission.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.pdk.bfaadicoding.submission.R
import com.pdk.bfaadicoding.submission.data.models.User
import com.pdk.bfaadicoding.submission.ui.adapter.UsersAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by Budi Ardianata on 26/06/2020.
 * Project: BFAAdicoding
 * Email: budiardianata@windowslive.com
 */
class HomeFragment : Fragment()  {

    private var users: MutableList<User> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        loadData()
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_home.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycler_home.adapter = UsersAdapter(users){ user, iv->
            findNavController().navigate(
                HomeFragmentDirections.detailsAction(user, user.username),
                FragmentNavigatorExtras(
                    iv to user.username
                )
            )
        }
    }

    private fun loadData(){
        users.clear()
        val names = resources.getStringArray(R.array.name)
        val usernames = resources.getStringArray(R.array.username)
        val avatars = resources.obtainTypedArray(R.array.avatar)
        val companies = resources.getStringArray(R.array.company)
        val locations = resources.getStringArray(R.array.location)
        val repositories = resources.getStringArray(R.array.repository)
        val followers = resources.getStringArray(R.array.followers)
        val followings = resources.getStringArray(R.array.following)

        for (i in names.indices) {
            users.add(
                User(usernames[i], names[i], avatars.getResourceId(i, 0),
                    companies[i], locations[i], repositories[i], followers[i],
                    followings[i])
            )
        }
        avatars.recycle()
    }
}
