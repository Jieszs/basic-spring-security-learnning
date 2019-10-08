package com.smscode.demo.config;


import com.smscode.demo.authentication.mobile.SmsCodeAuthenticationFilter;
import com.smscode.demo.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.smscode.demo.authentication.mobile.SmsCodeFilter;
import com.smscode.demo.constant.SpringSecurityConstants;
import com.smscode.demo.handler.MyAuthenticationFailureHandler;
import com.smscode.demo.handler.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import javax.annotation.Resource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource
    private MyAuthenticationFailureHandler authenticationFailureHandler;
    @Resource
    private SmsCodeFilter smsCodeFilter;
    @Resource
    private SmsCodeAuthenticationSecurityConfig smsAuthenticationConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests() // 授权配置
                //表示跳转到登录页面的请求不被拦截，否则会进入无限循环。登录跳转 URL 无需认证
                .antMatchers("/auth", "/code/sms","/login.html").permitAll()//验证码
                .anyRequest()  // 所有请求
                .authenticated() // 都需要认证
                .and().csrf().disable()//关闭CSRF攻击防御
                .apply(smsAuthenticationConfig); // 将短信验证码认证配置加到 Spring Security 中
    }
}
