package com.blog.connect.mapper;

import com.blog.connect.model.CommentaryModel;
import com.blog.connect.model.PostModel;
import com.blog.connect.model.SecurityUserModel;
import com.blog.connect.model.UserModel;
import com.blog.connect.persistence.entity.CommentaryEntity;
import com.blog.connect.persistence.entity.PostEntity;
import com.blog.connect.persistence.entity.UserEntity;
import com.blog.connect.web.dto.AccountCredentialsRequestDto;
import com.blog.connect.web.dto.AccountLoginRequestDto;
import com.blog.connect.web.dto.CommentaryRequestDto;
import com.blog.connect.web.dto.CommentaryResponseDto;
import com.blog.connect.web.dto.PostRequestDto;
import com.blog.connect.web.dto.PostResponseDto;
import com.blog.connect.web.dto.UserDataRequestDto;
import com.blog.connect.web.dto.UserResponseDto;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/** Mapper for service layer. */
@org.mapstruct.Mapper(
    unmappedTargetPolicy = ReportingPolicy.WARN,
    builder = @Builder(disableBuilder = true))
public interface Mapper {
  /** Instance. */
  Mapper I = Mappers.getMapper(Mapper.class);

  /**
   * UserEntity to SecurityUserModel mapper.
   *
   * @param userEntity userEntity.
   * @return SecurityUserModel.
   */
  SecurityUserModel userEntityToSecurityUserModel(UserEntity userEntity);

  /**
   * SecurityUserModel to UserModel mapper.
   *
   * @param securityUserModel SecurityUserModel.
   * @return SecurityUserModel.
   */
  UserModel securityUserModelToModel(SecurityUserModel securityUserModel);

  /**
   * UserEntity to UserModel mapper.
   *
   * @param userEntity UserEntity.
   * @return UserModel.
   */
  UserModel userEntityToModel(UserEntity userEntity);

  /**
   * UserModel to UserEntity mapper.
   *
   * @param userModel UserModel.
   * @return UserEntity.
   */
  UserEntity userModelToEntity(UserModel userModel);

  /**
   * Update UserEntity From UserModel.
   *
   * @param userModel UserModel.
   * @return UserEntity.
   */
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  UserEntity updateUserEntityFromModel(UserModel userModel, @MappingTarget UserEntity userEntity);

  /**
   * PostEntity to PostModel.
   *
   * @param postEntity PostEntity.
   * @return PostModel.
   */
  PostModel postEntityToModel(PostEntity postEntity);

  /**
   * List of PostEntity to List of PostModel.
   *
   * @param postEntity PostEntity.
   * @return List of PostModel.
   */
  List<PostModel> postEntitiesToModels(List<PostEntity> postEntity);

  /**
   * CommentaryModel to CommentaryEntity.
   *
   * @param commentaryModel CommentaryModel.
   * @return CommentaryEntity.
   */
  CommentaryEntity commentaryModelToEntity(CommentaryModel commentaryModel);

  /**
   * CommentaryEntity to Commentary.
   *
   * @param commentaryEntity CommentaryEntity.
   * @return Commentary.
   */
  CommentaryModel commentaryEntityToModel(CommentaryEntity commentaryEntity);

  /**
   * List of CommentaryEntity to List of Commentary.
   *
   * @param commentaryEntityEntities List of CommentaryEntity.
   * @return List of Commentary.
   */
  List<CommentaryModel> commentaryEntitiesToModels(List<CommentaryEntity> commentaryEntityEntities);

  /**
   * UserDataRequestDto to UserModel mapper.
   *
   * @param requestDto UserDataRequestDto.
   * @return UserModel.
   */
  UserModel userDataRequestDtoToUserModel(UserDataRequestDto requestDto);

  /**
   * AccountCredentialsRequestDto to UserModel mapper.
   *
   * @param requestDto AccountCredentialsRequestDto.
   * @return UserModel.
   */
  UserModel accountCredentialsRequestDtoToUser(AccountCredentialsRequestDto requestDto);

  /**
   * AccountLoginRequestDto to UserModel mapper.
   *
   * @param requestDto AccountLoginRequestDto.
   * @return UserModel.
   */
  UserModel accountLoginRequestDtoToUser(AccountLoginRequestDto requestDto);

  /**
   * PostRequestDto to PostModel mapper.
   *
   * @param requestDto PostRequestDto.
   * @return PostModel.
   */
  PostModel postRequestDtoToModel(PostRequestDto requestDto);

  /**
   * PostModel to PostResponseDto mapper.
   *
   * @param postModel PostModel.
   * @return PostResponseDto.
   */
  PostResponseDto postModelToResponseDto(PostModel postModel);

  /**
   * UserModel to UserModelResponseDto mapper.
   *
   * @param userModel UserModel.
   * @return UserModelResponseDto.
   */
  UserResponseDto userModelToResponseDto(UserModel userModel);

  /**
   * List of PostModel to List of PostResponseDto mapper.
   *
   * @param postModel PostModel
   * @return PostResponseDto
   */
  List<PostResponseDto> postModelsToResponseDto(List<PostModel> postModel);

  /**
   * CommentaryModel to CommentaryResponseDto.
   *
   * @param commentaryModel CommentaryModel.
   * @return CommentaryResponseDto.
   */
  CommentaryResponseDto commentaryModelToResponseDto(CommentaryModel commentaryModel);

  /**
   * CommentaryRequestDto to CommentaryModel mapper.
   *
   * @param commentaryRequestDto CommentaryRequestDto.
   * @return CommentaryModel.
   */
  CommentaryModel commentaryRequestDtoToModel(CommentaryRequestDto commentaryRequestDto);

  /**
   * List of CommentaryModel to List of CommentaryResponseDto.
   *
   * @param commentaries List of CommentaryModel.
   * @return List of CommentaryResponseDto.
   */
  List<CommentaryResponseDto> commentaryModelsToResponseDtoList(List<CommentaryModel> commentaries);
}
