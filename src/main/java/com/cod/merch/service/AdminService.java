package com.cod.merch.service;

import com.cod.merch.model.Achievement;
import com.cod.merch.model.Contest;
import com.cod.merch.model.DTO.request.AchievementRequest;
import com.cod.merch.model.DTO.request.ContestRequest;
import com.cod.merch.model.DTO.request.ItemRequest;
import com.cod.merch.model.Item;
import com.cod.merch.model.User;
import com.cod.merch.repository.AchievementRepository;
import com.cod.merch.repository.ContestRepository;
import com.cod.merch.repository.ItemRepository;
import com.cod.merch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final ContestRepository contestRepository;
    private final AchievementRepository achievementRepository;
    private final ItemRepository itemRepository;

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
            if (request.getDate() == null) return false;
            if (request.getName() == null || request.getName().isEmpty()) return false;
            if (request.getCost() == null) return false;
            Contest contest = new Contest(request.getName(), request.getDate(), request.getCost());
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
            if (request.getDate() != null) {
                contest.setDate(request.getDate());
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
