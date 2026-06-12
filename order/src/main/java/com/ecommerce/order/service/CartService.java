package com.ecommerce.order.service;

import com.ecommerce.order.client.ProductServiceClient;
import com.ecommerce.order.client.UserServiceClient;
import com.ecommerce.order.dto.CartItemRequest;
import com.ecommerce.order.dto.ProductResponse;
import com.ecommerce.order.dto.UserResponse;
import com.ecommerce.order.model.CartItem;

import com.ecommerce.order.repository.CartItemRepository;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {


    @Autowired
    private CartItemRepository cartItemRepository;

    private final ProductServiceClient productServiceClient;

    private final UserServiceClient userServiceClient;


    public boolean addToCart(String userId, CartItemRequest request) {

        try {
            UserResponse userOpt = userServiceClient.getUserDetails(userId);
            if(userOpt==null)
                return false;
            ProductResponse productResponse = productServiceClient.getProductDetails((request.getProductId()));

            if (productResponse.getStockQuantity() < request.getQuantity())
                return false;

        }catch(FeignException.NotFound ex){
            return false;
        }



//
        CartItem existingCartItem = cartItemRepository.findByUserIdAndProductId(userId, request.getProductId());
        if(existingCartItem != null){
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
            existingCartItem.setPrice(BigDecimal.valueOf(1000.00));
            cartItemRepository.save(existingCartItem);
        }
        else {
            CartItem cartItem = new CartItem();
            cartItem.setUserId(userId
            );
            cartItem.setProductId(request.getProductId());
            cartItem.setQuantity(request.getQuantity());
            cartItem.setPrice(BigDecimal.valueOf(1000.00));
            cartItemRepository.save(cartItem);
        }
        return true;
    }

    public boolean deleteItemFromCart(String userId, String productId) {
       CartItem cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);
        if(cartItem != null){
            cartItemRepository.delete(cartItem);
            return true;
        }
        return false;
    }

    public List<CartItem> getCartItems(String userId) {

        return cartItemRepository.findByUserId((userId));


    }

    public void clearCart(String userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}
