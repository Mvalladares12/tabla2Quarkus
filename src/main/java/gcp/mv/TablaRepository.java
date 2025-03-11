package gcp.mv;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TablaRepository implements PanacheRepository<Tabla> {
}