package cn.hh.harbor.module.system.controller.admin.user;

import cn.hh.harbor.framework.common.pojo.CommonResult;
import cn.hh.harbor.framework.tenant.core.context.TenantContextHolder;
import cn.hh.harbor.module.system.controller.admin.user.vo.team.UserTeamListRespVO;
import cn.hh.harbor.module.system.controller.admin.user.vo.team.UserTeamRespVO;
import cn.hh.harbor.module.system.convert.user.UserConvert;
import cn.hh.harbor.module.system.dal.dataobject.user.UserDO;
import cn.hh.harbor.module.system.service.permission.PermissionService;
import cn.hh.harbor.module.system.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

import static cn.hh.harbor.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.hh.harbor.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 管理团队")
@RestController
@RequestMapping("/system/user/team")
@Validated
@Slf4j
public class UserTeamController {

    @Resource
    private UserService userService;
    @Resource
    private PermissionService permissionService;

    @GetMapping("/list")
    @Operation(summary = "获得当前租户下的管理用户")
    @PreAuthorize("@ss.hasPermission('system:userteam:list')")
    public CommonResult<List<UserTeamListRespVO>> getUserList(@Validated String nickname) {
        List<UserDO> userDOList = userService.getUserListByTenantIdOrNickname(TenantContextHolder.getTenantId(), nickname);

        List<UserTeamListRespVO> result = userDOList.stream().map(e -> {
            UserTeamListRespVO userTeamListRespVO = UserConvert.INSTANCE.convertTeam(e);
            userTeamListRespVO.setRoleIds(permissionService.getUserRoleIdListByUserId(e.getId()));
            return userTeamListRespVO;
        }).collect(Collectors.toList());

        return success(result);
    }

    @GetMapping("/query")
    @Operation(summary = "根据用户昵称进行模糊查询,并过滤已经加入当前租户的用户", description = "发送邀请的下拉框匹配")
    public CommonResult<List<UserTeamRespVO>> getUserListByNickName(@Validated @NotBlank String nickname) {
        List<UserDO> userDOList = userService.getUsersByNicknameFilter(TenantContextHolder.getTenantId(), nickname);
        return success(UserConvert.INSTANCE.convertTeam(userDOList));
    }
}