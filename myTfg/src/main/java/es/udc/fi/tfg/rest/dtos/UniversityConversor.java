package es.udc.fi.tfg.rest.dtos;

import es.udc.fi.tfg.model.entities.University;

import java.util.List;
import java.util.stream.Collectors;

public class UniversityConversor {

    public final static UniversityDto toUniversityDto(University university) {
        return new UniversityDto(university.getId(), university.getUniName());
    }

    public final static List<UniversityDto> toUniversityDtos(List<University> universities) {
        return universities.stream().map(c -> toUniversityDto(c)).collect(Collectors.toList());
    }
}
