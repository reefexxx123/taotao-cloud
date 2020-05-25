/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.social.service.Impl
 * Date: 2020/5/14 14:55
 * Author: dengtao
 */
package com.taotao.cloud.social.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.taotao.cloud.social.entity.GitHubUserInfo;
import com.taotao.cloud.social.entity.GiteeUserInfo;
import com.taotao.cloud.social.entity.QQUserInfo;
import com.taotao.cloud.social.service.SocialService;
import com.taotao.cloud.uc.api.entity.SysGiteeUser;
import com.taotao.cloud.uc.api.entity.SysGithubUser;
import com.taotao.cloud.uc.api.entity.SysQqUser;
import com.taotao.cloud.uc.api.feign.RemoteSocialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <br>
 *
 * @author dengtao
 * @version v1.0.0
 * @create 2020/5/14 14:55
 */
@Slf4j
@Service
public class SocialServiceImpl implements SocialService {
    @Autowired
    private RemoteSocialService remoteSocialService;

    @Override
    public void saveGiteeUserInfo(GiteeUserInfo userInfo) {
        SysGiteeUser giteeUser = new SysGiteeUser();
        BeanUtil.copyProperties(userInfo, giteeUser);
        remoteSocialService.saveGiteeUser(giteeUser);
    }

    @Override
    public void saveQQUserInfo(QQUserInfo userInfo) {
        SysQqUser qqUser = new SysQqUser();
        BeanUtil.copyProperties(userInfo, qqUser);
        qqUser.setIsLost(userInfo.getIs_lost());
        qqUser.setFigureUrl(userInfo.getFigureurl());
        qqUser.setFigureUrl1(userInfo.getFigureurl_1());
        qqUser.setFigureUrl2(userInfo.getFigureurl_2());

        qqUser.setFigureUrlQq1(userInfo.getFigureurl_qq_1());
        qqUser.setFigureUrlQq2(userInfo.getFigureurl_qq_2());

        qqUser.setIsYellowVip(userInfo.getIs_yellow_vip());
        qqUser.setYellowVipLevel(userInfo.getYellow_vip_level());
        qqUser.setIsYellowYearVip(userInfo.getIs_yellow_year_vip());
        remoteSocialService.saveQqUser(qqUser);
    }

    @Override
    public void saveGithubUserInfo(GitHubUserInfo userInfo) {
        SysGithubUser githubUser = new SysGithubUser();
        BeanUtil.copyProperties(userInfo, githubUser);
        remoteSocialService.saveGithubUser(githubUser);
    }
}
