package lk.ijse.dinamore.busness.custom.impl;

import lk.ijse.dinamore.busness.custom.DeliverBO;
import lk.ijse.dinamore.dto.CheffDTO;
import lk.ijse.dinamore.dto.DeliverDTO;
import lk.ijse.dinamore.password.PasswordUtil;
import lk.ijse.dinamore.repository.RepositoryFactory;
import lk.ijse.dinamore.repository.custom.CheffRepository;
import lk.ijse.dinamore.repository.custom.DeliverRepository;
import lk.ijse.dinamore.repository.custom.impl.DeliverRepositoryImpl;
import lk.ijse.dinamore.util.HibanateUtil;
import org.hibernate.Session;

import java.util.List;

public class DeliverBOImpl implements DeliverBO {
    private DeliverRepository deliverRepository;

    public DeliverBOImpl() {
        this.deliverRepository = (DeliverRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.DELIVER);
    }

    @Override
    public boolean addDeliver(DeliverDTO deliverDTO) throws Exception {
        Session session= HibanateUtil.getSessionFactory().openSession();

        deliverRepository.setSession(session);

        session.beginTransaction();

        String userName=deliverDTO.getUserName();
        String password=deliverDTO.getPassword();
        String salt= PasswordUtil.getSalt(30);
        String secPw=PasswordUtil.generateSecurePassword(password, salt);

        DeliverDTO deliverDTO1=new DeliverDTO(deliverDTO.getDrivingLisionID(),deliverDTO.getName(),deliverDTO.getAdress(),deliverDTO.getTp(),deliverDTO.getVehicalNo(),userName,secPw,salt);
        boolean result=deliverRepository.save(deliverDTO1);

        session.getTransaction().commit();
        return result;
    }

    @Override
    public boolean updateDeliver(DeliverDTO deliverDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteDeliver(String id) throws Exception {
        return false;
    }

    @Override
    public DeliverDTO searchDelivers(int id) throws Exception {
        Session session= HibanateUtil.getSessionFactory().openSession();

        deliverRepository.setSession(session);

        session.beginTransaction();

        DeliverDTO result=deliverRepository.findById(id);

        session.getTransaction().commit();
        return result;
    }

    @Override
    public List<DeliverDTO> getAllDelivers() throws Exception {
        Session session= HibanateUtil.getSessionFactory().openSession();

        deliverRepository.setSession(session);

        session.beginTransaction();

        List<DeliverDTO> all = deliverRepository.findAll();

        session.getTransaction().commit();
        return all;
    }
}
