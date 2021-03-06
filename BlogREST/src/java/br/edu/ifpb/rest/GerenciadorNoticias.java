/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.rest;

import br.edu.ifpb.dao.DAO;
import br.edu.ifpb.dao.DAONoticia;
import br.edu.ifpb.model.Noticia;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * REST Web Service
 *
 * @author Manu
 */
@Path("gerenciador")
public class GerenciadorNoticias {

    @Context
    private UriInfo context;
    
    private DAONoticia dao;
    private List<Noticia> noticias;
    /**
     * Creates a new instance of Noticia
     */
    public GerenciadorNoticias() {
       //em = Persistence.createEntityManagerFactory("Projeto_Rest_PDPU").createEntityManager();
       
      dao = new DAONoticia();
      DAO.open();
      DAO.begin();
      this.noticias = dao.findAll();
      DAO.close();
 
    }

   
    @GET
    @Produces("application/json")
    public Response getNoticia(@QueryParam("id")Long id){
        dao.open();
        dao.begin();
        
        Noticia n = dao.find(Noticia.class, id);
        if(n != null){
            dao.close();
            return Response.ok(n, MediaType.APPLICATION_JSON).build();
        }
        else{
            dao.close();
            return Response.status(Response.Status.NOT_FOUND).build();
            
        }
    }
   
    @POST
    @Consumes("application/xml")
    public Response create(Noticia noticia) {
        
        dao.open();
        dao.begin();
        Noticia exist = dao.find(noticia.getId());
        if(exist != null){
            dao.close();
            return Response.status(Status.CONFLICT).build();
        }
            
        Noticia n = new Noticia(noticia.getId(),noticia.getAutor(), noticia.getTitulo(), noticia.getConteudo());

        dao.persist(n);
        dao.commit();
        dao.close();

       
        return Response.ok("Noticia Cadastrada com Sucesso - "+noticia.toString()).build();
    }
    
    
    
    @DELETE
    
    @Path("{id}")
    @Produces("application/xml")
    public Response remove(@PathParam("id")Long id){
    
      
        dao.open();
        dao.begin();
        Noticia n = dao.find(Noticia.class,id);
   
        if (n==null){
            dao.close();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
            
        dao.remove(n);
        dao.commit();
        dao.close();
       
        return Response.ok(n,MediaType.APPLICATION_XML).build();
    }
    
    
    @PUT
    @Produces("application/json")
    @Path("{id}/{titulo}")
    public Response update(@PathParam("id") Long id,@PathParam("titulo")String titulo) { 
        dao.open();
        dao.begin();
        Noticia n = dao.find(id); 
        
        if(n == null){
            dao.close();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
         
        n.setTitulo(titulo);        
        dao.merge(n);
        dao.commit();
        dao.close();
        return Response.ok(n,MediaType.APPLICATION_JSON).build();
    }
  
}