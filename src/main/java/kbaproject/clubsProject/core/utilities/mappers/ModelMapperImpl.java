package kbaproject.clubsProject.core.utilities.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperImpl implements ModelMapperService {
    @Autowired
    ModelMapper getModelMapper; //buradaki modelmapper enjeksiyonu otomatik olmaz
    // üçüncü taraf bir sınıf olduğu için kendi elimizle üretmek gerekir bunu ana classımızda yaptık. Burada sadece kullanıyoruz

    @Override
    public ModelMapper forResponse() {
        this.getModelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.LOOSE); //cevap için gevşek mapleme yap. sadece gereken şeyleri maple

        return this.getModelMapper;
    }

    @Override
    public ModelMapper forRequest() {
        this.getModelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.LOOSE);

        return this.getModelMapper;
    }
}
