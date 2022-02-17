package com.cod.merch.service;

import com.cod.merch.model.*;
import com.cod.merch.model.DTO.AchievementDTO;
import com.cod.merch.model.DTO.ContestDTO;
import com.cod.merch.model.DTO.ItemDTO;
import com.cod.merch.model.DTO.UserDTO;
import com.cod.merch.model.DTO.CategoryDTO;
import com.cod.merch.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ContestRepository contestRepository;
    private final DepartmentRepository departmentRepository;
    private final AchievementRepository achievementRepository;

    public ItemDTO getItemById(Long id) {
        try {
            Optional<Item> itemOptional = itemRepository.findById(id);
            if (itemOptional.isEmpty()) return null;
            return new ItemDTO(itemOptional.get());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<CategoryDTO> getAllCategories() {
        try {
            List<Category> categoryList = categoryRepository.findAll();
            return transformListCategory(categoryList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ItemDTO> getItemsByCategoryId(Long id) {
        try {
            Optional<Category> categoryOptional = categoryRepository.findById(id);
            if (categoryOptional.isEmpty()) return null;
            Category category = categoryOptional.get();
            return transformListItems(category.getItemList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public UserDTO getUserById(Long id) {
        try {
            User user = getUser(id);
            if (user == null) return null;
            return new UserDTO(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ItemDTO> getBasketByUserId(Long id) {
        try {
            User user = getUser(id);
            if (user == null) return null;
            List<ItemDTO> answer = new ArrayList<>();
            for (var item : user.getBasket()) {
                answer.add(new ItemDTO(item));
            }
            return answer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ItemDTO> getWishListByUserId(Long id) {
        try {
            User user = getUser(id);
            if (user == null) return null;
            List<ItemDTO> answer = new ArrayList<>();
            for (var item : user.getWishList()) {
                answer.add(new ItemDTO(item));
            }
            return answer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<UserDTO> getWinnersByContestId(Long id) {
        try {
            Optional<Contest> contestOptional = contestRepository.findById(id);
            if (contestOptional.isEmpty()) return null;
            Contest contest = contestOptional.get();
            return transformListUser(contest.getWinners());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private User getUser(Long id) {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            return userOptional.isEmpty() ? null : userOptional.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<ItemDTO> transformListItems(List<Item> items) {
        try {
            List<ItemDTO> ans = new ArrayList<>();
            for (var el : items) {
                ans.add(new ItemDTO(el));
            }
            return ans;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ContestDTO> getAllContests() {
        try {
            List<Contest> contests = contestRepository.findAll();
            return transformListContest(contests);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ContestDTO getContestById(Long id) {
        try {
            Optional<Contest> contestOptional = contestRepository.findById(id);
            if (contestOptional.isEmpty()) return null;
            return new ContestDTO(contestOptional.get());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<UserDTO> getUsersByDepartmentId(Long id) {
        try {
            Optional<Department> departmentOptional = departmentRepository.findById(id);
            if (departmentOptional.isEmpty()) return null;
            return transformListUser(departmentOptional.get().getUserList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<AchievementDTO> getAllAchievements() {
        try {
            List<Achievement> achievementList = achievementRepository.findAll();
            return transformListAchievement(achievementList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<AchievementDTO> getLockedAchievementsByUserId(Long id) {
        try {
            User user = getUser(id);
            if (user == null) return null;
            List<Achievement> all = achievementRepository.findAll();
            List<Achievement> userAchievements = user.getAchievementList();
            List<Achievement> ans = new ArrayList<>(all);
            ans.removeAll(userAchievements);
            return transformListAchievement(ans);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<AchievementDTO> getAchievementsByUserId(Long id) {
        try {
            User user = getUser(id);
            if (user == null) return null;
            return transformListAchievement(user.getAchievementList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<AchievementDTO> transformListAchievement(List<Achievement> list) {
        try {
            List<AchievementDTO> ans = new ArrayList<>();
            for (var el : list) {
                ans.add(new AchievementDTO(el));
            }
            return ans;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<CategoryDTO> transformListCategory(List<Category> categoryList) {
        try {
            List<CategoryDTO> ans = new ArrayList<>();
            for (var el : categoryList) {
                ans.add(new CategoryDTO(el));
            }
            return ans;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<UserDTO> transformListUser(List<User> users) {
        try {
            List<UserDTO> ans = new ArrayList<>();
            for (var user : users) {
                ans.add(new UserDTO(user));
            }
            return ans;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ContestDTO> transformListContest(List<Contest> contests) {
        try {
            List<ContestDTO> ans = new ArrayList<>();
            for (var contest : contests) {
                ans.add(new ContestDTO(contest));
            }
            return ans;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<CategoryDTO> getCategoriesByItemId(Long id) {
        try {
            Optional<Item> itemOptional = itemRepository.findById(id);
            if (itemOptional.isEmpty()) return null;
            Item item = itemOptional.get();
            return transformListCategory(item.getCategoryList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<UserDTO> getUsersByAchievementId(Long id) {
        try {
            Optional<Achievement> achievementOptional = achievementRepository.findById(id);
            if (achievementOptional.isEmpty()) return null;
            return transformListUser(achievementOptional.get().getReceivedUsers());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
