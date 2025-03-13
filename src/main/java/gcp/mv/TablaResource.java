package gcp.mv;

import io.smallrye.config.WithDefault;
import io.smallrye.config.WithName;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/tabla")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
public class TablaResource {

    @Inject
    private TablaRepository tablaRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tabla> findAll() {
        return Tabla.listAll();
    }

    @GET
    @Path("{id}")
    public Tabla getShoe(@PathParam("id") Long id) {
        return Tabla.findById(id);
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Tabla tabla) {
        tabla.persist();
        return Response.created(URI.create("/tabla/"+ tabla.getId())).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id){
        Tabla entity = Tabla.findById(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Tabla update(@PathParam("id")Long id, Tabla tabla) {
        var entity = tablaRepository.findById(id);
        if (entity != null) {
            entity.setCodigo(tabla.getCodigo());
            entity.setNombre(tabla.getNombre());
            tablaRepository.persist(entity);
            return entity;
        }else {
            throw new NotFoundException();
        }
    }
}
