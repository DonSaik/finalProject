/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.resources;

/**
 * Class used to represent links for HATEOAS.
 * @author donatas
 */
public class Link {

    
    private String href;
    private String rel;

    public void setHref(String href) {
        this.href = href;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public String getRel() {
        return rel;
    }
    /**
     * Default constructor.
     */
    public Link() {
        
    }
    /**
     * Constructor
     * @param href link.
     * @param rel relation.
     */
     public Link(String href, String rel) {
        this.href = href;
        this.rel = rel;
    }

    
}
