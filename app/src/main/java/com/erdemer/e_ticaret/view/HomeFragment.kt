package com.erdemer.e_ticaret.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.erdemer.e_ticaret.R
import com.erdemer.e_ticaret.adapter.OnProductItemClickListener
import com.erdemer.e_ticaret.adapter.ProductRecyclerAdapter
import com.erdemer.e_ticaret.adapter.ViewPagerAdapter
import com.erdemer.e_ticaret.databinding.HomeFragmentBinding
import com.erdemer.e_ticaret.extension.invisible
import com.erdemer.e_ticaret.extension.visible
import com.erdemer.e_ticaret.model.Product
import com.erdemer.e_ticaret.model.ProductResponse
import com.erdemer.e_ticaret.util.Constants
import com.erdemer.e_ticaret.util.SharedPreferenceManager
import com.erdemer.e_ticaret.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.Runnable
import java.util.*


class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding
    private lateinit var spManager: SharedPreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getAllProducts()
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        spManager = SharedPreferenceManager()

        initObserver()

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.ivBackIcon?.invisible()
        context?.let {context ->
            activity?.let { activity ->
                spManager.readFromSharedPreferences(context, activity) } }

    }

    private fun initObserver() {
        viewModel.productResponseList.observe(viewLifecycleOwner, {
            binding.tvMainTitle.text = it.mainTitle
            makeTimedViewPager(it)
            binding.tvSubTitle.text = it.subTitle
            it.productList?.let { it1 -> setRVAdapter(it1.toMutableList()) }

        })
    }

    private fun makeTimedViewPager(item: ProductResponse) {
        val vpAdapter = item.categoryList?.let { ViewPagerAdapter(it) }
        binding.viewPager.adapter = vpAdapter
        val timerTask: TimerTask = object : TimerTask() {
            override fun run() {
                binding.viewPager.post(Runnable {
                    binding.viewPager.currentItem =
                        (binding.viewPager.currentItem + 1) % item.categoryList.size
                })
            }
        }
        val timer = Timer()
        timer.schedule(timerTask, 5000, 5000)
    }

    private fun setRVAdapter(productList: MutableList<Product>) {
        rvMainProducts.layoutManager = GridLayoutManager(context, 2)
        rvMainProducts.adapter = productList.let {
            ProductRecyclerAdapter(it, object : OnProductItemClickListener {
                override fun onClick(position: Int) {
                    val p_id = Integer.parseInt(it[position].id)
                    val bundle = Bundle().apply {
                        putInt("productId", p_id)
                    }
                    findNavController().navigate(
                        R.id.action_homeFragment_to_productDetailFragment,
                        bundle
                    )
                }
            })
        }
    }




}


