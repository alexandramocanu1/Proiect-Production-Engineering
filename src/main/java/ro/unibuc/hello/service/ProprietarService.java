package ro.unibuc.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.unibuc.hello.dto.Proprietar;
import ro.unibuc.hello.data.InformationEntity;
import ro.unibuc.hello.data.ProprietarEntity;
import ro.unibuc.hello.data.ProprietarRepository;
import ro.unibuc.hello.exception.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProprietarService {
    @Autowired
    private ProprietarRepository proprietarRepository;

    public ProprietarService(ProprietarRepository proprietarRepository){
        this.proprietarRepository = proprietarRepository;
    }

    public List<ProprietarEntity> getAllProprietari(){
        return proprietarRepository.findAll();
    }

    public Optional<ProprietarEntity> getProprietarById(String id){
        return proprietarRepository.findById(id);
    }

    public Proprietar createProprietar(Proprietar proprietarDTO) {
        ProprietarEntity proprietarEntity = new ProprietarEntity(
            proprietarDTO.getId(), 
            proprietarDTO.getNume(), 
            proprietarDTO.getPrenume(), 
            proprietarDTO.getEmail()
        );
        proprietarEntity = proprietarRepository.save(proprietarEntity);
        return new Proprietar(
            proprietarEntity.getId(),
            proprietarEntity.getNume(),
            proprietarEntity.getPrenume(),
            proprietarEntity.getEmail()
        );
    }

    public Optional<ProprietarEntity> updateProprietar(String id, ProprietarEntity proprietarDetails){
        return proprietarRepository.findById(id).map(existingProprietar ->{
            existingProprietar.setNume(proprietarDetails.getNume());
            existingProprietar.setPrenume(proprietarDetails.getPrenume());
            existingProprietar.setEmail(proprietarDetails.getEmail());

            return proprietarRepository.save(existingProprietar);
        });
    }
}
