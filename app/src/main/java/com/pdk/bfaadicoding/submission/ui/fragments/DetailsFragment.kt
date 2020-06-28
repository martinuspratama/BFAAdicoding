package com.pdk.bfaadicoding.submission.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.color.MaterialColors
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialContainerTransform
import com.pdk.bfaadicoding.submission.R
import com.pdk.bfaadicoding.submission.databinding.FragmentDetailsBinding
import com.pdk.bfaadicoding.submission.ui.viewmodels.DetailViewModel
import com.pdk.bfaadicoding.submission.utils.Status

/**
 * Created by Budi Ardianata on 26/06/2020.
 * Project: BFAAdicoding
 * Email: budiardianata@windowslive.com
 */
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    private val args: DetailsFragmentArgs by navArgs()

    private lateinit var pagerAdapter: PagerAdapter

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailViewModel::class.java)

        detailViewModel.setUsername(args.Username)

        val transformation: MaterialContainerTransform = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host
            interpolator = FastOutSlowInInterpolator()
            containerColor = MaterialColors.getColor(
                requireActivity().findViewById(android.R.id.content),
                R.attr.colorSurface
            )
            fadeMode = MaterialContainerTransform.FADE_MODE_OUT
            duration = 300
        }
        sharedElementEnterTransition = transformation
        sharedElementReturnTransition = transformation
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        observeData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.content.transitionName = args.Username
        val tabList = arrayOf(
            resources.getString(R.string.followers),
            resources.getString(R.string.following)
        )
        pagerAdapter = PagerAdapter(tabList, args.Username, this)
        binding.pager.adapter = pagerAdapter

        TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
            tab.text = tabList[position]
        }.attach()
    }

    private fun observeData() {
        detailViewModel.data.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS) {
                binding.data = it.data
            }
        })
    }

    inner class PagerAdapter(
        private val tabList: Array<String>,
        private val username: String,
        fragment: Fragment
    ) : FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int = tabList.size

        override fun createFragment(position: Int): Fragment =
            FollowFragment.newInstance(username, tabList[position])
    }

}
