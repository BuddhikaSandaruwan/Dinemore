package lk.ijse.dinamore.service.custom;

import lk.ijse.dinamore.dto.DeliverDTO;
import lk.ijse.dinamore.service.SuperService;

import java.util.List;

public interface DeliverService extends SuperService {
    public boolean addDeliver(DeliverDTO deliverDTO)throws Exception;

    public boolean updateDeliver(DeliverDTO deliverDTO)throws Exception;

    public boolean deleteDeliver(String id)throws Exception;

    public DeliverDTO searchDeliver(int id)throws Exception;

    public List<DeliverDTO> getAllDelivers()throws Exception;
}
