package es.udc.fi.tfg.rest.controllers;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.tfg.model.services.EtiquetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/etiquetas")
public class EtiquetaController {

    @Autowired
    private EtiquetaService etiquetaService;


    @GetMapping("/allKey")
    public List<String> getAllKeys(){
        return etiquetaService.getKeys();
    }

    @GetMapping("/values")
    public List<String> getAllValuesFromKey(@RequestParam String key){
        return etiquetaService.getValuesFromKey(key);
    }

    @PostMapping("/{postId}")
    public void addEtiquetasToPost(@PathVariable Long postId, @RequestParam List<String> etiquetas) throws InstanceNotFoundException {
        etiquetaService.addEtiquetasToPost(postId,etiquetas);
    }

    @PutMapping("/{postId}/{etiquetaId}")
    public void removeEtiquetaFromPost(@PathVariable Long postId, @PathVariable Long etiquetaId) throws InstanceNotFoundException {
        etiquetaService.removeEtiquetaFromPost(postId,etiquetaId);
    }
}
