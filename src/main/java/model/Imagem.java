
package model;

/**
 *
 * @author Douglas
 */
public class Imagem {

    protected int idImagem;
    protected String nome;

    public Imagem(int idImagem, String nome) {
        this.idImagem = idImagem;
        this.nome = nome;
    }

    public int getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(int idImagem) {
        this.idImagem = idImagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    

}
