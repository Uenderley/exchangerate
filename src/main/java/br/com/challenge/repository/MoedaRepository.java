package br.com.challenge.repository;

import br.com.challenge.model.MoedaEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MoedaRepository implements PanacheRepository<MoedaEntity> {
}
