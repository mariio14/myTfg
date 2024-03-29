package es.udc.fi.tfg.model.services;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;

public interface EtiquetaService {

    void addEtiquetaToPost(Long postId, String etiqueta) throws InstanceNotFoundException;

    void removeEtiquetaFromPost(Long postId, Long etiquetaId) throws InstanceNotFoundException;
}
