/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.rest;

import br.edu.ifpb.dao.DAO;
import br.edu.ifpb.dao.DAONoticia;
import br.edu.ifpb.model.NoticiaEntity;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Manu
 */
@Path("Gerenciador")
public class GerenciadorNoticias {

    @Context
    private UriInfo context;
    
    private DAONoticia dao;
    
    private List<NoticiaEntity> noticias;

    /**
     * Creates a new instance of GerenciadorNoticias
     */
    public GerenciadorNoticias() {
        
      dao = new DAONoticia();
      DAO.open();
      DAO.begin();
      this.noticias = dao.findAll();
      DAO.close();
    }

    /**
     * Retrieves representation of an instance of br.edu.ifpb.rest.GerenciadorNoticias
     * @return an instance of java.lang.String
     */
    
      @GET
    @Path("/listar")
    public Response listarNoticias(){//@PathParam("id")Long id //@PathParam("id")Long id@QueryParam("id") String id, @QueryParam("formato")String formato
        
        String resposta = "";
        
        if(noticias.size() == 0)
            resposta += "Nao possui noticias cadastradas!";
        else{
            
            for(NoticiaEntity n : noticias){
                resposta += n.toString();
            }
        }
        
        return Response.ok(resposta,MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of GerenciadorNoticias
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
