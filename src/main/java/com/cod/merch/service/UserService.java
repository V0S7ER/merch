package com.cod.merch.service;

import com.cod.merch.model.DTO.request.ChangeUserRequest;
import com.cod.merch.model.DTO.response.BooleanResponse;
import com.cod.merch.model.Department;
import com.cod.merch.model.Item;
import com.cod.merch.model.User;
import com.cod.merch.repository.DepartmentRepository;
import com.cod.merch.repository.ItemRepository;
import com.cod.merch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    private final DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    public boolean addItemToBasket(Long item_id, String email, String password) {
        try {
            Item item = getItemById(item_id);
            User user = getAuthorizedUser(email, password);
            if (user == null || item == null) return false;
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
            if (user == null || item == null) return false;
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
            if (user == null || item == null) return false;
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
            if (user == null || item == null) return false;
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
            if (user == null) return false;
            Long sum = user.getBasket().stream().mapToLong(Item::getPrice).sum();
            if (sum == null || sum > user.getBalance()) return false;
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
            if (user == null) return false;
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
            if (user == null) return false;
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
            if (user == null || item == null) return false;
            if (user.getBalance() < item.getPrice()) return false;
            user.removeFromBasket(item);
            user.setBalance(user.getBalance() - item.getPrice());
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changeUser(ChangeUserRequest request) {
        try {
            User user = getAuthorizedUser(request.getUser_email(), request.getUser_password());
            if (user == null) return false;
            if (request.getEmail() != null && !request.getEmail().isEmpty()) {
                user.setEmail(request.getEmail());
            }
            if (request.getName() != null && !request.getName().isEmpty()) {
                user.setName(request.getName());
            }
            if (request.getSurname() != null && !request.getSurname().isEmpty()) {
                user.setSurname(request.getSurname());
            }
            if (request.getSex() != null) {
                user.setSex(request.getSex());
            }
            if (request.getDepartment_id() != null) {
                Optional<Department> department = departmentRepository.findById(request.getDepartment_id());
                department.ifPresent(user::setDepartment);
            }
            if (request.getBirthday() != null && !request.getBirthday().isEmpty()) {
                Date date = format.parse(request.getBirthday());
                user.setBirthday(date);
            }
            if (request.getPhoto() != null && !request.getPhoto().isEmpty()) {
                user.setPhoto(request.getPhoto());
            }
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public BooleanResponse itemContainsInBasket(Long id, String email, String password) {
        try {
            Item item = getItemById(id);
            User user = getAuthorizedUser(email, password);
            if(item == null || user == null) return new BooleanResponse(false);
            return new BooleanResponse(user.getBasket().contains(item));
        } catch (Exception e) {
            e.printStackTrace();
            return new BooleanResponse(false);
        }
    }

    public BooleanResponse itemContainsInWishList(Long id, String email, String password) {
        try {
            Item item = getItemById(id);
            User user = getAuthorizedUser(email, password);
            if(item == null || user == null) return new BooleanResponse(false);
            return new BooleanResponse(user.getWishList().contains(item));
        } catch (Exception e) {
            e.printStackTrace();
            return new BooleanResponse(false);
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
