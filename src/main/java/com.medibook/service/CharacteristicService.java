package com.medibook.service;

import com.medibook.entities.Characteristic;
import com.medibook.repository.CharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CharacteristicService {

    private final CharacteristicRepository characteristicRepository;

    @Autowired
    public CharacteristicService(CharacteristicRepository characteristicRepository) {
        this.characteristicRepository = characteristicRepository;
    }

    public List<Characteristic> getAllCharacteristics() {
        return characteristicRepository.findAll();
    }

    public Characteristic getCharacteristicById(Long id) {
        return characteristicRepository.findById(id).orElse(null);
    }

    public Characteristic createCharacteristic(Characteristic characteristic) {
        return characteristicRepository.save(characteristic);
    }

    public void deleteCharacteristic(Long id) {
        characteristicRepository.deleteById(id);
    }

    // Actualiza una característica existente por su ID
    public Characteristic updateCharacteristic(Long id, Characteristic updatedCharacteristic) {
        Optional<Characteristic> optionalCharacteristic = characteristicRepository.findById(id);
        if (optionalCharacteristic.isPresent()) {
            Characteristic characteristic = optionalCharacteristic.get();
            characteristic.setName(updatedCharacteristic.getName());
            characteristic.setUrlicon(updatedCharacteristic.getUrlicon());
            // Actualiza otros campos si es necesario
            return characteristicRepository.save(characteristic);
        }
        else { throw new NoSuchElementException("Characteristic not found with ID: " + id);
        }
    }

    // Obtiene una característica por su nombre
    public Characteristic getCharacteristicByName(String name) {
        Optional<Characteristic> characteristic = characteristicRepository.findByName(name);
        return characteristic.orElse(null);
    }
}
