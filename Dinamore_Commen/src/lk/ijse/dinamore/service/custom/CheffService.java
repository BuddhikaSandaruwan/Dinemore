package lk.ijse.dinamore.service.custom;

import lk.ijse.dinamore.dto.CheffDTO;
import lk.ijse.dinamore.service.SuperService;

import java.util.List;

public interface CheffService extends SuperService {
    public boolean addCheff(CheffDTO cheffDTO)throws Exception;

    public boolean updatecheff(CheffDTO cheffDTO)throws Exception;

    public boolean deleteCheff(String id)throws Exception;

    public CheffDTO searchCheff(int id)throws Exception;

    public List<CheffDTO> getAllCheffs() throws Exception;
}
