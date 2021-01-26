package com.aymer.sirketimceptebackend.common.interceptor;

import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.system.sirket.repository.SirketRepository;
import com.aymer.sirketimceptebackend.system.user.service.UserService;
import com.aymer.sirketimceptebackend.utils.SecurityUtils;
import com.aymer.sirketimceptebackend.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CompanyInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Autowired
    private SirketRepository companyRepository;

    @Autowired
    private SessionUtils sessionUtils;

    @Autowired
    private SecurityUtils securityUtils;


    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (securityUtils.hasAuthentication()) {
            String companyIdStr = request.getHeader("Company");
            if (StringUtils.hasText(companyIdStr)) {
                Sirket company = companyRepository.findById(Long.valueOf(companyIdStr)).get();
                if (sessionUtils.getUserDetails() != null) {
                    Long userId = sessionUtils.getUserDetails().getId();
                    if (userService.hasAutherationForCompany(userId, company.getId()))
                        sessionUtils.setSelectedCompany(company);
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {
    }
}
