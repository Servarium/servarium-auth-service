package app.servarium.adapter.persistence.adapter.mapper;

import app.servarium.adapter.persistence.entity.JpaUser;
import app.servarium.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = JpaMapperConfig.class,
        uses = JpaRoleMapper.class
)
public interface JpaUserMapper {
    @Mapping(source = "passHash", target = "password")
    User toDomain(JpaUser entity);

    @Mapping(source = "password", target = "passHash")
    JpaUser toEntity(User domain);
}
