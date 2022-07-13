package com.pimenov.feature_cart_impl.presentation.view

import android.animation.Animator
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.hedgehog.shadowLibrary.BaseShadowBuilder.Companion.ShadowLibrary
import com.pimenov.core_utils.recyclerUtils.autoCleared
import com.pimenov.feature_cart_api.CartNavigationApi
import com.pimenov.feature_cart_impl.R
import com.pimenov.feature_cart_impl.databinding.FragmentCartBinding
import com.pimenov.feature_cart_impl.di.CartFeatureComponent
import com.pimenov.feature_cart_impl.presentation.adapter.CartAdapter
import com.pimenov.feature_cart_impl.presentation.adapter.SwipeController
import com.pimenov.feature_cart_impl.presentation.view_model.CartViewModel
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_cart.view.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CartFragment : Fragment(R.layout.fragment_cart) {

    @Inject
    lateinit var cartNavigationApi: CartNavigationApi

    private val viewModel: CartViewModel by viewModels {
        CartFeatureComponent.cartFeatureComponent!!.fabric()
    }

    private val binding by viewBinding(FragmentCartBinding::bind)
    private val cartAdapter : CartAdapter by autoCleared {
        CartAdapter()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        CartFeatureComponent.cartFeatureComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        setAdapter()
        setBuyListener()

        ShadowLibrary.load(R.drawable.ic_shopping)
            .withShadowColor(R.color.shadow)
            .withShadowRadius(25)
            .withShadowScale(1.05f)
            .withShadowTransition(start = 10, end = 10)
            .withShadowTransition(top = 10, start = 10)
            .into(binding.shadowCart)
    }

    private fun setBuyListener(){
        binding.billCustomView.actionListener = {
            if (it){
                startAnimation()
            }
        }
    }

    private fun startAnimation(){
        viewModel.buyProduct()
        with(binding){
            successLoadingLottie.visibility = View.VISIBLE
            successLoadingLottie.addAnimatorListener(object : Animator.AnimatorListener{
                override fun onAnimationStart(p0: Animator?) {
                    cartRecycler.visibility = View.GONE
                    billCustomView.visibility = View.GONE

                }

                override fun onAnimationEnd(p0: Animator?) {
                    successLoadingLottie.visibility = View.GONE
                    shadowCart.visibility = View.VISIBLE
                    text_empty_cart.visibility = View.VISIBLE
                }

                override fun onAnimationCancel(p0: Animator?) {
                }

                override fun onAnimationRepeat(p0: Animator?) {
                }
            })
            successLoadingLottie.playAnimation()
        }
    }

    private fun setAdapter(){
        binding.cartRecycler.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            addItemDecoration(object : RecyclerView.ItemDecoration(){
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                    outRect.set(10,15,15,10)
                }
            })

            val swipeController = object : SwipeController(){
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    viewModel.deleteProduct(viewHolder.absoluteAdapterPosition)
                }
            }
            val itemTouchHelper = ItemTouchHelper(swipeController)
            itemTouchHelper.attachToRecyclerView(this)
        }
    }

   private fun observe(){
       viewModel.productCartSharedFlow.onEach { list ->
           cartAdapter.submitList(list)
           if (list.isNotEmpty()){
               with(binding.billCustomView){
                   textValueSum = list.sumOf {
                       it?.price?.toInt() ?: 0
                   }.toString()
               }
           }
       }.launchIn(viewLifecycleOwner.lifecycleScope)
   }
    override fun onPause() {
        if(isRemoving) {
            if (cartNavigationApi.isFeatureClosed(this)) {
                CartFeatureComponent.resetComponent()
            }
        }
        super.onPause()
    }
}