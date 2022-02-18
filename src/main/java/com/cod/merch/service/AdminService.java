package com.cod.merch.service;

import com.cod.merch.model.*;
import com.cod.merch.model.DTO.request.AchievementRequest;
import com.cod.merch.model.DTO.request.ContestRequest;
import com.cod.merch.model.DTO.request.ItemRequest;
import com.cod.merch.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final ContestRepository contestRepository;
    private final AchievementRepository achievementRepository;
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    private final DateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");

    public boolean setAdmin(Long userId, String adminEmail, String adminPassword) {
        if (!isAdmin(adminEmail, adminPassword)) {
            return false;
        }
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) return false;
            User user = userOptional.get();
            user.setIsAdmin(true);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addContestWinner(Long userId, Long contestId, String adminEmail, String adminPassword) {
        if (!isAdmin(adminEmail, adminPassword)) {
            return false;
        }
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) return false;
            Optional<Contest> contestOptional = contestRepository.findById(contestId);
            if (contestOptional.isEmpty()) return false;
            User user = userOptional.get();
            Contest contest = contestOptional.get();
            user.addWonContest(contest);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean createAchievement(AchievementRequest request) {
        if (!isAdmin(request.getAdmin_email(), request.getAdmin_password())) {
            return false;
        }
        try {
            if (request.getName() == null || request.getName().isEmpty()) return false;
            if (request.getPhoto() == null || request.getPhoto().isEmpty()) return false;
            if (request.getCost() == null) return false;
            if (request.getDescription() == null || request.getDescription().isEmpty()) return false;
            Achievement achievement = new Achievement(request.getName(), request.getCost(), request.getDescription(), request.getPhoto());
            achievementRepository.save(achievement);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean createContest(ContestRequest request) {
        if (!isAdmin(request.getAdmin_email(), request.getAdmin_password())) {
            return false;
        }
        try {
            if (request.getDate() == null || request.getDate().isEmpty()) return false;
            if (request.getName() == null || request.getName().isEmpty()) return false;
            if (request.getCost() == null) return false;
            Date date = format.parse(request.getDate());
            Contest contest = new Contest(request.getName(), date, request.getCost());
            contestRepository.save(contest);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean createItem(ItemRequest request) {
        if (!isAdmin(request.getAdmin_email(), request.getAdmin_password())) {
            return false;
        }
        try {
            if (request.getName() == null || request.getName().isEmpty()) return false;
            if (request.getPrice() == null) return false;
            if (request.getDescription() == null || request.getDescription().isEmpty()) return false;
            if (request.getPhoto() == null || request.getPhoto().isEmpty()) return false;
            Item item = new Item(request.getName(), request.getPrice(), request.getDescription(), request.getPhoto());
            itemRepository.save(item);
            return true;
        } catch (Exception e) {
            return false;
        }
    } //create

    public boolean changeItem(Long id, ItemRequest request) {
        if (!isAdmin(request.getAdmin_email(), request.getAdmin_password())) return false;
        try {
            Optional<Item> itemOptional = itemRepository.findById(id);
            if (itemOptional.isEmpty()) return false;
            Item item = itemOptional.get();
            if (request.getName() != null && !request.getName().isEmpty()) {
                item.setName(request.getName());
            }
            if (request.getPrice() != null) {
                item.setPrice(request.getPrice());
            }
            if (request.getDescription() != null && !request.getDescription().isEmpty()) {
                item.setDescription(request.getDescription());
            }
            if (request.getPhoto() != null && !request.getPhoto().isEmpty()) {
                item.setPhoto(request.getPhoto());
            }
            itemRepository.save(item);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean changeAchievement(Long id, AchievementRequest request) { //name cost description
        if (!isAdmin(request.getAdmin_email(), request.getAdmin_password())) return false;
        try {
            Optional<Achievement> achievementOptional = achievementRepository.findById(id);
            if (achievementOptional.isEmpty()) return false;
            Achievement achievement = achievementOptional.get();
            if (request.getName() != null && !request.getName().isEmpty()) {
                achievement.setName(request.getName());
            }
            if (request.getCost() != null) {
                Long delta = request.getCost() - achievement.getCost();
                achievement.setCost(request.getCost());
                if (delta > 0) { //Меняем баланс только если он изменяется в лучшую сторону
                    for (var user : achievement.getReceivedUsers()) {
                        user.setBalance(user.getBalance() + delta);
                    }
                }
            }
            if (request.getDescription() != null && !request.getDescription().isEmpty()) {
                achievement.setDescription(request.getDescription());
            }
            if (request.getPhoto() != null && !request.getPhoto().isEmpty()) {
                achievement.setPhoto(request.getPhoto());
            }
            achievementRepository.save(achievement);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean changeContest(Long id, ContestRequest request) {
        if (!isAdmin(request.getAdmin_email(), request.getAdmin_password())) return false;
        try {
            Optional<Contest> contestOptional = contestRepository.findById(id);
            if (contestOptional.isEmpty()) return false;
            Contest contest = contestOptional.get();
            if (request.getName() != null && !request.getName().isEmpty()) {
                contest.setName(request.getName());
            }
            if (request.getDate() != null && !request.getDate().isEmpty()) {
                Date date = format.parse(request.getDate());
                contest.setDate(date);
            }
            if (request.getCost() != null) {
                Long delta = request.getCost() - contest.getCost();
                contest.setCost(request.getCost());
                if (delta > 0) { //Меняем баланс только если увеличили приз за контест
                    for (var user : contest.getWinners()) {
                        user.setBalance(user.getBalance() + delta);
                    }
                }
            }
            contestRepository.save(contest);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteContest(Long id, String adminEmail, String adminPassword) {
        if (!isAdmin(adminEmail, adminPassword)) return false;
        try {
            Optional<Contest> contestOptional = contestRepository.findById(id);
            if (contestOptional.isEmpty()) return false;
            for (var user : contestOptional.get().getWinners()) {
                user.removeWonContest(contestOptional.get());
            }
            contestRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteItem(Long id, String adminEmail, String adminPassword) {
        if (!isAdmin(adminEmail, adminPassword)) return false;
        try {
            itemRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteAchievement(Long id, String adminEmail, String adminPassword) {
        if (!isAdmin(adminEmail, adminPassword)) return false;
        try {
            Optional<Achievement> achievementOptional = achievementRepository.findById(id);
            if (achievementOptional.isEmpty()) return false;
            for (var user : achievementOptional.get().getReceivedUsers()) {
                user.removeAchievement(achievementOptional.get());
            }
            achievementRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setUserBalance(Long id, String adminEmail, String adminPassword, Long balance) {
        if (!isAdmin(adminEmail, adminPassword)) return false;
        try {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isEmpty()) return false;
            User user = userOptional.get();
            user.setBalance(balance);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean setAchievementToUser(Long userId, Long achievementId, String adminEmail, String adminPassword) {
        if (!isAdmin(adminEmail, adminPassword)) return false;
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            Optional<Achievement> achievementOptional = achievementRepository.findById(achievementId);
            if (userOptional.isEmpty() || achievementOptional.isEmpty()) return false;
            User user = userOptional.get();
            Achievement achievement = achievementOptional.get();
            user.addAchievement(achievement);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addItemToCategory(Long item_id, Long category_id, String admin_email, String admin_password) {
        if(!isAdmin(admin_email, admin_password)) return false;
        try {
            Optional<Item> itemOptional = itemRepository.findById(item_id);
            Optional<Category> categoryOptional = categoryRepository.findById(category_id);
            if(itemOptional.isEmpty() || categoryOptional.isEmpty()) return false;
            Item item = itemOptional.get();
            Category category = categoryOptional.get();
            item.addCategory(category);
            itemRepository.save(item);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isAdmin(String email, String password) {
        try {
            if (email == null) return false;
            if (password == null) return false;
            if (email.isEmpty() || password.isEmpty()) return false;
            Optional<User> user = userRepository.findByEmailAndPassword(email, password);
            if (user.isEmpty()) return false;
            return user.get().getIsAdmin();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
