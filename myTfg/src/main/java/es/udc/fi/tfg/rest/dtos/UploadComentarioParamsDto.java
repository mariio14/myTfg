package es.udc.fi.tfg.rest.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UploadComentarioParamsDto {
    
    private String textoComentario;

    public UploadComentarioParamsDto(){}

    public UploadComentarioParamsDto(String textoComentario) {
        this.textoComentario = textoComentario;
    }


    @NotNull
	@Size(min=1, max = 254)
    public String getTextoComentario() {
        return textoComentario;
    }

    public void setTextoComentario(String textoComentario) {
        this.textoComentario = textoComentario;
    }

    
    
}
