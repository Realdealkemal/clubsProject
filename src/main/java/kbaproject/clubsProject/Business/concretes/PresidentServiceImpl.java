package kbaproject.clubsProject.Business.concretes;

import kbaproject.clubsProject.Business.abstracts.PresidentService;
import kbaproject.clubsProject.Business.responses.GetAllClubsResponse;
import kbaproject.clubsProject.Business.responses.GetAllPresidentResponse;
import kbaproject.clubsProject.Business.responses.GetAllTrainersResponse;
import kbaproject.clubsProject.DataAccess.abstracts.PresidentRepository;
import kbaproject.clubsProject.core.utilities.mappers.ModelMapperService;
import kbaproject.clubsProject.entities.President;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PresidentServiceImpl implements PresidentService {
    @Autowired
    PresidentRepository presidentRepository;
    @Autowired
    ModelMapperService modelMapperService;
    @Override
    public List<GetAllPresidentResponse> getAll() {
        List<President> allPresidents= this.presidentRepository.findAll();
        List<GetAllPresidentResponse> getAllPresidentResponses= allPresidents.stream().map(president-> this.modelMapperService.forResponse().map(president, GetAllPresidentResponse.class)).collect(Collectors.toList());
        return  getAllPresidentResponses;
    }
}
