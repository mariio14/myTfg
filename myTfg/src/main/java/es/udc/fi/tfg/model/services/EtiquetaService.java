package es.udc.fi.tfg.model.services;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;

import java.util.List;

public interface EtiquetaService {

    void addEtiquetasToPost(Long postId, List<String> etiquetas) throws InstanceNotFoundException;

    void removeEtiquetaFromPost(Long postId, Long etiquetaId) throws InstanceNotFoundException;

    List<String> getKeys();

    List<String> getValuesFromKey(String key);
}
