package app.servarium.adapter.persistence.adapter.mapper;

import app.servarium.adapter.persistence.entity.JpaRefreshToken;
import app.servarium.domain.model.RefreshToken;
import org.mapstruct.Mapper;

@Mapper(
        config = JpaMapperConfig.class,
        uses = JpaUserMapper.class
)
public interface JpaRefreshTokenMapper {
    RefreshToken toDomain(JpaRefreshToken entity);

    JpaRefreshToken toEntity(RefreshToken domain);
}
