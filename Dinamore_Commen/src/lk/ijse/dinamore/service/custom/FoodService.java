package lk.ijse.dinamore.service.custom;

import lk.ijse.dinamore.dto.FoodDTO;
import lk.ijse.dinamore.service.SuperService;

import java.util.List;

public interface FoodService extends SuperService {
    public boolean addFood(FoodDTO foodDTO)throws Exception;

    public boolean updateFood(FoodDTO foodDTO)throws Exception;

    public boolean deleteFood(String id)throws Exception;

    public FoodDTO searchFood(int id)throws Exception;


    public List<FoodDTO> getAllFoods()throws Exception;
}
