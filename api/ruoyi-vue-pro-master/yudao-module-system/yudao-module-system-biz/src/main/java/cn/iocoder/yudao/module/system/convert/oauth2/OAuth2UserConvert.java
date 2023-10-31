package cn.iocoder.yudao.module.system.convert.oauth2;

import cn.iocoder.yudao.module.system.controller.admin.oauth2.vo.user.OAuth2UserInfoRespVO;
import cn.iocoder.yudao.module.system.controller.admin.oauth2.vo.user.OAuth2UserUpdateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.profile.UserProfileUpdateReqVO;

import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OAuth2UserConvert {

    OAuth2UserConvert INSTANCE = Mappers.getMapper(OAuth2UserConvert.class);

    OAuth2UserInfoRespVO convert(AdminUserDO bean);

    UserProfileUpdateReqVO convert(OAuth2UserUpdateReqVO bean);

}
