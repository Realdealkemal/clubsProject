package kbaproject.clubsProject.Business.concretes;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import kbaproject.clubsProject.Business.requests.CreatePPartnerRequest;
import kbaproject.clubsProject.Business.rules.ClubBusinessRules;
import kbaproject.clubsProject.DataAccess.abstracts.PlayersPartnerRepository;
import kbaproject.clubsProject.core.utilities.mappers.ModelMapperService;
import kbaproject.clubsProject.entities.PlayersPartner;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@NoArgsConstructor
@Service
public class PlayersPartnerImpl implements PlayersPartnerRepository {

    private EntityManager entityManager;
    private  ModelMapperService modelMapperService;

    @Autowired
    public PlayersPartnerImpl(EntityManager entityManager, ModelMapperService modelMapperService) {
        this.entityManager = entityManager;
        this.modelMapperService = modelMapperService;
    }


    @Override
    @Transactional
    public void save(PlayersPartner playersPartner) {
        entityManager.persist(playersPartner);

    }

    @Override
    public PlayersPartner findById(int id) {

     return  entityManager.find(PlayersPartner.class,id);

    }

    @Override
    @Transactional
    public void delete(int id) {
       PlayersPartner pPartner=entityManager.find(PlayersPartner.class,id);
       pPartner.getPlayer().setPlayersPartner(null); //Öncelikle referans bağını koparırız
       entityManager.remove(pPartner);

    }

    @Override
    public List<PlayersPartner> getAll() {
        List<PlayersPartner> ppList=  entityManager.createQuery("select pp from PlayersPartner pp").getResultList();
        return ppList;
    }

    @Override
    public List<PlayersPartner> getByName(String name) {
      TypedQuery<PlayersPartner> listP = entityManager.createQuery("select pp from PlayersPartner pp where pp.name=:name ",PlayersPartner.class);
      listP.setParameter("name",name); //parametreyi böyle ayarlarız

    return   listP.getResultList();

    }

   @Override
    public PlayersPartner getByPlayerId(int id) {
        TypedQuery<PlayersPartner> pp =entityManager.createQuery("select pp from PlayersPartner pp Inner join Player p on p.playersPartner=pp.id where p.id=:id",PlayersPartner.class);
        pp.setParameter("id",id);

        return pp.getSingleResult();
    }

    @Override
    public List<PlayersPartner> getByClubsName(String name) {
        TypedQuery<PlayersPartner> pp= entityManager.createQuery("select pp from PlayersPartner pp Inner Join  Player p on p.playersPartner=pp.id Inner join Club c on c.id=p.club where c.name=:clubName",PlayersPartner.class);
        pp.setParameter("clubName",name);
        return pp.getResultList();
    }

    @Override
    @Transactional
    public void update(PlayersPartner playersPartner,int id) {
       PlayersPartner updateablePlayersPartner =entityManager.find(PlayersPartner.class,id);
       updateablePlayersPartner.setName(playersPartner.getName());
       updateablePlayersPartner.setContactNumber(playersPartner.getContactNumber());
       updateablePlayersPartner.setPlayer(playersPartner.getPlayer());

       entityManager.merge(updateablePlayersPartner);


    }

    @Override
    public List<PlayersPartner> checkIfExistsAnyPlayersPartner(String contactNumber, String name) {

        TypedQuery<PlayersPartner> pp= entityManager.createQuery("select pp from PlayersPartner pp where pp.name=:name and pp.contactNumber=:contactNumber",PlayersPartner.class);


        pp.setParameter("name",name);
        pp.setParameter("contactNumber",contactNumber);
       return pp.getResultList();
    }

    @Override
    public List<PlayersPartner> listsTop3PlayersPartner() {
        TypedQuery<PlayersPartner> pp= entityManager.createQuery("select new kbaproject.clubsProject.entities.PlayersPartner ( pp.id,pp.name,pp.contactNumber,pp.player)  from PlayersPartner pp order by name limit 3",PlayersPartner.class);
        return  pp.getResultList();
    }

    @Override
    public List<PlayersPartner> getAvgNameJenniferandCeyse(String name,String name2) {
        TypedQuery<PlayersPartner> pp= entityManager.createQuery("select avg(id) from PlayersPartner where name in (select name from PlayersPartner where name in (:name, :name2))",PlayersPartner.class);
        pp.setParameter("name",name);
        pp.setParameter("name2",name2);

        return pp.getResultList();
    }

    @Override
    public List<PlayersPartner> getByTeams(String teamName) {
        TypedQuery<PlayersPartner> pp= entityManager.createQuery("select pp from PlayersPartner pp inner join Players p on p.id=pp.player inner join club c on c.id=p.club where c.name=:teamName ",PlayersPartner.class);
        pp.setParameter("teamName",teamName);

        return pp.getResultList();
    }

    @Override
    public List<PlayersPartner> pageablePartners(int pageNo,int pageSize){
        TypedQuery<PlayersPartner> pp = entityManager.createQuery("from PlayersPartner",PlayersPartner.class);
        pp.setFirstResult((pageNo-1) * pageSize); //ilk gösterilecek kaydın indexi
        pp.setMaxResults(pageSize); // son  gösterilecek kaydın indexi(first resultun üstüne koyar)
        return  pp.getResultList();
    }

    @Override
    public List<PlayersPartner> contactNumberCharacterGreater(int a) {
        TypedQuery<PlayersPartner> pp= entityManager.createQuery("select new kbaproject.clubsProject.entities.PlayersPartner ( pp.id,pp.name,pp.contactNumber,pp.player) from PlayersPartner pp where LENGTH(pp.contactNumber)>:parametre",PlayersPartner.class);
        pp.setParameter("parametre",a);

        return pp.getResultList();
    }


}
