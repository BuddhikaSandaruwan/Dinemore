package lk.ijse.dinamore.busness.custom.impl;

import lk.ijse.dinamore.busness.custom.CheffBO;
import lk.ijse.dinamore.dto.CheffDTO;
import lk.ijse.dinamore.repository.RepositoryFactory;
import lk.ijse.dinamore.repository.custom.CheffRepository;
import lk.ijse.dinamore.util.HibanateUtil;
import lk.ijse.dinamore.password.PasswordUtil;
import org.hibernate.Session;

import java.util.List;

public class CheffBOImpl implements CheffBO {
    private CheffRepository cheffRepository;

    public CheffBOImpl() {
        this.cheffRepository = (CheffRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.CHEFF);
    }

    @Override
    public boolean addCheff(CheffDTO cheffDTO) throws Exception {
        Session session= HibanateUtil.getSessionFactory().openSession();

        cheffRepository.setSession(session);

        session.beginTransaction();

        String userName=cheffDTO.getUserName();
        String password=cheffDTO.getPassword();
        String salt= PasswordUtil.getSalt(30);
        String secPw=PasswordUtil.generateSecurePassword(password, salt);

        CheffDTO cheffDTO1=new CheffDTO(
                cheffDTO.getCheffNIC(),
                cheffDTO.getName(),
                cheffDTO.getAdress(),
                cheffDTO.getTp(),
                userName,
                secPw,
                salt);
        boolean result=cheffRepository.save(cheffDTO1);

        session.getTransaction().commit();
        return result;
    }

    @Override
    public boolean updateCheff(CheffDTO cheffDTO) throws Exception {
        Session session= HibanateUtil.getSessionFactory().openSession();

        cheffRepository.setSession(session);

        session.beginTransaction();

        String userName=cheffDTO.getUserName();
        String password=cheffDTO.getPassword();
        String salt= PasswordUtil.getSalt(30);
        String secPw=PasswordUtil.generateSecurePassword(password, salt);

        CheffDTO cheffDTO1=new CheffDTO(
                cheffDTO.getCheffNIC(),
                cheffDTO.getName(),
                cheffDTO.getAdress(),
                cheffDTO.getTp(),
                userName,
                secPw,
                salt);
        boolean result=cheffRepository.update(cheffDTO1);

        session.getTransaction().commit();
        return result;
    }

    @Override
    public boolean deleteCheff(String id) throws Exception {
        return false;
    }

    @Override
    public CheffDTO searchCheff(int id) throws Exception {
        Session session= HibanateUtil.getSessionFactory().openSession();

        cheffRepository.setSession(session);

        session.beginTransaction();

        CheffDTO byId = cheffRepository.findById(id);

        session.getTransaction().commit();
        return byId;
    }

    @Override
    public List<CheffDTO> getAllCheffs() throws Exception {
        Session session= HibanateUtil.getSessionFactory().openSession();

        cheffRepository.setSession(session);

        session.beginTransaction();

        List<CheffDTO> all = cheffRepository.findAll();

        session.getTransaction().commit();
        return all;
    }
}
