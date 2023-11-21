package cn.hh.harbor.module.system.api.sms;

import cn.hh.harbor.framework.common.enums.UserTypeEnum;
import cn.hh.harbor.module.system.api.sms.dto.send.SmsSendSingleToUserReqDTO;
import cn.hh.harbor.module.system.service.sms.SmsSendService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * 短信发送 API 接口
 */
@Service
@Validated
public class SmsSendApiImpl implements SmsSendApi {

    @Resource
    private SmsSendService smsSendService;

    @Override
    public Long sendSingleSmsToAdmin(SmsSendSingleToUserReqDTO reqDTO) {
        return smsSendService.sendSingleSms(reqDTO.getMobile(), reqDTO.getUserId(), UserTypeEnum.ADMIN.getValue(),
                reqDTO.getTemplateCode(), reqDTO.getTemplateParams());
    }

    @Override
    public Long sendSingleSmsToMember(SmsSendSingleToUserReqDTO reqDTO) {
        return smsSendService.sendSingleSms(reqDTO.getMobile(), reqDTO.getUserId(), UserTypeEnum.APP.getValue(),
                reqDTO.getTemplateCode(), reqDTO.getTemplateParams());
    }

}