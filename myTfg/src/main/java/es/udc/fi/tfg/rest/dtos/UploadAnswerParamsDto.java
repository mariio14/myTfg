package es.udc.fi.tfg.rest.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UploadAnswerParamsDto {
    
    private String textoComentario;
    
    private Long respuestaId;

    public UploadAnswerParamsDto(){}


    public UploadAnswerParamsDto(String textoComentario, Long respuestaId) {
		super();
		this.textoComentario = textoComentario;
		this.respuestaId = respuestaId;
	}

	@NotNull
	@Size(min=1, max = 254)
    public String getTextoComentario() {
        return textoComentario;
    }

    public void setTextoComentario(String textoComentario) {
        this.textoComentario = textoComentario;
    }


	public Long getRespuestaId() {
		return respuestaId;
	}


	public void setRespuestaId(Long respuestaId) {
		this.respuestaId = respuestaId;
	}
    
}