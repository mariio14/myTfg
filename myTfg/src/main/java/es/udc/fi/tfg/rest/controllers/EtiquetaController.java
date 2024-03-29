package es.udc.fi.tfg.rest.controllers;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.tfg.model.services.EtiquetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/etiquetas")
public class EtiquetaController {

    @Autowired
    private EtiquetaService etiquetaService;


    @PostMapping("/{postId}")
    public void addEtiquetaToPost(@PathVariable Long postId, @RequestParam String etiqueta) throws InstanceNotFoundException {
        etiquetaService.addEtiquetaToPost(postId,etiqueta);
    }

    @PutMapping("/{postId}/{etiquetaId}")
    public void removeEtiquetaFromPost(@PathVariable Long postId, @PathVariable Long etiquetaId) throws InstanceNotFoundException {
        etiquetaService.removeEtiquetaFromPost(postId,etiquetaId);
    }
}
