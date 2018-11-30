package lk.ijse.dinamore.service.custom;

import lk.ijse.dinamore.dto.ReseptionDTO;
import lk.ijse.dinamore.service.SuperService;

import java.util.List;

public interface ReseptionService extends SuperService {
    public boolean addReseption(ReseptionDTO reseptionDTO)throws Exception;

    public boolean updateReseption(ReseptionDTO reseptionDTO)throws Exception;

    public boolean deleteReseption(String id)throws Exception;

    public List<ReseptionDTO> getAllReseptionS()throws Exception;

    public ReseptionDTO searchReseption(int id)throws Exception;
}
