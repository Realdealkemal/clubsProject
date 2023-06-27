package kbaproject.clubsProject.Business.abstracts;

import kbaproject.clubsProject.Business.responses.GetAllPresidentResponse;
import kbaproject.clubsProject.entities.President;

import java.util.List;

public interface PresidentService {
    List<GetAllPresidentResponse> getAll();
}
