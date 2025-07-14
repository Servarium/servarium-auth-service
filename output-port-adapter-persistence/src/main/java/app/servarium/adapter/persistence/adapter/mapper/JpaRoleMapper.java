package app.servarium.adapter.persistence.adapter.mapper;

import app.servarium.adapter.persistence.entity.JpaRole;
import app.servarium.domain.model.Role;
import org.mapstruct.Mapper;

@Mapper(
        config = JpaMapperConfig.class
)
public interface JpaRoleMapper {
    Role toDomain(JpaRole entity);
}
