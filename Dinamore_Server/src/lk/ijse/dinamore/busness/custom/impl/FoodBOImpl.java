package lk.ijse.dinamore.busness.custom.impl;

import lk.ijse.dinamore.busness.custom.FoodBO;
import lk.ijse.dinamore.dto.FoodDTO;
import lk.ijse.dinamore.repository.RepositoryFactory;
import lk.ijse.dinamore.repository.custom.FoodRepository;
import lk.ijse.dinamore.util.HibanateUtil;
import org.hibernate.Session;

import java.util.List;

public class FoodBOImpl implements FoodBO {
    private FoodRepository foodRepository;
    public FoodBOImpl() {
        this.foodRepository=(FoodRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.FOOD);
    }

    @Override
    public boolean addFood(FoodDTO foodDTO) throws Exception {
        Session session= HibanateUtil.getSessionFactory().openSession();

        foodRepository.setSession(session);
        session.beginTransaction();

        FoodDTO foodDTO1=new FoodDTO(foodDTO.getFoodID(),foodDTO.getFoodName(),foodDTO.getPrice());
        boolean result=foodRepository.save(foodDTO1);
        session.getTransaction().commit();
        return result;
    }

    @Override
    public boolean updateFood(FoodDTO foodDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteFood(String id) throws Exception {
        return false;
    }

    @Override
    public FoodDTO searchFood(int id) throws Exception {
        Session session= HibanateUtil.getSessionFactory().openSession();

        foodRepository.setSession(session);
        session.beginTransaction();

        FoodDTO foodDTO1=foodRepository.findById(id);
        session.getTransaction().commit();
        return foodDTO1;
    }


    @Override
    public List<FoodDTO> getAllFoods() throws Exception {
        Session session = HibanateUtil.getSessionFactory().openSession();

        foodRepository.setSession(session);

        session.beginTransaction();

        List<FoodDTO> foods = foodRepository.findAll();

        session.getTransaction().commit();

        if (foods != null) {
            return foods;

        } else {

            return null;
        }
    }
}
