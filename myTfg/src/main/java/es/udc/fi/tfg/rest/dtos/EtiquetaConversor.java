package es.udc.fi.tfg.rest.dtos;

import es.udc.fi.tfg.model.entities.Etiqueta;

import java.util.List;
import java.util.stream.Collectors;

public class EtiquetaConversor {

    public static final List<EtiquetaDto> toEtiquetaDtos(List<Etiqueta> etiquetas) {
        return etiquetas.stream().map(EtiquetaConversor::toEtiquetaDto).collect(Collectors.toList());
    }

    public static final EtiquetaDto toEtiquetaDto(Etiqueta etiqueta) {
        return new EtiquetaDto(etiqueta.getId(), etiqueta.getClave(), etiqueta.getValue(),
                etiqueta.getClave() + "-" +etiqueta.getValue());
    }
}
