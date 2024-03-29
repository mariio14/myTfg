package es.udc.fi.tfg.model.services;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.tfg.model.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EtiquetaServiceImpl implements EtiquetaService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private EtiquetaDao etiquetaDao;

    @Autowired
    private EtiquetaOfPostDao etiquetaOfPostDao;

    @Override
    public void addEtiquetaToPost(Long postId, String etiqueta) throws InstanceNotFoundException {

        Optional<Post> optionalPost = postDao.findById(postId);
        if (!optionalPost.isPresent()) {
            throw new InstanceNotFoundException("Post no encontrado", postId);
        }

        String[] parts = etiqueta.split("-");
        String key = parts[0];
        String value = parts[1];

        Optional<Etiqueta> etiquetaOptional = etiquetaDao.findByKeyAndValue(key, value);

        Etiqueta etiqueta1;
        if (etiquetaOptional.isEmpty()) {
            etiqueta1 = new Etiqueta(key, value);
            etiquetaDao.save(etiqueta1);
        }
        else etiqueta1 = etiquetaOptional.get();

        EtiquetaOfPost etiquetaOfPost = new EtiquetaOfPost(optionalPost.get(),etiqueta1);
        etiquetaOfPostDao.save(etiquetaOfPost);
    }

    @Override
    public void removeEtiquetaFromPost(Long postId, Long etiquetaId) throws InstanceNotFoundException {

        Optional<Post> optionalPost = postDao.findById(postId);
        if (!optionalPost.isPresent()) {
            throw new InstanceNotFoundException("Post no encontrado", postId);
        }

        Optional<Etiqueta> optionalEtiqueta = etiquetaDao.findById(etiquetaId);
        if (!optionalEtiqueta.isPresent()) {
            throw new InstanceNotFoundException("Etiqueta no encontrada", etiquetaId);
        }

        Optional<EtiquetaOfPost> optionalEtiquetaOfPost = etiquetaOfPostDao.findByPostAndEtiqueta(optionalPost.get(), optionalEtiqueta.get());
        if (!optionalEtiquetaOfPost.isPresent()) {
            throw new InstanceNotFoundException("Etiqueta no encontrada", etiquetaId);
        }

        etiquetaOfPostDao.delete(optionalEtiquetaOfPost.get());
        optionalPost.get().getEtiquetas().remove(optionalEtiquetaOfPost.get());
        optionalEtiqueta.get().getEtiquetas().remove(optionalEtiquetaOfPost.get());
    }
}
