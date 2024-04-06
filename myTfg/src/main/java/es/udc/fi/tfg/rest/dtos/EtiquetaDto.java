package es.udc.fi.tfg.rest.dtos;

public class EtiquetaDto {

    private Long id;

    private String key;

    private String value;

    private String text;

    public EtiquetaDto() {
    }

    public EtiquetaDto(Long id, String key, String value, String text) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
