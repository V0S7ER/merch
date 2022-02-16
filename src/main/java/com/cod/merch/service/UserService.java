package com.cod.merch.service;

import com.cod.merch.model.Item;
import com.cod.merch.model.User;
import com.cod.merch.repository.ItemRepository;
import com.cod.merch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public boolean addItemToBasket(Long item_id, String email, String password) {
        try {
            Item item = getItemById(item_id);
            User user = getAuthorizedUser(email, password);
            if(user == null || item == null) return false;
            user.addToBasket(item);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addItemToWish(Long item_id, String email, String password) {
        try {
            Item item = getItemById(item_id);
            User user = getAuthorizedUser(email, password);
            if(user == null || item == null) return false;
            user.addToWish(item);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteItemFromWish(Long item_id, String email, String password) {
        try {
            Item item = getItemById(item_id);
            User user = getAuthorizedUser(email, password);
            if(user == null || item == null) return false;
            user.removeFromWish(item);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteItemFromBasket(Long item_id, String email, String password) {
        try {
            Item item = getItemById(item_id);
            User user = getAuthorizedUser(email, password);
            if(user == null || item == null) return false;
            user.removeFromBasket(item);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean buyBasket(String email, String password) {
        try {
            User user = getAuthorizedUser(email, password);
            if(user == null) return false;
            Long sum = user.getBasket().stream().mapToLong(Item::getPrice).sum();
            if(sum == null || sum > user.getBalance()) return false;
            user.setBalance(user.getBalance() - sum);
            user.setBasket(new ArrayList<>());
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean clearBasket(String email, String password) {
        try {
            User user = getAuthorizedUser(email, password);
            if(user == null) return false;
            user.setBasket(new ArrayList<>());
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean clearWish(String email, String password) {
        try {
            User user = getAuthorizedUser(email, password);
            if(user == null) return false;
            user.setWishList(new ArrayList<>());
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean buyItem(Long item_id, String email, String password) {
        try {
            User user = getAuthorizedUser(email, password);
            Item item = getItemById(item_id);
            if(user == null || item == null) return false;
            if(user.getBalance() < item.getPrice()) return false;
            user.removeFromBasket(item);
            user.setBalance(user.getBalance() - item.getPrice());
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private User getAuthorizedUser(String email, String password) {
        try {
            Optional<User> userOptional = userRepository.findByEmailAndPassword(email, password);
            if (userOptional.isEmpty()) return null;
            return userOptional.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Item getItemById(Long item_id) {
        try {
            Optional<Item> itemOptional = itemRepository.findById(item_id);
            if (itemOptional.isEmpty()) return null;
            return itemOptional.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
