package es.udc.fi.tfg.rest.dtos;

import es.udc.fi.tfg.model.entities.Subject;

import java.util.List;
import java.util.stream.Collectors;

public class SubjectConversor {

    public final static SubjectDto toSubjectDto(Subject subject) {
        return new SubjectDto(subject.getId(), subject.getSubjectName());
    }

    public final static List<SubjectDto> toSubjectDtos(List<Subject> subjects) {
        return subjects.stream().map(c -> toSubjectDto(c)).collect(Collectors.toList());
    }
}
